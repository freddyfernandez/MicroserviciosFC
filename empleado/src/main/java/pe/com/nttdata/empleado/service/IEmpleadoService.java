package pe.com.nttdata.empleado.service;

import pe.com.nttdata.empleado.model.Empleado;

import java.time.LocalDate;
import java.util.List;

public interface IEmpleadoService {
    public Empleado registrarEmpleado(Empleado empleado);
    public List<Empleado> listarEmpleadoPorFechaingreso(LocalDate fechaIngreso);
    public String validarEmpleado(Empleado empleado);
    public void registrarNotificacion(Empleado empleado);
}
