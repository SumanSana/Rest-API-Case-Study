package telstra.developer.exercise.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



@ControllerAdvice
public class ExerciseEceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<ErrorResource> handleException(CustomerNotFoundException exc) {
		
		
		ErrorResource error = new ErrorResource("ER001",exc.getMessage());
				
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
}
