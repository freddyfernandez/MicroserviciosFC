package pe.com.nttdata.validar.empleado.service;

public interface IEmpleadoCheckService {
    public boolean esEmpleadoFraudulento(Integer empleadoId);
    public void esplanillaCheck(Integer empleadoId,String cargo, Integer hijos);
}
