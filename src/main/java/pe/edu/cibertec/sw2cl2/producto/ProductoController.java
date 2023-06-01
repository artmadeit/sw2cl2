package pe.edu.cibertec.sw2cl2.producto;

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

import lombok.AllArgsConstructor;
import pe.edu.cibertec.sw2cl2.tienda.TiendaRepository;

@RestController
@RequestMapping("productos")
@AllArgsConstructor
public class ProductoController {
    ProductoRepository productoRepository;
    TiendaRepository tiendaRepository;

    // Existen al menos 3 maneras de verificar el id de la tienda

    // 1. usando una entidad como RequestBody y lanzando una excepcion

    // @PostMapping
    // public ResponseEntity<?> register(@RequestBody @Valid Producto producto) {
    // if (producto.getTienda() == null || producto.getTienda().getId() == null)
    // throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id no valido");
    // }

    // Producto productoCreado = productoRepository.save(producto);
    // URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
    // .buildAndExpand(productoCreado.getId()).toUri();

    // return ResponseEntity.created(location).body(productoCreado);
    // }

    // 2. usando una entidad como RequestBody y usando response entitiy
    // @PostMapping
    // public ResponseEntity<?> register(@RequestBody @Valid Producto producto) {
    // Long tiendaId =
    // if (producto.getTienda() == null || producto.getTienda().getId() == null) {
    // return ResponseEntity.badRequest().body("Id no valido");
    // }
    // Producto productoCreado = productoRepository.save(producto);
    // return ResponseEntity.created(null).body(productoCreado);
    // }

    // 3. en vez de usar una entity, usar un dto como RequestBody
    @PostMapping
    public Producto register(@Valid @RequestBody ProductoRequest productoRequest) {
        Producto producto = new Producto();
        producto.setNombre(productoRequest.getNombre());
        producto.setCantidad(productoRequest.getCantidad());
        producto.setPrecio(productoRequest.getPrecio());
        producto.setCategoria(productoRequest.getCategoria());
        producto.setTienda(tiendaRepository.findById(productoRequest.getTiendaId())
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tienda no existe")));

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
        // Existen 2 maneras usando DTOs o projections (vea
        // https://github.com/artmadeit/rest-demo/tree/class11#proyecciones-rest)

        // note "NO" estamos haciendo REST projections (si quiere agregue el isMobile
        // como vimos en clase)

        // 1. Usando Spring data projections (recomendada)
        if (tiendaId != null) {
            return productoRepository.findByTiendaId(tiendaId, pageable);
        } else {
            return productoRepository.findSummaryBy(pageable);
        }

        // 2. Usando DTO
        // return productoRepository.findByTiendaId(pageable).map(producto -> {
        // var dto = new ProductoListItemDto();
        // dto.setNombre(producto.getNombre());
        // dto.setPrecio(producto.getPrecio());
        // dto.setTienda(producto.getTienda());
        // return dto;
        // });
    }

    @DeleteMapping("{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        productoRepository.deleteById(id);
    }

}
