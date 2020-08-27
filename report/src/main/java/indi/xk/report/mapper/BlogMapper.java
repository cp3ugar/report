package indi.xk.report.mapper;

import indi.xk.report.pojo.Blog;
import indi.xk.report.pojo.dto.StudentDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author xk
 * @Date 2020/8/20 18:46
 * @Version 1.0
 */
@Repository
public interface BlogMapper {
    /**
     * 添加
     * @param blog
     */
    void addBlog(Blog blog);

    /**
     * 编辑
     * @param blog
     */
    void updateBlog(Blog blog);

    /**
     * 删除
     * @param id
     */
    void deleteBlog(Integer id);

    /**
     * 详情
     * @param id
     * @return
     */
    Blog detailBlog(Integer id);

    /**
     * count
     * @return
     */
    Integer count();

    /**
     * 列表
     * @param map
     * @return
     */
    List<Blog> listBlog(Map map);
}
