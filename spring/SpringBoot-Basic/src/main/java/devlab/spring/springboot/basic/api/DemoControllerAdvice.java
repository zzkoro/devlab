package devlab.spring.springboot.basic.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

/**
 * 
 * @author asus
 *
 */
@ControllerAdvice
public class DemoControllerAdvice {

	@ExceptionHandler(value = { MethodArgumentNotValidException.class }) 
	//@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) 
	@ResponseBody
	Map<?,?> handleValidError(Exception ex, WebRequest request) {
		Map<String, String> error = new HashMap<String, String>();
		error.put("errNo", "11111");
		error.put("errMsg",  ex.getMessage());
		
		return error;
	}
}

