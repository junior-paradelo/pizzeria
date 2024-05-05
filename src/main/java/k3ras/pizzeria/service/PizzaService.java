package k3ras.pizzeria.service;

import k3ras.pizzeria.persistence.entity.PizzaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PizzaService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<PizzaEntity> getAll() {
        System.out.println("1");
        return this.jdbcTemplate.query("SELECT * FROM pizza WHERE available = 1", new BeanPropertyRowMapper<>(PizzaEntity.class));
    }
}