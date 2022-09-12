package pe.com.nttdata.empleadofeign.notificacionkafka;

public record NotificacionKafkaRequest(Integer empleadoId,String telefono, String mensaje) {
}
