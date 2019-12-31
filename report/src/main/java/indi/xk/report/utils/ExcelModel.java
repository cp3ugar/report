package indi.xk.report.utils;

import java.io.Serializable;
import java.util.List;

/**
 * @Author xk
 * @Date 2019/12/31 11:13
 * @Version 1.0
 */
public class ExcelModel implements Serializable {
    private int row;

    private List<ExcelObjectModel> excelList;

    public ExcelModel() {
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public List<ExcelObjectModel> getExcelList() {
        return excelList;
    }

    public void setExcelList(List<ExcelObjectModel> excelList) {
        this.excelList = excelList;
    }
}
