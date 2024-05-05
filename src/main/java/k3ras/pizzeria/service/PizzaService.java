package k3ras.pizzeria.service;

import k3ras.pizzeria.persistence.entity.PizzaEntity;
import k3ras.pizzeria.persistence.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {
    private final PizzaRepository pizzaRepository;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    public List<PizzaEntity> getAll() {
        return this.pizzaRepository.findAll();
    }

    public PizzaEntity getById(Integer id) {
        return this.pizzaRepository.findById(id).orElse(null);
    }

    public PizzaEntity save(PizzaEntity pizzaEntity) {
        return this.pizzaRepository.save(pizzaEntity);
    }

    public boolean existsById(Integer id) {
        return this.pizzaRepository.existsById(id);
    }
}