package cn.hergua.servicemodule.repoistory.mybatis;



import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: Mr.Hergua | 黄源钦
 * @version: 1.0     2018/10/21
 * <p>
 * </p>
 */

@Mapper
@Repository
public interface DemoMapper {

    /**
     *
     * @return
     */
    @Select("SELECT * FROM demo")
    List<String> selectDemoAll();


}
