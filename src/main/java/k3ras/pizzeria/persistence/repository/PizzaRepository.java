package k3ras.pizzeria.persistence.repository;

import k3ras.pizzeria.persistence.entity.PizzaEntity;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer> {
}
