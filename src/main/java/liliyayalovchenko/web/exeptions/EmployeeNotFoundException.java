package liliyayalovchenko.web.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Employee Not Found By This Id")
public class EmployeeNotFoundException extends Exception {

    public EmployeeNotFoundException(int id){
        super ("EmployeeNotFoundException with id="+id);
    }
}
