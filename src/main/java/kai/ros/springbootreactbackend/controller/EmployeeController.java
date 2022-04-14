package kai.ros.springbootreactbackend.controller;

import java.util.List;
import kai.ros.springbootreactbackend.model.Employee;
import kai.ros.springbootreactbackend.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

  @Autowired
  private EmployeeRepo employeeRepo;

  // get all employee
  @GetMapping("/employees")
  public List<Employee> getAllEmployee(){
    return employeeRepo.findAll();
  }


}
