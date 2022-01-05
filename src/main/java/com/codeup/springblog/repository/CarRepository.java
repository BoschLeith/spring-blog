package com.codeup.springblog.repository;

import com.codeup.springblog.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

	Car findCarByMake(String make);
	Car findCarByModel(String model);
	List<Car> findAllByMake(String make);

}
