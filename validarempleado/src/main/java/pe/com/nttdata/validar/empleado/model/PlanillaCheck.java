package pe.com.nttdata.validar.empleado.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PlanillaCheck {

    @Id
    @SequenceGenerator(
            name="planillacheck_id_sequence",
            sequenceName = "planillacheck_id_sequence"
    )
    @GeneratedValue(
            strategy= GenerationType.SEQUENCE,
            generator = "planillacheck_id_sequence"
    )
    private Integer id;
    private Integer empleadoId;
    private Double Sueldo;
    private Double Bonificacion;
    private Date fechaCreacion;

}
