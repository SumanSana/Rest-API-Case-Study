package telstra.developer.exercise.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telstra.developer.exercise.dao.ExerciseDAO;
import telstra.developer.exercise.entity.CustomerResource;
import telstra.developer.exercise.entity.Discount;
import telstra.developer.exercise.entity.PurchasedProduct;
import telstra.developer.exercise.error.CustomerNotFoundException;

@Service
public class ExerciseServiceImpl implements ExerciseService {
	
	ExerciseDAO exerciseDAO;
	
	@Autowired
	ExerciseServiceImpl(ExerciseDAO exerciseDAO){
		this.exerciseDAO = exerciseDAO;
	}

	@Override
	public CustomerResource getCustomerByUUID(String uuid) {
		
		CustomerResource customer = null;
		Optional<CustomerResource> result = exerciseDAO.findById(uuid);
		if(result.isPresent()) {
			customer=result.get();
			List<Discount> discounts = customer.getCustomerDiscounts();
			for(PurchasedProduct product:customer.getProducts()) {
				discounts.add(product.getDiscountInformation());
			}
			customer.setEligibleDiscounts(discounts);
		}
		else {
			throw new CustomerNotFoundException("Customer not found : "+uuid);
		}
		return customer;
	}

	@Override
	public List<Discount> getDiscountsForUser(String uuid, String productId) {
		
		CustomerResource customer = getCustomerByUUID(uuid);
		List<Discount> discounts = customer.getEligibleDiscounts();
		List<Discount> output;
		if(productId==null) {
			output=discounts;
		}
		else {
			output=discounts.stream().filter(d->d.getProductId()!=null && d.getProductId().equals(productId)).collect(Collectors.toList());
		}
		return output;
	}

}
