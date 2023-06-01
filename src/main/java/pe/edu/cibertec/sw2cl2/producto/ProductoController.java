package pe.edu.cibertec.sw2cl2.producto;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.AllArgsConstructor;
import pe.edu.cibertec.sw2cl2.tienda.TiendaRepository;

@RestController
@RequestMapping("productos")
@AllArgsConstructor
public class ProductoController {
    ProductoRepository productoRepository;
    TiendaRepository tiendaRepository;

    // @PostMapping
    // public ResponseEntity<?> register(@RequestBody Producto producto) {
    // if (producto.getTienda() == null || producto.getTienda().getId() == null) {
    // return ResponseEntity.badRequest().body("Id no valido");
    // }

    // Producto productoCreado = productoRepository.save(producto);
    // URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
    // .buildAndExpand(productoCreado.getId()).toUri();

    // return ResponseEntity.created(location).body(productoCreado);
    // }

    @PostMapping
    public Producto register(@Valid @RequestBody ProductoRequest productoRequest) {
        // if (producto.getTienda() == null || producto.getTienda().getId() == null)
        // throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id no valido");
        // }

        Producto producto = new Producto();
        producto.setNombre(productoRequest.getNombre());
        producto.setCantidad(productoRequest.getCantidad());
        producto.setPrecio(productoRequest.getPrecio());
        producto.setCategoria(productoRequest.getCategoria());
        producto.setTienda(tiendaRepository.findById(productoRequest.getTiendaId())
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tienda no     existe")));

        return productoRepository.save(producto);
    }

    @PutMapping("{id}")
    public Producto edit(@PathVariable Long id, @RequestBody Producto producto) {
        producto.setId(id);
        return productoRepository.save(producto);
    }

    @GetMapping("{id}")
    public ResponseEntity<Producto> findById(@PathVariable Long id) {
        return ResponseEntity.of(productoRepository.findById(id));
    }

    @GetMapping
    public Page<ProductoListItemDto> findAll(Pageable pageable, Long tiendaId) {
        if (tiendaId != null) {
            return productoRepository.findByTiendaId(tiendaId, pageable);
        } else {
            return productoRepository.findSummaryBy(pageable);

            // return productoRepository.findAll(pageable).map(producto -> {
            // var dto = new ProductoListItemDto();
            // dto.setNombre(producto.getNombre());
            // dto.setPrecio(producto.getPrecio());
            // dto.setTienda(producto.getTienda());
            // return dto;
            // });
        }
    }

    @DeleteMapping("{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        productoRepository.deleteById(id);
    }

}
