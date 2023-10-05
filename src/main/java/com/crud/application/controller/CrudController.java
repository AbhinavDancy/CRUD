package com.crud.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.crud.application.entity.Employee;
import com.crud.application.service.EmployeeService;

@Controller
public class CrudController {

	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("/")
	public String home(Model model) {
		return pagination(1, model);
	}
	
	@GetMapping("/add_emp")
	public String addEmp(Model model)
	{
		Employee e = new Employee();
		//model.addAttribute("employee", e);
		return "add_emp";
	}
		
	@PostMapping("/insert")
	public String insert(@ModelAttribute Employee employee,Model model)
	{
		model.addAttribute("employee",employeeService.insertEmployee(employee));
		return "redirect:/";
	}
	
	/*
	 * @GetMapping("/edit_emp") public String editEmp(Model model) { Employee e =
	 * new Employee(); //model.addAttribute("employee", e); return "edit_emp"; }
	 */
	
	@GetMapping("/update/{id}")
	public String update(@PathVariable (value ="id") int id , Model model)
	{
		model.addAttribute("employee", employeeService.updateEmployee(id));
		return "edit_emp";
	}
	
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable (value ="id")int id , Model model)
	{
		model.addAttribute("employee",employeeService.deleteEmployee(id));
		return "redirect:/";
	}
	@GetMapping("/pagination/{pageNo}")
	public String pagination(@PathVariable (value = "pageNo") int pageNo, Model model)
	{
		int pageSize =6;
		Page<Employee> pages = employeeService.findPaginated(pageNo, pageSize);
		List<Employee> lists = pages.getContent();
		model.addAttribute("currentPage",pageNo);
		model.addAttribute("listEmployee", lists);
		model.addAttribute("totalPages",pages.getTotalPages());
		model.addAttribute("totalElements",pages.getTotalElements());
		
		return "index";
		
	}
}
