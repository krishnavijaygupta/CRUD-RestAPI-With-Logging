package com.CRUDEmployeeWithLogging.DAO;

import java.util.List;

import com.CRUDEmployeeWithLogging.Model.EmployeeDataModel;

public interface EmployeeDaoInter {

	void save(EmployeeDataModel employee);

	List<EmployeeDataModel> getAllEmployees();

	void updateEmployee(EmployeeDataModel employee);

	EmployeeDataModel getEmployeeById(long empid);

	EmployeeDataModel DeleteEmployeeById(long empid);

}
