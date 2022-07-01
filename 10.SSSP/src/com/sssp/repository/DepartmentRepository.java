package com.sssp.repository;

import com.sssp.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.QueryHint;
import java.util.List;

/**
 * @Description: TODO
 * @Author: Alex McAvoy
 * @Date: 2022/3/15 17:32
 * @Version: 1.0
 **/
public interface DepartmentRepository extends JpaRepository<Department,Integer> {

    //自定义方法，以使用二级缓存
    @QueryHints({@QueryHint(name = org.hibernate.ejb.QueryHints.HINT_CACHEABLE,value = "true")})
    @Query("FROM Department d")
    List<Department> getAll();
}
