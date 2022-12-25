package com.example.demoMVCHibernate.service;

import com.example.demoMVCHibernate.entity.Employee;

import java.util.List;

public interface EmployeeService {//про сервис прочти в тг
    public List<Employee> getAllEmployees();//нет смысла называть метод по другому, не так как он назван в ДАО интерфейсе

    public void saveEmployee(Employee employee);//возвращать метод ничего не будет, а в параметрах будет работник, который должен быть сохранен в бд

    public Employee getEmployee (int id);

    public void deleteEmploye (int id);
}
