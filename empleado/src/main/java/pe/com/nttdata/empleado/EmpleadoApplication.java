package pe.com.nttdata.empleado;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(
        scanBasePackages = {
                "pe.com.nttdata.empleado", //para comunicar con el modulo
                "pe.com.nttdata.empleadoqueues" // para comunicar con el modulo queues
        }
)
@EnableFeignClients(
        basePackages = "pe.com.nttdata.empleadofeign" //para comunicar con el modulo y paquete
)
public class EmpleadoApplication {

    public static void main(String[] args){
        SpringApplication.run(EmpleadoApplication.class, args);
    }

}