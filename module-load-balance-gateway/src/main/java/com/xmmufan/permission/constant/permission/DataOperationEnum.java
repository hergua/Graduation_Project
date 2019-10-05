package com.xmmufan.permission.constant.permission;

import com.xmmufan.permission.constant.CodeEnum;

/**
 * @author 黄源钦
 * @Description
 * @Date 12:04
 */
public enum DataOperationEnum implements CodeEnum {

    ADD("ADD","新增"),
    DELETE("DELETE","删除"),
    UPDATE("UPDATE","更改"),
    QUERY("QUERY","查询");

    private String code;
    private String desc;
    private String table;

    @Override
    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    DataOperationEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
