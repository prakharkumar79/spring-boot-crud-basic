package com.pk.springbootcruddemo.dao;

import com.pk.springbootcruddemo.entity.Employee;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Employee> findAll() {
        Session session = entityManager.unwrap(Session.class);

        List<Employee> employees = session.createQuery("from Employee", Employee.class).getResultList();

        return employees;
    }

    @Override
    public Employee findById(int id) {
        Session session = entityManager.unwrap(Session.class);

        Employee employee = session.get(Employee.class, id);

        return employee;
    }

    @Override
    public void save(Employee employee) {
        Session session = entityManager.unwrap(Session.class);

        session.saveOrUpdate(employee);
    }

    @Override
    public void deleteById(int id) {
        Session session = entityManager.unwrap(Session.class);

        session.createQuery("delete from Employee where id=:id").setParameter("id", id).executeUpdate();
    }
}
