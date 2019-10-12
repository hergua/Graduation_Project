package com.xmmufan.permission.controller;

import com.xmmufan.permission.utils.TreeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/mapping")
public class UrlMapperController {

    @Autowired
    private RequestMappingHandlerMapping handlerMapping;

    @GetMapping("/getAllControllerMapping")
    public List mappingList() {
        List<HashMap<String, String>> urlList = new ArrayList<>(50);

        handlerMapping.getHandlerMethods().forEach((requestMappingInfo, handlerMethod) -> {
            HashMap<String, String> hashMap = new HashMap<>(8);
            PatternsRequestCondition condition = requestMappingInfo.getPatternsCondition();
            for (String url : condition.getPatterns()) {
                hashMap.put("url", url);
                hashMap.put("operation", "["+handlerMethod.getMethod().getName()+","+url+"]");
            }
            hashMap.put("className", handlerMethod.getMethod().getDeclaringClass().getName());
            hashMap.put("method", handlerMethod.getMethod().getName());
            RequestMethodsRequestCondition methodsCondition = requestMappingInfo.getMethodsCondition();
            String type = methodsCondition.toString();
            if (type != null && type.startsWith("[") && type.endsWith("]")) {
                type = type.substring(1, type.length() - 1);
                hashMap.put("type", type); // 方法名
            }
            urlList.add(hashMap);
        });
        List<Map<String,String>> sourceList = urlList.stream()
                .filter(map -> !this.getClass().getName().equals(map.get("className")))
                .collect(Collectors.toList());
        return TreeUtils.generatorTreeStructure(sourceList, "className");
    }


}
