package telstra.developer.exercise.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import telstra.developer.exercise.entity.CustomerResource;

public interface ExerciseDAO  extends JpaRepository<CustomerResource,String>{
	
}
