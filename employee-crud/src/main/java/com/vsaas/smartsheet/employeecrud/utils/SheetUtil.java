package com.vsaas.smartsheet.employeecrud.utils;

import com.smartsheet.api.*;
import com.smartsheet.api.models.Column;
import com.smartsheet.api.models.Row;
import com.smartsheet.api.models.Sheet;
import com.smartsheet.api.models.Workspace;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SheetUtil {

    public static Smartsheet getSmartSheetFactory() {
        return SmartsheetFactory.createDefaultClient(StaticData.SHEET_ACCESS_TOKEN);
    }

    public static WorkspaceResources getWorkSpaceResources() {
        Smartsheet smartSheetFactory = getSmartSheetFactory();
        return smartSheetFactory.workspaceResources();
    }

    public static Workspace getWorkspace() throws SmartsheetException {
        WorkspaceResources workspaceResources = getWorkSpaceResources();
        if(workspaceResources == null) {
            return  null;
        }

        List<Workspace> workspaces = workspaceResources.listWorkspaces(null).getData();

        for(Workspace workspace: workspaces) {
            if(workspace.getName().equals(StaticData.WORKSPACE_NAME)) {
                return workspace;
            }
        }

        return null;
    }

    public static SheetResources getSheetResources() {
        return getSmartSheetFactory().sheetResources();
    }

    public static Sheet getSheet() throws SmartsheetException {
        Workspace workSpace = getWorkspace();
        if(workSpace != null) {
            List<Sheet> sheets = workSpace.getSheets();
            for(Sheet sheet: sheets) {
                if(sheet.getName().equals(StaticData.SHEET_NAME)) {
                    return sheet;
                }
            }
        }
        return null;
    }

    public static SheetRowResources getRowResources() throws SmartsheetException {
        SheetResources sheetResources = getSheetResources();
        if(sheetResources != null) {
            return sheetResources.rowResources();
        }
        return null;
    }

    public static List<Row> getRows() throws SmartsheetException {
        Sheet sheet = getSheet();
        if(sheet != null) {
            return sheet.getRows();
        }
        return null;
    }

    public static Row getRow(Long rowId) throws SmartsheetException {
        List<Row> rows = getRows();
        if(rows != null && !rows.isEmpty()) {
            for(Row row: rows) {
                if(row.getId().equals(rowId)) {
                    return row;
                }
            }
        }
        return null;
    }

    public static List<Row> addRows(List<Row> rows) throws SmartsheetException {
        SheetRowResources rowResources = getRowResources();
        if(rowResources != null) {
            Sheet sheet = getSheet();
            if(sheet != null) {
                Long sheetId = sheet.getId();
                return rowResources.addRows(sheetId, rows);
            }
        }
        return null;
    }

    public static void deleteRows(Set<Long> rowIds) throws SmartsheetException {
        SheetRowResources rowResources = getRowResources();
        if(rowResources != null) {
            Sheet sheet = getSheet();
            if(sheet != null) {
                Long sheetId = sheet.getId();
                rowResources.deleteRows(sheetId, rowIds, true);
            }
        }
    }

    public static List<Row> updateRow(List<Row> rows) throws SmartsheetException {
        SheetRowResources rowResources = getRowResources();
        if(rowResources != null) {
            Sheet sheet = getSheet();
            if(sheet != null) {
                Long sheetId = sheet.getId();
                return rowResources.updateRows(sheetId, rows);
            }
        }
        return null;
    }

    public static List<Column> getColumns() throws SmartsheetException {
        Sheet sheet = getSheet();
        if(sheet != null) {
            return sheet.getColumns();
        }
        return null;
    }

    public static Map<String, Long> getColumnNameNdId() throws SmartsheetException {
        List<Column> columns = getColumns();
        if(columns != null && !columns.isEmpty()) {
            Map<String, Long> columnNameNdIdMap = new HashMap<>();
            for(Column column: columns) {
                columnNameNdIdMap.put(column.getTitle(), column.getId());
            }
            return columnNameNdIdMap;
        }
        return null;
    }
}
