package k3ras.pizzeria.service.dto;

import lombok.Data;

@Data
public class UpdatePizzaPriceDto {
    private int pizzaId;
    private double price;
}
