package pe.edu.cibertec.sw2cl2.producto;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import lombok.Data;
import pe.edu.cibertec.sw2cl2.tienda.Tienda;

@Entity
@Data
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String nombre;
    Integer cantidad;
    String categoria;

    BigDecimal precio;

    @ManyToOne(optional = false)
    Tienda tienda;
}
