package pe.com.nttdata.empleado.controller;

import java.time.LocalDate;

public record EmpleadoRequest(Integer id, String TipoDocumento,
                              String NroDocumento, String Nombres,
                              String Apellidos, String Cargo, String Telefono,
                              LocalDate FechaIngreso) {
}
