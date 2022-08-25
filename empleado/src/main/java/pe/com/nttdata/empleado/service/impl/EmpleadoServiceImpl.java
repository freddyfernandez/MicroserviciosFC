package pe.com.nttdata.empleado.service.impl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.com.nttdata.empleado.dao.IEmpleadoDao;
import pe.com.nttdata.empleado.model.Empleado;
import pe.com.nttdata.empleado.service.IEmpleadoService;
import pe.com.nttdata.empleadofeign.validar.empleado.EmpleadoCheckClient;
import pe.com.nttdata.empleadofeign.validar.empleado.EmpleadoCheckResponse;
import pe.com.nttdata.empleadofeign.validar.empleado.PlanillaCheckResponse;
import pe.com.nttdata.empleadoqueues.rabbitmq.RabbitMQMessageProducer;
import pe.com.nttdata.empleadofeign.notificacion.NotificacionRequest;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class EmpleadoServiceImpl implements IEmpleadoService {
    private final IEmpleadoDao empleadoDao;
    private  final EmpleadoCheckClient empleadoCheckClient;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;
    public Empleado registrarEmpleado(Empleado empleado) {
        Empleado empleadoResponse =empleadoDao.save(empleado);
        return empleadoResponse;
    }
    public List<Empleado> listarEmpleadoPorFechaingreso(LocalDate fechaIngreso) {
        return empleadoDao.findByFechaIngreso(fechaIngreso);
    }
    @CircuitBreaker(name = "validarempleadoCB", fallbackMethod = "fallValidarempleadoCB")
    @Retry(name = "validarempleadoRetry")
    public String validarEmpleado(Empleado empleado) {
        log.info("Estoy en metodo validarCliente");
        //validar empleado
        EmpleadoCheckResponse empleadoCheckResponse = empleadoCheckClient.validarEmpleado(empleado.getId());
        if (empleadoCheckResponse.esEstafador()) {
            throw new IllegalStateException("Empleado tiene de contacto!!");
        }
        //validar planilla
        PlanillaCheckResponse planillaCheckResponse = empleadoCheckClient.validarPLanilla(empleado.getId(),
                empleado.getCargo(),empleado.getHijos());

        return "OK";
    }
    public String fallValidarempleadoCB(Empleado empleado, Exception e) {
        return "NO_OK";
    }

    public void registrarNotificacion(Empleado empleado) {
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
    }
}
