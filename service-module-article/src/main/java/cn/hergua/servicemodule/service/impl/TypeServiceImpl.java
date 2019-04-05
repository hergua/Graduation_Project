package cn.hergua.servicemodule.service.impl;

import cn.hergua.servicemodule.domain.entity.ArticleType;
import cn.hergua.servicemodule.repository.jpa.TypeRepository;
import cn.hergua.servicemodule.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Hergua
 * @Version 1.0
 * @Date 2019/3/27
 * <p>
 *
 * </p>
 */
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    TypeRepository repository;

    @Override
    public void saveType(ArticleType articleType) {
        repository.save(articleType);
    }

    @Override
    public void delType(Long id) {
        repository.deleteById(id);
    }

    @Override
    public ArticleType updateType(ArticleType articleType) {
        return null;
    }

    @Override
    public List<ArticleType> queryType() {
        return repository.findAll();
    }
}
