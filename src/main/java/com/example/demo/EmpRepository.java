package com.example.demo;

import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.simple.JdbcClient;
import java.util.List;

@Repository
public class EmpRepository {
	
	private final JdbcClient jdbcClient;
	
	public EmpRepository(JdbcClient jdbcClient) {
		this.jdbcClient = jdbcClient;
	}
	
	public List<Employee> findAll() {
		return jdbcClient.sql("SELECT * FROM employee")
				.query(Employee.class)
				.list();
	}
	
	Employee findById(int id) {
		return jdbcClient.sql("SELECT * FROM employee WHERE number = ?")
				.param(id)
				.query(Employee.class)
				.single();
	}
	
	void create(Employee emp) {
		jdbcClient.sql("INSERT INTO employee (role, salary) VALUES (?, ?)")
			.params(emp.role(), emp.salary())
			.update();
	}
	
	void update(Employee emp, int id) {
		jdbcClient.sql("UPDATE employee SET role = ?, salary = ? WHERE number = ?")
			.params(emp.role(), emp.salary(), id)
			.update();
	}
	
	void delete(int id) {
		jdbcClient.sql("DELETE FROM employee WHERE number = ?")
			.param(id)
			.update();
		
	}
}
