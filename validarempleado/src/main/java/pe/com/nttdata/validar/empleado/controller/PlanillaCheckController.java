package pe.com.nttdata.validar.empleado.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.com.nttdata.empleadofeign.validar.empleado.EmpleadoCheckResponse;
import pe.com.nttdata.empleadofeign.validar.empleado.PlanillaCheckResponse;
import pe.com.nttdata.validar.empleado.service.IEmpleadoCheckService;

@Slf4j
@RestController
@RequestMapping("api/v1/planilla-check")
@AllArgsConstructor
public class PlanillaCheckController {
    private  final IEmpleadoCheckService planillaCheckService;

    @GetMapping(path = "{empleadoId}/{cargo}/{hijos}")
    public PlanillaCheckResponse validarPlanilla(@PathVariable("empleadoId") Integer empleadoId,
                                                               @PathVariable("cargo") String cargo,
                                                               @PathVariable("hijos") Integer hijos){
        planillaCheckService.esplanillaCheck(empleadoId,cargo,hijos);
        log.info("validacion para empleado: {}",empleadoId);
        return new PlanillaCheckResponse();
    }
}
