package com.CRUDEmployeeWithLogging.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.CRUDEmployeeWithLogging.Model.EmployeeDataModel;
import com.CRUDEmployeeWithLogging.Service.EmployeeServiceInter;

@RestController

/**
 * @author Sonu Kumar Gupta
 *
 */
public class EmployeeDataController {
	
	@Autowired
	private EmployeeServiceInter employeeService ; 
	
	private static final Logger log = LoggerFactory.getLogger(EmployeeDataController.class);

	
	@PostMapping("/addEmployee")
	public EmployeeDataModel addEmployee(@RequestBody EmployeeDataModel employee) {
		employeeService.save(employee);
		log.info("Employee Added successfully In DataBase");
		return employee;

	}
	
	@GetMapping("/getEmployeesList")
	public ResponseEntity<List<EmployeeDataModel>> getAllEmployees() {
		List<EmployeeDataModel> list = employeeService.getAllEmployees();
		log.info("All Employee Fatched succesfully From DataBase",list);
		return new ResponseEntity<List<EmployeeDataModel>>(list, new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/employee{empid}")
	public ResponseEntity<EmployeeDataModel> getEmployeeById(@PathVariable("empid") long empid) {
		EmployeeDataModel employee = employeeService.getEmployeeById(empid);
		log.info("Employee With Id ",employee.getEmpid());
		return new ResponseEntity<EmployeeDataModel>(employee, new HttpHeaders(), HttpStatus.OK);
	}
	
	@PutMapping("/update{empid}")
	public EmployeeDataModel updateEmployee(@RequestBody EmployeeDataModel employee , @PathVariable("empid") long empid) {
		
		EmployeeDataModel employeeData = employeeService.getEmployeeById(empid);
		log.info("Employee With Id ",employee.getEmpid());
		employee.setFirstName(employeeData.getFirstName());
		employee.setLastName(employeeData.getLastName());
		employee.setEmail(employeeData.getEmail());
		employee.setAge(employeeData.getAge());
		employee.setGender(employeeData.getGender());
		employee.setAddress(employeeData.getAddress());
		employeeService.updateEmployee(employee);
		log.info("Employee Data Updated succesfully",employee.getEmpid());
		return employee;

	}
	
	@DeleteMapping(value = "/employee{empid}")
	public ResponseEntity<EmployeeDataModel> DeleteEmployeeById(@PathVariable("empid") long empid) {
		EmployeeDataModel employee = employeeService.DeleteEmployeeById(empid);
		log.info("Employee Deleted ",employee.getEmpid());
		return new ResponseEntity<EmployeeDataModel>(employee, new HttpHeaders(), HttpStatus.OK);
	}
}
