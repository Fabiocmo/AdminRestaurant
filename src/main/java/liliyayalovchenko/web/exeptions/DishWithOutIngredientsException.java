package liliyayalovchenko.web.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="No one ingredient was chosen for dish")
public class DishWithOutIngredientsException extends Throwable {

    public DishWithOutIngredientsException() {
        super("DishWithOutIngredientsException cant create dish without ingredients");
    }
}
