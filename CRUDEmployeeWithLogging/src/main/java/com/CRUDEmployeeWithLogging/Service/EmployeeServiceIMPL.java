package com.CRUDEmployeeWithLogging.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CRUDEmployeeWithLogging.DAO.EmployeeDaoInter;
import com.CRUDEmployeeWithLogging.Model.EmployeeDataModel;

/**
 * @author Sonu Kumar Gupta
 *
 */
@Service
public class EmployeeServiceIMPL implements EmployeeServiceInter{

	
	@Autowired
	private EmployeeDaoInter employeeDao;

	@Override
	public void save(EmployeeDataModel employee) {
		employeeDao.save(employee);
		
	}

	@Override
	public List<EmployeeDataModel> getAllEmployees() {
		return employeeDao.getAllEmployees();
	}

	@Override
	public EmployeeDataModel getEmployeeById(long empid) {
		return employeeDao.getEmployeeById(empid);
	}

	@Override
	public void updateEmployee(EmployeeDataModel employee) {
		employeeDao.updateEmployee(employee);
	}

	@Override
	public EmployeeDataModel DeleteEmployeeById(long empid) {
		return employeeDao.DeleteEmployeeById(empid);
	}
}
