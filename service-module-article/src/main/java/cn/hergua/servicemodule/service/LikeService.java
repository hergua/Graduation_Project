package cn.hergua.servicemodule.service;

import cn.hergua.servicemodule.domain.entity.Like;

/**
 * @Author Hergua
 * @Version 1.0
 * @Date 2019/3/27
 * <p>
 *
 * </p>
 */
public interface LikeService {

    void saveLike(Like like);

    void delLike (Long id);

}
