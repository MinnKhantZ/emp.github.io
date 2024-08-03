package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.List;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/api/emps")
public class EmpController {
	
	final EmpRepository empRepo;
	
	public EmpController(EmpRepository empRepo) {
		this.empRepo = empRepo;
	}
	
	@GetMapping("")
	List<Employee> findAll() {
		return empRepo.findAll();
	}
	
	@GetMapping("/{id}")
	Employee findById(@PathVariable int id) {
		return empRepo.findById(id);
	}
	
	@PostMapping("")
	void create(@RequestBody Employee emp) {
		empRepo.create(emp);
	}
	
	@PutMapping("{id}")
	void update(@RequestBody Employee emp,@PathVariable int id) {
		empRepo.update(emp, id);
	}
	
	@DeleteMapping("{id}")
	void delete(@PathVariable int id) {
		empRepo.delete(id);
	}
}
