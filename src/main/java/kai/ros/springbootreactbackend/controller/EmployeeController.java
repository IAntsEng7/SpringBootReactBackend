package kai.ros.springbootreactbackend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kai.ros.springbootreactbackend.exception.ResourceNotFoundException;
import kai.ros.springbootreactbackend.model.Employee;
import kai.ros.springbootreactbackend.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000") // For React
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

  @Autowired
  private EmployeeRepo employeeRepo;

  // get all employee
  @GetMapping("/employees")
  public List<Employee> getAllEmployee() {
    return employeeRepo.findAll();
  }

  // create employee rest pai
  @PostMapping("/employees")
  public Employee createEmployee(@RequestBody Employee employee) {
    return employeeRepo.save(employee);
  }

  // get employee bu id rest api
  @GetMapping("/employees/{id}")
  public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
    Employee employee = employeeRepo.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id : " + id));
    return ResponseEntity.ok(employee);
  }

  // update employee rest api
  @PutMapping("/employees/{id}")
  public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,
      @RequestBody Employee employeeDetails) {
    Employee employee = employeeRepo.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id : " + id));

    employee.setFirstName(employeeDetails.getFirstName());
    employee.setLastName(employeeDetails.getLastName());
    employee.setEmailId(employeeDetails.getEmailId());
    Employee updateEmployee = employeeRepo.save(employee);

    return ResponseEntity.ok(updateEmployee);
  }

  // delete employee rest api
  @DeleteMapping("/employees/{id}")
  public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
    Employee employee = employeeRepo.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id : " + id));
    employeeRepo.delete(employee);
    Map<String, Boolean> response = new HashMap<>();
    response.put("delete", Boolean.TRUE);
    return ResponseEntity.ok(response);
  }
}
