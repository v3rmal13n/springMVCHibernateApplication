package com.example.demoMVCHibernate.service;

import com.example.demoMVCHibernate.dao.EmployeeDAO;
import com.example.demoMVCHibernate.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeDAO employeeDAO;//для того, чтобы вызывать метод из ДАОИмпл, нужно прописать зависимость от ДАО

    @Override
    @Transactional//мы бы могли в ручную создавать транзакцию, закрывать, сохранять с помощью .commit() но здесь, благодаря c3p0 мы можем доверить это спрингу
    public List<Employee> getAllEmployees() {
        return employeeDAO.getAllEmployees();
    }//в сервисе мы просто будем вызывать метод getAllEmployees() который был описан в ДАОИмпл

    @Override
    @Transactional
    public void saveEmployee(Employee employee) {
        employeeDAO.saveEmployee(employee);
    }

    @Override
    @Transactional
    public Employee getEmployee(int id) {
        return employeeDAO.getEmployee(id);
    }

    @Override
    @Transactional
    public void deleteEmploye(int id) {
        employeeDAO.deleteEmployee(id);
    }
}
