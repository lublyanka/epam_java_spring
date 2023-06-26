package com.epam.rd.autotasks.springemployeecatalog;

import com.epam.rd.autotasks.springemployeecatalog.repository.EmployeeDA;
import com.epam.rd.autotasks.springemployeecatalog.domain.Employee;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeesController {

    @GetMapping(value = "")
    ResponseEntity<String> getEmployeesList(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                            @RequestParam(value = "size", required = false) Integer size,
                                            @RequestParam(value = "sort", required = false, defaultValue = "") String sort) {

        return new ResponseEntity<>(DBManager.getInstance()
                .execute(connection -> EmployeeDA.getEmployees(connection, page, size, sort))
                .stream().map(x -> getStringWrapped(Employee.Parser.toJson(x)))
                .collect(Collectors.joining(",", "[", "]")), HttpStatus.OK);
    }

    @GetMapping("/{employee_id}")
    public ResponseEntity<String> getEmployee(@PathVariable("employee_id") Integer employeeId,
                                              @RequestParam(value = "full_chain", defaultValue = "false") Boolean isFullChain) {

        if (employeeId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Employee employee = DBManager.getInstance().execute((con) -> EmployeeDA.getEmployeeByID(con, employeeId, isFullChain));
        /*if (employee == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else*/
            return new ResponseEntity<>(getStringWrapped(Employee.Parser.toJson(employee)), HttpStatus.OK);
    }

    @GetMapping("/by_manager/{managerId}")
    public ResponseEntity<String> getEmployeeByManager(@PathVariable("managerId") Integer managerId,
                                                       @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                                       @RequestParam(value = "size", required = false) Integer size,
                                                       @RequestParam(value = "sort", required = false, defaultValue = "") String sort) {
        if (managerId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<Employee> employeeList = DBManager.getInstance()
                .execute((con) -> EmployeeDA.getEmployeeByManagerID(con, managerId, page, size, sort));
       /* if (employeeList == null || employeeList.size() == 0)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else */
            return new ResponseEntity<>(employeeList
                .stream().map(x -> getStringWrapped(Employee.Parser.toJson(x)))
                .collect(Collectors.joining(",", "[", "]")), HttpStatus.OK);
    }

    @GetMapping("/by_department/{department}")
    public ResponseEntity<String> getEmployeeByDepartment(@PathVariable("department") String department,
                                                          @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                                          @RequestParam(value = "size", required = false) Integer size,
                                                          @RequestParam(value = "sort", required = false, defaultValue = "") String sort) {
        if (department == null || department.isBlank()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(DBManager.getInstance()
                .execute((con) -> EmployeeDA.getEmployeeByDepartment(con, department, page, size, sort))
                .stream().map(x -> getStringWrapped(Employee.Parser.toJson(x)))
                .collect(Collectors.joining(",", "[", "]")), HttpStatus.OK);
    }

    private String getStringWrapped(String stringToWrap) {
        return stringToWrap
                .replaceAll("\"location\"", "\"position\"")
                .replaceAll("\"name\"", "\"fullName\"");
    }
}