package pe.com.nttdata.empleado.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Empleado {
    @Id
    @SequenceGenerator(
            name = "empleado_id_sequence",
            sequenceName = "empleado_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "empleado_id_sequence"
    )
    @Column(name = "empleado_id")
    private Integer id;

    @NotEmpty(message = "{NotEmpty.empleado.tipoDocumento}")
    @Pattern(regexp="[a-zA-Z]{2,20}",message = "{Pattern.empleado.tipoDocumento}")
    @Column(name = "empleado_tipo_documento")
    private String tipoDocumento;

    @NotEmpty(message = "{NotEmpty.empleado.nroDocumento}")
    @Pattern(regexp="\\d{8,11}",message = "{Pattern.empleado.nroDocumento}")
    @Column(name = "empleado_numero_documento")
    private String nroDocumento;

    @NotEmpty(message = "{NotEmpty.empleado.nombres}")
    @Pattern(regexp="[a-zA-Z]{2,20}",message = "{Pattern.empleado.nombres}")
    @Column(name = "empleado_nombres")
    private String nombres;

    @NotEmpty(message = "{NotEmpty.empleado.apellidos}")
    @Pattern(regexp="[a-zA-Z]{4,60}",message = "{Pattern.empleado.apellidos}")
    @Column(name = "empleado_apellidos")
    private String apellidos;

    @NotEmpty(message = "{NotEmpty.empleado.cargo}")
    @Pattern(regexp="[a-zA-Z]{2,20}",message = "{Pattern.empleado.cargo}")
    @Column(name = "empleado_cargo")
    private String cargo;

    @NotEmpty(message = "{NotEmpty.empleado.telefono}")
    @Pattern(regexp="\\d{9}",message = "{Pattern.empleado.telefono}")
    @Column(name = "empleado_telefono")
    private String telefono;

    @ManyToOne
    @JoinColumn(name = "nacionalidad_id", nullable = false)
    @NotNull(message = "{NotNull.empleado.nacionalidad}")
    private Nacionalidad nacionalidad;

    @Column(name = "empleado_fecha_ingreso")
    private LocalDate fechaIngreso;
}
