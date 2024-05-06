package k3ras.pizzeria.persistence.repository;

import k3ras.pizzeria.persistence.entity.PizzaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaPagSortRepository extends ListPagingAndSortingRepository<PizzaEntity, Integer> {
    Page<PizzaEntity> findByAvailableTrue(Pageable pageable);
}
