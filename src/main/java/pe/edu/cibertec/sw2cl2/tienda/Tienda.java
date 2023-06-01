package pe.edu.cibertec.sw2cl2.tienda;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Entity
@Data
public class Tienda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotBlank
    String nombre;
    @Pattern(regexp = "\\d{11}")
    String ruc;

}
