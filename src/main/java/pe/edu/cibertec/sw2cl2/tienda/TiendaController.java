package pe.edu.cibertec.sw2cl2.tienda;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("tiendas")
@AllArgsConstructor
public class TiendaController {
    TiendaRepository tiendaRepository;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Tienda register(@Valid @RequestBody Tienda tienda) {
        return tiendaRepository.save(tienda);
    }

    @PutMapping("{id}")
    public Tienda edit(@PathVariable Long id, @Valid @RequestBody Tienda tienda) {
        tienda.setId(id);
        return tiendaRepository.save(tienda);
    }

    @GetMapping("{id}")
    public ResponseEntity<Tienda> findById(@PathVariable Long id) {
        return ResponseEntity.of(tiendaRepository.findById(id));
    }

    @GetMapping
    public Page<Tienda> findAll(Pageable pageable) {
        return tiendaRepository.findAll(pageable);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        tiendaRepository.deleteById(id);
    }

}
