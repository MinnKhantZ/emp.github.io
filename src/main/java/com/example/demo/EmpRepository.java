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
		return jdbcClient.sql("select * from employee")
				.query(Employee.class)
				.list();
	}
	
	Employee findById(int id) {
		return jdbcClient.sql("select * from employee where number = ?")
				.param(id)
				.query(Employee.class)
				.single();
	}
	
	void create(Employee emp) {
		jdbcClient.sql("insert into employee (role, salary) values (?, ?)")
			.params(emp.role(), emp.salary())
			.update();
	}
	
	void update(Employee emp, int id) {
		jdbcClient.sql("update employee set role = ?, salary = ? where number = ?")
			.params(emp.role(), emp.salary(), id)
			.update();
	}
	
	void delete(int id) {
		jdbcClient.sql("delete from employee where number = ?")
			.param(id)
			.update();
		
	}
}
