package tacos.data;

import lombok.Data;


import javax.persistence.Entity;

@Data
public class IngredientRef {
    private final String ingredient;
}
