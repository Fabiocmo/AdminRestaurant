package liliyayalovchenko.web.exeptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="You enter date in a wrong format! Try again.")
public class WrongDateInputFormatException extends Exception{

    public WrongDateInputFormatException(String date) {
        super ("WrongDateInputFormatException with wrong date format="+ date);
    }
}
