package pe.com.nttdata.empleado;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication(
        scanBasePackages = {
                "pe.com.nttdata.empleado", //para comunicar con el modulo servicio
                "pe.com.nttdata.empleadoqueues" // para comunicar con el modulo queues
        }
)
@EnableFeignClients(
        basePackages = "pe.com.nttdata.empleadofeign" //para comunicar con el modulo y paquete
)
//agregado para comunicar servicios en un entorno externo
@PropertySources({
        @PropertySource("classpath:empleadofeign-${spring.profiles.active}.properties")//para comunicar con el archivo properties de modulo tipo libreria
})
public class EmpleadoApplication {

    public static void main(String[] args){
        SpringApplication.run(EmpleadoApplication.class, args);
    }

}