package cn.hergua.servicemodule.domain;

import cn.hergua.servicemodule.repoistory.jpa.ArticleRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
public class AddArticleData {

    @Autowired
    ArticleRepository articleRepository;

}
