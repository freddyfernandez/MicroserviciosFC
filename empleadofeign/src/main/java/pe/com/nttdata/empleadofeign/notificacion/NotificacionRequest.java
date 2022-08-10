package pe.com.nttdata.empleadofeign.notificacion;

public record NotificacionRequest(Integer empleadoId, String telefono, String mensaje) {
}