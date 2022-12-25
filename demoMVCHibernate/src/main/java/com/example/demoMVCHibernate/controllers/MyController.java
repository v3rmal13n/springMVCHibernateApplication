package com.example.demoMVCHibernate.controllers;

import com.example.demoMVCHibernate.dao.EmployeeDAO;
import com.example.demoMVCHibernate.entity.Employee;
import com.example.demoMVCHibernate.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MyController {
    @Autowired
    private EmployeeService employeeService;
    //private EmployeeDAO employeeDAO;//так как у нас есть Service который является посредником между контроллером и дао, меняем эту зависимость на зависимость от сервиса

    @GetMapping("/")
    public String showAllEmployees (Model model) {
        List<Employee> allEmployees = employeeService.getAllEmployees();//на объекте дао вызываем метод который переопределен в EmployeeDAOImpl и помещаем это в лист работников

        model.addAttribute("allEmps", allEmployees);//чтобы отобразить всех работников с листа, помещаем его в модель

        return "all-employees.html";
    }
    @GetMapping("/addNewEmployee")
    public String addNewEmployee (Model model) {
        Employee employee = new Employee();//используя но-аргс констркутор, создаем пустого работника

        model.addAttribute("employee", employee); //поместим пустого работника в модель, работник пустой, потому что мы передадим его в форму заполнения и присвоенные значения пойдут в бд

        return "employee-info.html";
    }
    @GetMapping("/saveEmployee")
    public String saveEmployee (@ModelAttribute("employee") Employee employee) {//чтобы получить заполненную модель, пишем аннотация @ModelAttribute и название модели, которую хотим получить

        employeeService.saveEmployee(employee);//сохраняем работника через метод сейв созданного нами в интерфейсе сервис

        return "redirect:/";// должен вернуться старый вью, со всеми работниками, но обновленный, для этого пишем редирект
    }

    @GetMapping("{id}/updateInfo")
    public String updateEmployee (Model model, //для того, что бы отобразить уже ранее заполненного работника, нужно послать в этот метод модель, поэтому в параметре еще прописываем @Model
                                  @PathVariable int id) {//при вызове метода, мы знаем, что получаем id employee, который был прописан в ссылке кнопки, поэтому в параметрах метода мы можем использовать данный id и для этого прописываем @RequestParam

        Employee employee = employeeService.getEmployee(id);  //у нас есть айди работника, давайте возьмем из базы его данные и добавим работника в аттрибут модели, должны в сервисе создать метод getEmployee

        model.addAttribute("employee", employee); //из за того что вью employee-info работает с аттрибутом "employee" этот аттрибут должен называться также, а то вью его не распознает

        return "employee-info.html";//вызывает старую форму для заполнения работника
    }
    @GetMapping("{id}/deleteEmployee")
    public String deleteEmployee (Model model,
                                  @PathVariable int id) {
        employeeService.deleteEmploye(id);

        return "redirect:/";
    }
}
