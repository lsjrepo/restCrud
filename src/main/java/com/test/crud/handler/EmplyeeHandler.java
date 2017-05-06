package com.test.crud.handler;

import com.sun.javafx.collections.MappingChange;
import com.test.crud.dao.DepartmentDao;
import com.test.crud.dao.EmployeeDao;
import com.test.crud.entity.Department;
import com.test.crud.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * Created by lsj on 17-5-1.
 */
@Controller
public class EmplyeeHandler {
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private DepartmentDao departmentDao;
    /**防止新添加的数据覆盖原始的内容*/
    @ModelAttribute
    public  void getEmployee(Integer id,Map<String,Object> map){
        if(id!=null){
            map.put("employee",employeeDao.get(id));
        }
    }
    /**添加用户*/
    @RequestMapping(value ="/emp",method = RequestMethod.POST)
    public String save(Employee employee){
        employeeDao.save(employee);
        return "redirect:/emps";
    }
    /**删除用户*/
    @RequestMapping(value = "/emp/{id}",method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Integer id){
        employeeDao.delete(id);
        return "redirect:/emps";
    }
    /**相应list中超链接的请求*/
    @RequestMapping(value = "emp",method = RequestMethod.GET)
    public String input(Map<String,Object> map){
        map.put("departments",departmentDao.getDepartments());//需要在表单上显示部门信息
        map.put("employee",new Employee());//防止报错

        return "input";
    }
    /**显示所有的用户*/
    @RequestMapping("/emps")
    public String list(Map<String,Object> map){
        map.put("employees",employeeDao.getAll());//放入到map中会自动放到请求域中
        return "list";//数字解析器
    }
    /**添加用户*/
    @RequestMapping(value = "/emp/{id}",method = RequestMethod.GET)
    public String input(@PathVariable("id") Integer id,Map<String,Object> map){
        map.put("employee",employeeDao.get(id));
        map.put("departments",departmentDao.getDepartments());
        return "input";
    }
    /**更新用户*/
    @RequestMapping(value = "/emp",method = RequestMethod.PUT)
    public String update(Employee employee){
        employeeDao.save(employee);
        return "redirect:/emps";
    }

}
