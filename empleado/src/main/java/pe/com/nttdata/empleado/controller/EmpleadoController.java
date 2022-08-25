package pe.com.nttdata.empleado.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.nttdata.empleado.model.Empleado;
import pe.com.nttdata.empleado.service.IEmpleadoService;

import javax.validation.Valid;
import java.time.LocalDate;

@Slf4j
@RestController
@RequestMapping("api/v1/empleado")
@AllArgsConstructor
public class EmpleadoController {
    private  final IEmpleadoService empleadoService;

    @PostMapping
    public ResponseEntity<?>registrarEmpleado(@Valid @RequestBody Empleado empleado){
        log.info("nuevo registro de empleado {} ", empleado);
        Empleado newEmpleado =empleadoService.registrarEmpleado(empleado);
        String resultado = empleadoService.validarEmpleado(newEmpleado);
        log.info("Resultado: {}", resultado);
        if (resultado.equals("OK")){
            empleadoService.registrarNotificacion(newEmpleado);
            return new ResponseEntity<EmpleadoRequest>(new EmpleadoRequest(newEmpleado.getId(),
                    empleado.getTipoDocumento(),
                    empleado.getNroDocumento(),
                    empleado.getNombres(),
                    empleado.getApellidos(),
                    empleado.getCargo(),
                    empleado.getTelefono(),
                    empleado.getHijos(),
                    empleado.getFechaIngreso()),HttpStatus.OK);
        }
        return new ResponseEntity("Servicio validarEmpleado no disponible", HttpStatus.OK);

    }

    @GetMapping(params ="fechaIngreso")
    public ResponseEntity<?> obtenerEmpleadoPorFechaIngreso(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaIngreso) {
        log.info("obtener empleado: ", fechaIngreso);
        return new ResponseEntity<>(empleadoService.listarEmpleadoPorFechaingreso(fechaIngreso), HttpStatus.OK);
    }


}
