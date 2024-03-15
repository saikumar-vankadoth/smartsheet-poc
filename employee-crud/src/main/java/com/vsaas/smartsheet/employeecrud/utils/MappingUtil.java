package com.vsaas.smartsheet.employeecrud.utils;

import com.smartsheet.api.models.Cell;
import com.smartsheet.api.models.Row;
import com.vsaas.smartsheet.employeecrud.dtos.Employee;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MappingUtil {

    public static List<Employee> mapRowsToEmployees(List<Row> rows) {
        List<Employee> employees = new ArrayList<>();
        if(rows != null && !rows.isEmpty()) {
            for(Row row: rows) {
                employees.add(mapRowToEmployee(row));
            }
        }
        return employees;
    }

    public static Employee mapRowToEmployee(Row row) {
        Employee employee = new Employee();
        if(row != null) {
            employee.setRowId(row.getId());
            employee.setEmployeeId(Long.valueOf(row.getCells().get(0).getDisplayValue()));
            employee.setUserName(row.getCells().get(1).getDisplayValue());
            employee.setEmployeeName(row.getCells().get(2).getDisplayValue());
            employee.setActive(Boolean.parseBoolean(row.getCells().get(3).getDisplayValue()));
            employee.setCreatedAt(new Date(row.getCells().get(4).getDisplayValue()));
            employee.setUpdatedAt(new Date(row.getCells().get(5).getDisplayValue()));
        }
        return employee;
    }

}
