package indi.xk.esmysqldata.repository;

import indi.xk.esmysqldata.bean.User;
import org.springframework.data.repository.CrudRepository;


/**
 * @Author xk
 * @Date 2020/5/14 15:29
 * @Version 1.0
 */
public interface UserRepository extends CrudRepository<User,Integer> {

}
