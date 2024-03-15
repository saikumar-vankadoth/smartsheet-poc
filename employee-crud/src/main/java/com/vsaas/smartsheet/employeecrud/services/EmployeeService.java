package com.vsaas.smartsheet.employeecrud.services;

import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.models.Cell;
import com.smartsheet.api.models.Row;
import com.vsaas.smartsheet.employeecrud.dtos.Employee;
import com.vsaas.smartsheet.employeecrud.utils.MappingUtil;
import com.vsaas.smartsheet.employeecrud.utils.SheetUtil;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {

    public List<Employee> getEmployees() throws SmartsheetException {
        List<Employee> employees = new ArrayList<>();
        List<Row> rows = SheetUtil.getRows();
        if(rows != null && !rows.isEmpty()) {
            employees = MappingUtil.mapRowsToEmployees(rows);
        }
        return employees;
    }

    public List<Employee> createEmployee(Employee employee) throws SmartsheetException {
        Map<String, Long> columnNameNdIdMap = SheetUtil.getColumnNameNdId();
        if(columnNameNdIdMap != null && !columnNameNdIdMap.isEmpty()) {
            List<Cell> cells = null;
            for(String columnName: columnNameNdIdMap.keySet()) {
                Cell cell = new Cell(columnNameNdIdMap.get(columnName));
                switch (columnName) {
                    case "employeeId":
                        cell.setDisplayValue(employee.getEmployeeId().toString());
                        break;
                    case "userName":
                        cell.setDisplayValue(employee.getEmployeeId().toString());
                        break;
                    case "employeeName":
                        cell.setDisplayValue(employee.getEmployeeId().toString());
                        break;
                    case "active":
                        cell.setDisplayValue("true");
                        break;
                    case "createdAt":
                        cell.setDisplayValue(new Date().toString());
                        break;
                    case "updatedAt":
                        cell.setDisplayValue(new Date().toString());
                        break;
                }

                if(cells == null) {
                    cells = new ArrayList<Cell>();
                }
                cells.add(cell);
            }
            if(cells == null || cells.size() != 0) {
                Row row = new Row();
                row.setCells(cells);

                List<Row> rows = new ArrayList<>();
                rows.add(row);

                List<Row> addedRows = SheetUtil.addRows(rows);
                List<Employee> addedEmployees = MappingUtil.mapRowsToEmployees(addedRows);

                return addedEmployees;
            }
        }
        return null;
    }

    public List<Employee> updateEmployee(Employee employee) throws SmartsheetException {
        Map<String, Long> columnNameNdIdMap = SheetUtil.getColumnNameNdId();
        if(columnNameNdIdMap != null && !columnNameNdIdMap.isEmpty()) {
            Row row = SheetUtil.getRow(employee.getRowId());
            List<Cell> cells = null;
            for(String columnName: columnNameNdIdMap.keySet()) {
                Cell cell = new Cell(columnNameNdIdMap.get(columnName));
                switch (columnName) {
                    case "employeeId":
                        cell.setDisplayValue(employee.getEmployeeId().toString());
                        break;
                    case "userName":
                        cell.setDisplayValue(employee.getEmployeeId().toString());
                        break;
                    case "employeeName":
                        cell.setDisplayValue(employee.getEmployeeId().toString());
                        break;
                    case "active":
                        cell.setDisplayValue("true");
                        break;
                    case "createdAt":
                        cell.setDisplayValue(new Date().toString());
                        break;
                    case "updatedAt":
                        cell.setDisplayValue(new Date().toString());
                        break;
                }

                if(cells == null) {
                    cells = new ArrayList<Cell>();
                }
                cells.add(cell);
            }
            if(cells == null || cells.size() != 0) {
                row.setCells(cells);

                List<Row> rows = new ArrayList<>();
                rows.add(row);

                List<Row> addedRows = SheetUtil.updateRow(rows);
                List<Employee> updatedEmployees = MappingUtil.mapRowsToEmployees(addedRows);

                return updatedEmployees;
            }
        }
        return null;
    }

    public void deleteEmployee(Long rowId) throws SmartsheetException {
        Set<Long> rowIds = new HashSet<>();
        rowIds.add(rowId);
        SheetUtil.deleteRows(rowIds);
    }
}
