package pe.com.nttdata.empleado.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.nttdata.empleado.dao.IEmpleadoDao;
import pe.com.nttdata.empleado.model.Empleado;
import pe.com.nttdata.empleado.service.IEmpleadoService;
import pe.com.nttdata.empleadofeign.validar.empleado.EmpleadoCheckClient;
import pe.com.nttdata.empleadofeign.validar.empleado.EmpleadoCheckResponse;
import pe.com.nttdata.empleadoqueues.rabbitmq.RabbitMQMessageProducer;
import pe.com.nttdata.notificacion.controller.NotificacionRequest;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class EmpleadoServiceImpl implements IEmpleadoService {
    private final IEmpleadoDao empleadoDao;
    private  final EmpleadoCheckClient empleadoCheckClient;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;
    public Empleado registrarEmpleado(Empleado empleado) {
        Empleado empleadoResponse =empleadoDao.save(empleado);
        EmpleadoCheckResponse empleadoCheckResponse = empleadoCheckClient.validarEmpleado(empleadoResponse.getId());
        if (empleadoCheckResponse.esEstafador()) {
            throw new IllegalStateException("Empleado tiene de contacto!!");
        }
        //rabbbit
        NotificacionRequest notificacionRequest = new NotificacionRequest(
                empleado.getId(),
                empleado.getTelefono(),
                String.format("Hola %s, bienvenidos a NTTData...",
                        empleado.getNombres())
        );

        rabbitMQMessageProducer.publish(
                notificacionRequest,
                "internal.exchange",
                "internal.notification.routing-key"
        );
        return empleadoResponse;
    }
    public List<Empleado> listarEmpleadoPorFechaingreso(LocalDate fechaIngreso) {
        return empleadoDao.findByFechaIngreso(fechaIngreso);
    }
}
