package com.epam.rd.autotasks.springemployeecatalog.repository;

import com.epam.rd.autotasks.springemployeecatalog.DBManager;
import com.epam.rd.autotasks.springemployeecatalog.domain.Department;
import com.epam.rd.autotasks.springemployeecatalog.domain.Employee;
import com.epam.rd.autotasks.springemployeecatalog.domain.FullName;
import com.epam.rd.autotasks.springemployeecatalog.domain.Position;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class EmployeeDA {
   /* private final static String basicStatement = "SELECT E.ID, " +
            "E.FIRSTNAME," +
            "E.LASTNAME," +
            "E.MIDDLENAME, " +
            "E.POSITION, " +
            "E.HIREDATE," +
            "E.SALARY," +
            "D.ID AS DEPARTMENT," +
            "D.NAME, " +
            "D.LOCATION, " +
            "E.MANAGER " +
            "FROM EMPLOYEE E LEFT JOIN DEPARTMENT D ON E.DEPARTMENT = D.ID ";*/

    private final static String altStatement = "SELECT E.ID," +
            "E.FIRSTNAME," +
            "E.LASTNAME, " +
            "E.MIDDLENAME, " +
            "E.POSITION, " +
            "E.HIREDATE, " +
            "E.SALARY, " +
            "E.DEPARTMENT AS DEPARTMENT, " +
            "d1.NAME, " +
            "d1.LOCATION, " +
            "E.MANAGER, " +
            "M.FIRSTNAME as mFIRSTNAME, " +
            "m.LASTNAME as mLASTNAME, " +
            "m.MIDDLENAME as mMIDDLENAME, " +
            "m.POSITION as MPOSITION, " +
            "m.HIREDATE as MHIREDATE, " +
            "m.SALARY as msalary, " +
            "m.department AS mDEP, " +
            "d2.NAME as MDEPNAME, " +
            "d2.LOCATION as MDEPLOCATION " +
            "FROM Employee E LEFT JOIN EMPLOYEE M on E.manager = m.id " +
            "LEFT JOIN department d1 on d1.id = E.department " +
            "LEFT JOIN department d2 on d2.id = M.department";

    public static List<Employee> getEmployees(Connection connection, Integer page, Integer size, String sort) {
        List<Employee> employees = new ArrayList<>();
        String st = altStatement;
        st = Paging.addSort(sort, st);
        st = Paging.addPaging(page, size, st);

        System.out.println(st);
        try (PreparedStatement statement = connection.prepareStatement(st)) {
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    employees.add(getEmployeeFromRS(rs, false));
                }
            }
            return employees;
        } catch (SQLException e) {
            log.error(e);
        }
        return employees;
    }

    public static Employee getEmployeeByID(Connection connection, Integer employeeId, Boolean isFullChain) {
        if (employeeId != null)
            try (PreparedStatement statement = connection.prepareStatement(altStatement + " WHERE E.ID = ? ")) {
                {
                    statement.setInt(1, employeeId);
                    try (ResultSet rs = statement.executeQuery()) {
                        while (rs.next())
                            if (isFullChain)
                                return getEmployeeFromRS(rs, isFullChain);
                            else
                                return getEmployeeFromRS(rs, false);
                    }
                }
            } catch (SQLException e) {
                log.error(e);
            }
        return null;
    }
//░
    public static List<Employee> getEmployeeByManagerID(Connection connection, Integer managerId, Integer page, Integer size, String sort) {
        List<Employee> employees = new ArrayList<>();
        if (managerId != null) { //here was basic statement
            String st = altStatement + " WHERE E.MANAGER = ? ";

            st = Paging.addSort(sort, st);
            st = Paging.addPaging(page, size, st);
            System.out.println(st);
            try (PreparedStatement statement = connection.prepareStatement(st)) {
                {
                    statement.setInt(1, managerId);
                    try (ResultSet rs = statement.executeQuery()) {
                        while (rs.next()) {
                            employees.add(getEmployeeFromRS(rs, false));
                        }
                    }
                }
                return employees;
            } catch (SQLException e) {
                log.error(e);
            }
        }
        return employees;
    }


    public static List<Employee> getEmployeeByDepartment(Connection connection, String department, Integer page, Integer size, String sort) {
        if (department == null || department.isBlank())
            return null;
        List<Employee> employees = new ArrayList<>();
        String st = altStatement;
        if (department.matches("\\d*"))
            st = st + " WHERE E.DEPARTMENT = ? ";
        else
            st = st + " WHERE d1.NAME = ? ";
        st = Paging.addSort(sort, st);
        st = Paging.addPaging(page, size, st);
        System.out.println(st);
        try (PreparedStatement statement = connection.prepareStatement(st)) {
            {
                statement.setString(1, department);
                try (ResultSet rs = statement.executeQuery()) {
                    while (rs.next()) {
                        employees.add(getEmployeeFromRS(rs, false));
                    }
                }
            }
            return employees;
        } catch (SQLException e) {
            log.error(e);
        }
        return employees;
    }

    private static Employee getEmployeeFromRS(ResultSet rs, Boolean isFullChain) throws SQLException {
        long departmentId = rs.getLong("DEPARTMENT");
        Employee manager = null;
        long managerId = rs.getLong("MANAGER");
        if (!(managerId == 0))
            if (isFullChain) {
                manager = DBManager.getInstance().execute((con) -> getEmployeeByID(con, rs.getInt("MANAGER"), isFullChain));

            } else {
                manager = new Employee(managerId,
                        new FullName(rs.getString("MFIRSTNAME"), rs.getString("MLASTNAME"), rs.getString("MMIDDLENAME")),
                        rs.getString("MPOSITION") == null ? null : Position.valueOf(rs.getString("MPOSITION")),
                        rs.getDate("MHIREDATE").toLocalDate(),
                        rs.getBigDecimal("MSALARY"),
                        null,
                        rs.getLong("MDEP") == 0 ? null : new Department(rs.getLong("MDEP"), rs.getString("MDEPNAME"), rs.getString("MDEPLOCATION")));
            }
        return new Employee(rs.getLong("ID"),
                new FullName(rs.getString("FIRSTNAME"), rs.getString("LASTNAME"), rs.getString("MIDDLENAME")),
                Position.valueOf(rs.getString("POSITION")),
                rs.getDate("HIREDATE").toLocalDate(),
                rs.getBigDecimal("SALARY"),
                manager,
                departmentId == 0 ? null : new Department(departmentId, rs.getString("NAME"), rs.getString("LOCATION")));
    }
}
