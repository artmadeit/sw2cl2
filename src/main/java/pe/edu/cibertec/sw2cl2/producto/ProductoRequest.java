package pe.edu.cibertec.sw2cl2.producto;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import lombok.Data;

@Data
public class ProductoRequest {
    @NotBlank
    String nombre;
    Integer cantidad;
    String categoria;
    @PositiveOrZero
    BigDecimal precio;
    @NotNull
    Long tiendaId;
}
