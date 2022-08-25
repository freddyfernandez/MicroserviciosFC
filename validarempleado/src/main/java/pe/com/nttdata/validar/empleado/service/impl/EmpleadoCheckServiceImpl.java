package pe.com.nttdata.validar.empleado.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.nttdata.validar.empleado.dao.IEmpleadoCheckDao;
import pe.com.nttdata.validar.empleado.dao.IEmpleadoPlanillaDao;
import pe.com.nttdata.validar.empleado.model.EmpleadoCheck;
import pe.com.nttdata.validar.empleado.model.PlanillaCheck;
import pe.com.nttdata.validar.empleado.service.IEmpleadoCheckService;

import java.util.Date;

@Service
@AllArgsConstructor
public class EmpleadoCheckServiceImpl implements IEmpleadoCheckService {
    private final IEmpleadoCheckDao empleadoCheckDao;
    private final IEmpleadoPlanillaDao empleadoPlanillaDao;
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
    public void esplanillaCheck(Integer empleadoId,String cargo, Integer hijos){
        Double sueldo=0.0, bonificacion=0.0;
        switch(cargo) {
            case "Vendedor":
                sueldo=1200.0;
                break;
            case "Administrador":
                sueldo=1500.0;
                break;
            default:
                sueldo=1000.0;
        }
        if(hijos>0){
            if(hijos==1){bonificacion=100.0;}
            if(hijos==2){bonificacion=200.0;}
            if(hijos>=3){bonificacion=300.0;}
        }
        empleadoPlanillaDao.save(
                PlanillaCheck.builder()
                        .empleadoId(empleadoId)
                        .Sueldo(sueldo)
                        .Bonificacion(bonificacion)
                        .fechaCreacion(new Date())
                        .build()
        );
    }
}
