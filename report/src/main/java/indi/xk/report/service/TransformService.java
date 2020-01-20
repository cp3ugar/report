package indi.xk.report.service;

/**
 * @Author xk
 * @Date 2020/1/16 15:16
 * @Version 1.0
 */
public interface TransformService {
    void exportTransform(String idCard) throws Exception;

    void exportTransformScheme(String idCard) throws Exception;
}
