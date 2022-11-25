package telstra.developer.exercise.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import telstra.developer.exercise.entity.CustomerResource;
import telstra.developer.exercise.entity.Discount;
import telstra.developer.exercise.service.ExerciseService;

@RestController
@RequestMapping("/rest/v1")
public class ExerciseController {
	
	ExerciseService exerciseServiceImpl;
	
	@Autowired
	ExerciseController(ExerciseService exerciseServiceImpl){
		this.exerciseServiceImpl = exerciseServiceImpl;
	}
	
	@GetMapping("/customers/{theUUID}")
	public CustomerResource getCustomerByUUID(@PathVariable String theUUID) {
		
		return exerciseServiceImpl.getCustomerByUUID(theUUID);		
	}

	
	@GetMapping("/users/{theUUID}/discounts")
	public List<Discount> getDiscountsForUser(@PathVariable String theUUID,@RequestParam(name="productId",required=false) String theProductId) {
		
		return exerciseServiceImpl.getDiscountsForUser(theUUID, theProductId);
		
	}
	
}
