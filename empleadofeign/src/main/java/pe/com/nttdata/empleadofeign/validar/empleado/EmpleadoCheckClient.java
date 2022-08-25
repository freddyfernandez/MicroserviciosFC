package pe.com.nttdata.empleadofeign.validar.empleado;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//para el modulo de validarCliente
//@FeignClient("validarempleado")// viene del nombre del service del properties
@FeignClient(
        name = "validarempleado",
        url = "${empleadofeign.validarempleado.url}"
)
public interface EmpleadoCheckClient {
    @GetMapping(path = "api/v1/empleado-check/{empleadoId}")//viene del la uri del controller
    EmpleadoCheckResponse validarEmpleado(@PathVariable("empleadoId") Integer empleadoId);// viene del check controller
    @GetMapping(path = "api/v1/planilla-check/{empleadoId}/{cargo}/{hijos}")//viene del la uri del controller
    PlanillaCheckResponse validarPlanilla(@PathVariable("empleadoId") Integer empleadoId,
                                          @PathVariable("cargo")String cargo,
                                          @PathVariable("hijos")Integer nHijos);// viene del check controller
}