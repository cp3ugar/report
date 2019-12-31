package indi.xk.report.utils;

import java.io.Serializable;

/**
 * @Author xk
 * @Date 2019/12/31 11:11
 * @Version 1.0
 */
public class ExcelObjectModel implements Serializable {
    private int columnIndex;

    private String columnProperty;

    private String columnValue;

    public ExcelObjectModel() {
    }

    public ExcelObjectModel(int columnIndex, String columnProperty) {
        this.columnIndex = columnIndex;
        this.columnProperty = columnProperty;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    public String getColumnProperty() {
        return columnProperty;
    }

    public void setColumnProperty(String columnProperty) {
        this.columnProperty = columnProperty;
    }

    public String getColumnValue() {
        return columnValue;
    }

    public void setColumnValue(String columnValue) {
        this.columnValue = columnValue;
    }
}
