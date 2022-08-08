package pe.com.nttdata.empleadofeign.validar.empleado;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//para el modulo de validarCliente
@FeignClient("validarempleado")// viene del nombre del service del properties
public interface EmpleadoCheckClient {
    @GetMapping(path = "api/v1/empleado-check/{empleadoId}")//viene del la uri del controller
    EmpleadoCheckResponse validarEmpleado(@PathVariable("empleadoId") Integer empleadoId);// viene del check controller
}