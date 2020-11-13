package com.CRUDEmployeeWithLogging.Service;

import java.util.List;

import com.CRUDEmployeeWithLogging.Model.EmployeeDataModel;

public interface EmployeeServiceInter {

	void save(EmployeeDataModel employee);

	List<EmployeeDataModel> getAllEmployees();

	EmployeeDataModel getEmployeeById(long empid);
	
	void updateEmployee(EmployeeDataModel employee);

	EmployeeDataModel DeleteEmployeeById(long empid);

	

}
