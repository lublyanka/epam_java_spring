package com.epam.rd.autotasks.springemployeecatalog.repository;

import com.epam.rd.autotasks.springemployeecatalog.domain.Department;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Log4j2
//TODO
public class DepartmentDA {
    public static Department getDepartmentById(Connection connection, int departmentId) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM DEPARTMENT WHERE ID = ?")) {
            {
                statement.setInt(1, departmentId);
                try (ResultSet rs = statement.executeQuery()) {
                    while (rs.next()) {
                        return getDepartmentFromRS(rs);
                    }
                }
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return null;
    }

    public static Department getDepartmentByName(Connection connection, String departmentName) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM DEPARTMENT WHERE NAME = ?")) {
            {
                statement.setString(1, departmentName);
                try (ResultSet rs = statement.executeQuery()) {
                    while (rs.next()) {
                        return getDepartmentFromRS(rs);
                    }
                }
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return null;
    }

    private static Department getDepartmentFromRS(ResultSet rs) throws SQLException {
        return new Department(rs.getLong("ID"),
                rs.getString("NAME"),
                rs.getString("LOCATION"));
    }
}
