package com.crud.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.crud.application.dao.EmployeeRepository;
import com.crud.application.entity.Employee;

@Service
public class EmployeeService {
	@Autowired
	EmployeeRepository employeeRepository;
	
	public List<Employee> getAllEmployee()
	{
		System.out.println("We are in Employee Service fetching all employee");
		List<Employee> employee = employeeRepository.findAll();
		return employee;
	}
	  
	public Employee insertEmployee(Employee employee)
	{
		System.out.println("Employee Service creating the employee");
		Employee e = employeeRepository.save(employee);
		return e;
		
	}
	public Employee updateEmployee(int id)
	{
		Optional<Employee > optional =  employeeRepository.findById(id);
		Employee employee =null;
		//System.out.println(employee.toString());
		if(optional.isPresent())
		{
			employee =optional.get();
			System.out.println(employee.toString());
		}
		else
			throw new RuntimeException("Unable to find the id :: "+id);
		return employee;
			
			
	}
	public int deleteEmployee(int id)
	{
		 int status = 0;
		 try {
		employeeRepository.deleteById(id);
		status =1;
		 return status;
		 }catch (Exception e) {
			status =0;
		}
		 return status;
	}
	public Page<Employee> findPaginated(int pageNo,int pageSize)
	{
		PageRequest pageable = PageRequest.of(pageNo-1, pageSize);
		
		return this.employeeRepository.findAll(pageable);
	}

}
