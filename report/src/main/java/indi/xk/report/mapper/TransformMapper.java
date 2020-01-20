package indi.xk.report.mapper;

import indi.xk.report.pojo.TransformEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @Author xk
 * @Date 2020/1/16 15:18
 * @Version 1.0
 */
@Mapper
@Component
public interface TransformMapper {
    TransformEntity queryTransform(String idCard);

    String getRegister(String substring);
}
