package pe.com.nttdata.empleado.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Nacionalidad implements Serializable{

    @Id
    @SequenceGenerator(
            name = "nacionalidad_id_sequence",
            sequenceName = "nacionalidad_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "nacionalidad_id_sequence"
    )
    @Column(name = "nacionalidad_id")
    private int id;

    @Column(name = "nacionalidad_nombre")
    private String nombre;


}