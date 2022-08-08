package pe.com.nttdata.notificacion.controller;

public record NotificacionRequest(Integer empleadoId, String telefono, String mensaje) {
}