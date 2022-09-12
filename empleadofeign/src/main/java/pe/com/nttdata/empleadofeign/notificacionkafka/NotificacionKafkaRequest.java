package pe.com.nttdata.empleadofeign.notificacionkafka;

public record NotificacionKafkaRequest(Integer clienteId,String cleinteEmail, String mensaje) {
}
