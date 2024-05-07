package k3ras.pizzeria.persistence.audit;

import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreRemove;
import k3ras.pizzeria.persistence.entity.PizzaEntity;
import org.springframework.util.SerializationUtils;

public class AuditPizzaListener {

    private PizzaEntity currentValue;

    @PostLoad
    public void onPostLoad(PizzaEntity pizzaEntity) {
        System.out.println("AuditPizzaListener::onPostLoad");
        this.currentValue = SerializationUtils.clone(pizzaEntity);
    }
    
    @PostPersist
    @PostUpdate
    public void onPostPersist(PizzaEntity pizzaEntity) {
        System.out.println("AuditPizzaListener::onPostPersist");
        System.out.println("OLD VALUE: " + this.currentValue);
        System.out.println("NEW VALUE: " + pizzaEntity.toString());
    }

    @PreRemove
    public void onPreDelete(PizzaEntity pizzaEntity) {
        System.out.println("AuditPizzaListener::onPreDelete");
        System.out.println(pizzaEntity.toString());
    }
}
