package cn.hergua.servicemodule.domain;

import java.io.Serializable;

public class CountLikeVo implements Serializable {

    private Boolean isLike ;
    private Integer countLike;

    public CountLikeVo(boolean isLike, int countLike) {
        this.isLike = isLike;
        this.countLike = countLike;
    }

    public CountLikeVo() {
    }

    public Boolean getLike() {
        return isLike;
    }

    public void setLike(Boolean like) {
        isLike = like;
    }

    public Integer getCountLike() {
        return countLike;
    }

    public void setCountLike(Integer countLike) {
        this.countLike = countLike;
    }
}
