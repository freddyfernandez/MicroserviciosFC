package pe.com.nttdata.validar.empleado.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.nttdata.validar.empleado.model.PlanillaCheck;

public interface IEmpleadoPlanillaDao extends JpaRepository<PlanillaCheck,Integer> {

}
