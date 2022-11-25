package telstra.developer.exercise.service;

import java.util.List;

import telstra.developer.exercise.entity.CustomerResource;
import telstra.developer.exercise.entity.Discount;

public interface ExerciseService {
	CustomerResource getCustomerByUUID(String uuid); 
	List<Discount> getDiscountsForUser(String uuid,String productId);
}
