package cn.hergua.servicemodule.domain;

import cn.hergua.servicemodule.domain.entity.ArticleType;
import cn.hergua.servicemodule.repoistory.jpa.TypeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Author Hergua
 * @Version 1.0
 * @Date 2019/3/3
 * <p>
 *
 * </p>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AddTypeData {

    @Autowired
    TypeRepository repository;

    @Test
    public void addData(){
        ArticleType articleType = new ArticleType(10001L,"手机","手机",null);
        ArticleType articleType2 = new ArticleType(10002L,"手机","游戏手机",null);
        repository.save(articleType);
        repository.save(articleType2);
    }

}
