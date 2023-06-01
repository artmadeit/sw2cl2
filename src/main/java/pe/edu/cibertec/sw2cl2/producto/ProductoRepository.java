package pe.edu.cibertec.sw2cl2.producto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductoRepository extends PagingAndSortingRepository<Producto, Long> {

    Page<ProductoListItemDto> findByTiendaId(Long tiendaId, Pageable pageable);

    Page<ProductoListItemDto> findSummaryBy(Pageable pageable);
}
