package com.xmmufan.permission.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.ContextLoader
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping

import java.util.ArrayList
import java.util.HashMap
import java.util.stream.Collectors

@RestController
@RequestMapping(value = ["/mapping"])
open class UrlMapperController @Autowired  constructor(
        private val handlerMapping: RequestMappingHandlerMapping) {

    @GetMapping(value = ["/getAllControllerMapping"])
    fun mappingList(): List<*> {
        val urlList = ArrayList<HashMap<String, String>>(handlerMapping.handlerMethods.size)

        handlerMapping.handlerMethods.forEach { (requestMappingInfo, handlerMethod) ->
            val hashMap = HashMap<String, String>(8)
            val condition = requestMappingInfo.patternsCondition
            for (url in condition.patterns) {
                hashMap["url"] = url
                hashMap["operation"] = "[" + handlerMethod.method.name + "," + url + "]"
            }
            hashMap["className"] = handlerMethod.method.declaringClass.name
            hashMap["method"] = handlerMethod.method.name
            val methodsCondition = requestMappingInfo.methodsCondition
            val type = methodsCondition.toString()
            if (type.startsWith("[") && type.endsWith("]")) {
                hashMap["type"] = type.substring(1, type.length - 1) // 方法名
            }
            urlList.add(hashMap)
        }
        val sourceList = urlList.stream()
                .filter { this.javaClass.name != it["className"] }
                .collect(Collectors.toList<Map<String, String>>())
        return generatorTreeStructure(sourceList, "className")
    }

    fun list(): List<*>{
        return ArrayList<HashMap<String, String>>(handlerMapping.handlerMethods.size).apply {
            handlerMapping.handlerMethods.forEach{ (requestMappingInfo, handlerMethod) ->
                val hashMap = HashMap<String, String>(20)
                for (url in requestMappingInfo.patternsCondition.patterns) {
                    hashMap["url"] = url
                    hashMap["operation"] = "[" + handlerMethod.method.name + "," + url + "]"
                }
                hashMap["className"] = handlerMethod.method.declaringClass.name
                hashMap["method"] = handlerMethod.method.name
                val methodsCondition = requestMappingInfo.methodsCondition
                val type = methodsCondition.toString()
                if (type.startsWith("[") && type.endsWith("]")) {
                    hashMap["type"] = type.substring(1, type.length - 1) // 方法名
                }
                this.add(hashMap)
            }
        }
    }


    companion object{


        fun generatorTreeStructure(sourceListMap: List<Map<String, String>>, parentsFieldName: String): List<ControllerTree> {
            val destList = ArrayList<ControllerTree>(sourceListMap.size)
            val uniqueParentsMap = getUniqueKeyMap(sourceListMap, parentsFieldName)
            uniqueParentsMap.forEach { (key, v) ->
                destList.add(ControllerTree().apply {
                    this.className = key
                    this.urls = sourceListMap.stream().filter { key == it[parentsFieldName] }.collect(Collectors.toList<Map<String, String>>())
                })
            }
            return destList
        }


        private fun getUniqueKeyMap(sourceListMap: List<Map<String, String>>, parentsFieldName: String): Map<String, String> {
            return HashMap<String, String>(sourceListMap.size).apply {
                sourceListMap.forEach { this[it[parentsFieldName] ?: ""] = "" }
            }
        }
    }


    class ControllerTree {
        var className: Any? = null
        var urls: List<Map<*, *>>? = null
    }

}
