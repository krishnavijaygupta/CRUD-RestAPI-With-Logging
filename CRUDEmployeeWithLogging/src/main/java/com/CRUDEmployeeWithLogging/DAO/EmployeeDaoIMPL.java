package com.CRUDEmployeeWithLogging.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.CRUDEmployeeWithLogging.Model.EmployeeDataModel;

/**
 * @author Sonu Kumar Gupta
 *
 */
@Repository
@Transactional
public class EmployeeDaoIMPL implements EmployeeDaoInter{
	
	 @PersistenceContext
	  private EntityManager entityManager;
	
	@Autowired
	private SessionFactory sessionFactory;
	

	protected Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public void save(EmployeeDataModel employee) {
		getSession().saveOrUpdate(employee);
		getSession().flush();
	}

	@Override
	public List<EmployeeDataModel> getAllEmployees() {
		List<EmployeeDataModel> employee = getSession().createQuery("FROM employees",EmployeeDataModel.class).getResultList();
		getSession().flush();
		return employee;
	}

	@Override
	public EmployeeDataModel getEmployeeById(long empid) {
		EmployeeDataModel employee = getSession().get(EmployeeDataModel.class, empid);
		getSession().flush();
		return employee;
	}
	
	@Override
	public void updateEmployee(EmployeeDataModel employee) {
		getSession().saveOrUpdate(employee);
		getSession().flush();
		
	}

	

	@Override
	public EmployeeDataModel DeleteEmployeeById(long empid) {
		EmployeeDataModel employee = getSession().get(EmployeeDataModel.class, empid);
		getSession().delete(employee);
		getSession().flush();
		return employee;
	}

}
