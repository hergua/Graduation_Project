package com.xmmufan.permission.utils;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 黄源钦
 * @Description
 * @Date 8:40
 */
public class TreeUtils {


    public static List<ControllerTree> generatorTreeStructure(List<Map<String,String>> sourceListMap, String parentsFieldName){
        List<ControllerTree> destList = new ArrayList<>(sourceListMap.size());
        Map<String,String> uniqueParentsMap = getUniqueKeyMap(sourceListMap, parentsFieldName);
        uniqueParentsMap.forEach((key, val) ->
                destList.add(ControllerTree.builder()
                .className(key)
                .urls(sourceListMap.stream()
                        .filter(map -> key.equals(map.get(parentsFieldName))).collect(Collectors.toList()))
                        .build()));
        return destList;
    }


    private static Map<String,String> getUniqueKeyMap(List<Map<String,String>> sourceListMap, String parentsFieldName){
        Map<String,String> keyMap = new HashMap<>(sourceListMap.size());
        sourceListMap.forEach(map -> keyMap.put(map.get(parentsFieldName), ""));
        return keyMap;
    }

    @Data
    @Builder
    static class ControllerTree{
        Object className;
        List<Map> urls;
    }

}
