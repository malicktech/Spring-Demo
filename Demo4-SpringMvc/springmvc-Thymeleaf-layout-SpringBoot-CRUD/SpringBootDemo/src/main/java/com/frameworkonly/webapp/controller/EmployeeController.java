package com.frameworkonly.webapp.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.frameworkonly.webapp.domain.Employee;
import com.frameworkonly.webapp.service.EmployeeService;


@Controller
// @ComponentScan
public class EmployeeController {

	@Autowired
	private EmployeeService employeeServiceInterface;
	
	private static final String INDEX_VIEW_NAME = "index";
	private static final String EDITPAGE_VIEW_NAME = "editPage";
	
	@RequestMapping(value = {"/","index","/savepage"}, method = RequestMethod.GET)
	public String savePage(Model model) {
		model.addAttribute("employee", new Employee());
		model.addAttribute("allEmployees", (ArrayList<Employee>)employeeServiceInterface.getAllEmployees());
		return INDEX_VIEW_NAME;
	}
	
	/**
	 * Save Employee
	 * 
	 * @param employee
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = {"/employee/save"}, method = RequestMethod.POST)
	public String saveEmployee(@ModelAttribute("employee") @Valid Employee employee,BindingResult bindingResult,
			final RedirectAttributes redirectAttributes, Model model) {
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("allEmployees", (ArrayList<Employee>)employeeServiceInterface.getAllEmployees());
			return "index";
		}
		if(employeeServiceInterface.saveEmployee(employee)!=null) {
			redirectAttributes.addFlashAttribute("saveEmployee", "success");
		} else {
			redirectAttributes.addFlashAttribute("saveEmployee", "unsuccess");
		}
		
		return "redirect:/savepage";
	}
	
	/**
	 * DELETE or EDIT employee
	 * 
	 * @param operation
	 * @param empId
	 * @param redirectAttributes
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/employee/{operation}/{empId}", method = RequestMethod.GET)
	public String editRemoveEmployee(@PathVariable("operation") String operation,
			@PathVariable("empId") Long empId, final RedirectAttributes redirectAttributes,
			Model model) {
		if(operation.equals("delete")) {
			if(employeeServiceInterface.deleteEmployee(empId)) {
				redirectAttributes.addFlashAttribute("deletion", "success");
			} else {
				redirectAttributes.addFlashAttribute("deletion", "unsuccess");
			}
		} else if(operation.equals("edit")){
		  Employee editEmployee = employeeServiceInterface.findEmployee(empId);
		  if(editEmployee!=null) {
		       model.addAttribute("editEmployee", editEmployee);
		       return EDITPAGE_VIEW_NAME;
		  } else {
			  redirectAttributes.addFlashAttribute("status","notfound");
		  }
		}
		
		return "redirect:/savepage";
	}
	
	/**
	 * Update Employee
	 * 
	 * @param editEmployee
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "/employee/update", method = RequestMethod.POST)
	public String updateEmployee(@ModelAttribute("editEmployee") Employee editEmployee,
			final RedirectAttributes redirectAttributes) {
		if(employeeServiceInterface.editEmployee(editEmployee)!=null) {
			redirectAttributes.addFlashAttribute("edit", "success");
		} else {
			redirectAttributes.addFlashAttribute("edit", "unsuccess");
		}
		return "redirect:/savepage";
	}
}
