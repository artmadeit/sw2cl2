package pe.edu.cibertec.sw2cl2.producto;

import java.math.BigDecimal;

import lombok.Data;
import pe.edu.cibertec.sw2cl2.tienda.Tienda;

@Data
public class ProductoListItemDto {
    String nombre;
    BigDecimal precio;
    Tienda tienda;
}
