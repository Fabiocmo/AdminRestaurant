package liliyayalovchenko.web.controllers.ExeptionHendler;

import liliyayalovchenko.web.exeptions.DishNotFoundException;
import liliyayalovchenko.web.exeptions.DishWithOutIngredientsException;
import liliyayalovchenko.web.exeptions.EmployeeNotFoundException;
import liliyayalovchenko.web.exeptions.WrongDateInputFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ModelAndView handleEmployeeNotFoundException(HttpServletRequest request, Exception ex){
        LOGGER.error("Requested URL="+request.getRequestURL());
        LOGGER.error("Exception Raised="+ex);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", ex);
        modelAndView.addObject("url", request.getRequestURL());

        modelAndView.setViewName("404");
        return modelAndView;
    }

    @ExceptionHandler(DishNotFoundException.class)
    public ModelAndView handleDishNotFoundException(HttpServletRequest request, Exception ex){
        LOGGER.error("Requested URL="+request.getRequestURL());
        LOGGER.error("Exception Raised="+ex);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", ex);
        modelAndView.addObject("url", request.getRequestURL());

        modelAndView.setViewName("404");
        return modelAndView;
    }

    @ExceptionHandler(DishWithOutIngredientsException.class)
    public ModelAndView handleDishWithOutIngredientsException(HttpServletRequest request, Exception ex){
        LOGGER.error("Requested URL="+request.getRequestURL());
        LOGGER.error("Exception Raised="+ex);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", ex);
        modelAndView.addObject("url", request.getRequestURL());

        modelAndView.setViewName("wrongInput");
        return modelAndView;
    }

    @ExceptionHandler(DishWithOutIngredientsException.class)
    public ModelAndView handleMenuNotFoundException(HttpServletRequest request, Exception ex){
        LOGGER.error("Requested URL="+request.getRequestURL());
        LOGGER.error("Exception Raised="+ex);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", ex);
        modelAndView.addObject("url", request.getRequestURL());

        modelAndView.setViewName("404");
        return modelAndView;
    }

    @ExceptionHandler(WrongDateInputFormatException.class)
    public ModelAndView handleWrongDateInputFormatException(HttpServletRequest request, Exception ex){
        LOGGER.error("Requested URL="+request.getRequestURL());
        LOGGER.error("Exception Raised="+ex);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", ex);
        modelAndView.addObject("url", request.getRequestURL());
        modelAndView.setViewName("wrongInput");
        return modelAndView;
    }
}
