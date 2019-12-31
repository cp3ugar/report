package indi.xk.report.pojo.dto;

public abstract class AbstractImportDTO extends BaseImportEntity {
    private boolean error;

    private String errorInfo = "";

    private int excelRow = -1;

    public int getExcelRow() {
        return excelRow;
    }

    public void setExcelRow(int excelRow) {
        this.excelRow = excelRow;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    @Override
    public String toString() {
        return "AbstractImportDTO{" +
                "error=" + error +
                ", errorInfo='" + errorInfo + '\'' +
                ", excelRow=" + excelRow +
                '}';
    }
}
