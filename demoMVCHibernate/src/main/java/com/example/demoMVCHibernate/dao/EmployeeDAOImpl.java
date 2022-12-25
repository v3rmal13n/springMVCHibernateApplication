package com.example.demoMVCHibernate.dao;

import com.example.demoMVCHibernate.entity.Employee;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {
    @Autowired//у нас есть готовая фабрика, получим из нее сессию в метод getAllEmployees.  / SessionFactory зависит от DataSource, который мы также прописывали в контексте - дата средства это средства для подключения к бд
    private SessionFactory sessionFactory;

    @Override   //Так же учитывая паттерн BEST PRACTICE мы аннотацию трансактионал уберем и поместим в сервис
    //@Transactional //мы бы могли в ручную создавать транзакцию, закрывать, сохранять с помощью .commit() но здесь, благодаря c3p0 мы можем доверить это спрингу
    public List<Employee> getAllEmployees() {
        Session session = sessionFactory.getCurrentSession();//у нас есть готовый SessionFactory - получим из нее сессию

        //указываем не имя табл, а имя класса
        List<Employee> allEmployees = session.createQuery("from Employee",
                        Employee.class)//получим всех работников из базы
                .getResultList();//возвращает объект сущностей, т.е. весь лист

        return allEmployees;
    }

    @Override
    public void saveEmployee(Employee employee) {
        Session session = sessionFactory.getCurrentSession(); //опять таки нам нужно получить сессию
        //saveOrUpdate проверяет если айди будет не ноль, в случае, если не ноль, он его апдейтит, в случае если ноль, сохраняет нового
        session.saveOrUpdate(employee);//и тут просто сохраняем работника
    }

    @Override
    public Employee getEmployee(int id) {
        Session session = sessionFactory.getCurrentSession();

        Employee employee = session.get(Employee.class, id);

        return employee;
    }

    @Override
    public void deleteEmployee(int id) {
        Session session = sessionFactory.getCurrentSession();

        //техника по удалению работника по его айди                                 :employeeId означает, что вместо этого мы пропишем параметр параметр кстати ниже
        Query<Employee> query = session.createQuery("delete from Employee where id =:employeeId");
        query.setParameter("employeeId", id);//в нашем запросе произойдет замена название параметра на само значение айди
    //параметр мы назвали employeeId и подставляем сам айди в параметр

    //теперь нужно, чтобы запрос сработал, напишем ехекут апдейт . Для делит вызываем всегда ехекутапдейт-ибо этот метод отвечает как за апдейт так и за делит
        query.executeUpdate();
    }
}
