package pe.com.nttdata.validar.empleado.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.nttdata.validar.empleado.dao.IEmpleadoCheckDao;
import pe.com.nttdata.validar.empleado.model.EmpleadoCheck;
import pe.com.nttdata.validar.empleado.service.IEmpleadoCheckService;

import java.util.Date;

@Service
@AllArgsConstructor
public class EmpleadoCheckServiceImpl implements IEmpleadoCheckService {
    private final IEmpleadoCheckDao empleadoCheckDao;
    public boolean esEmpleadoFraudulento(Integer clienteId){
        empleadoCheckDao.save(
                EmpleadoCheck.builder()
                        .empleadoId(clienteId)
                        .esEstafador(false)
                        .fechaCreacion(new Date())
                        .build()
        );
        return false;
    }
}
