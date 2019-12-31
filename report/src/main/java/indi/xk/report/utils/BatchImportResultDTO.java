package indi.xk.report.utils;

import indi.xk.report.pojo.dto.BaseImportEntity;

public class BatchImportResultDTO extends BaseImportEntity {
    private Integer total;

    private Integer success;

    private Integer failure;

    private String errorFileAddress;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public Integer getFailure() {
        return failure;
    }

    public void setFailure(Integer failure) {
        this.failure = failure;
    }

    public String getErrorFileAddress() {
        return errorFileAddress;
    }

    public void setErrorFileAddress(String errorFileAddress) {
        this.errorFileAddress = errorFileAddress;
    }
}
