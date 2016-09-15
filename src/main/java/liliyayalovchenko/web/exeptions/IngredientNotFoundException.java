package liliyayalovchenko.web.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Ingredient Not Found By This Id")
public class IngredientNotFoundException extends Exception{
    public IngredientNotFoundException(int id) {
        super ("IngredientNotFoundException with id=" + id);
    }

    public IngredientNotFoundException(String name) {
        super ("IngredientNotFoundException with name=" + name);
    }
}
