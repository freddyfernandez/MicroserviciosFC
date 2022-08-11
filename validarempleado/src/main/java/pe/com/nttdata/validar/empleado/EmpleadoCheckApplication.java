package pe.com.nttdata.validar.empleado;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//referenciando a el modulo tipo libreria
@EnableFeignClients(
        basePackages = "pe.com.nttdata.empleadofeign"
)
public class EmpleadoCheckApplication {
    public static void main(String[] args) {

        SpringApplication.run(EmpleadoCheckApplication.class,args);
    }
}
