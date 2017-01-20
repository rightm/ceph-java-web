package com.web.exception;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.google.common.collect.Maps;

public class MyExceptionHandler implements HandlerExceptionResolver {  
	private Logger log = Logger.getLogger(getClass()) ;  
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,  
            Exception ex) {  
    	 ModelAndView mv = new ModelAndView();  
         FastJsonJsonView view = new FastJsonJsonView();  
         Map<String, Object> attributes = Maps.newHashMap();  
         attributes.put("code", "500");  
         attributes.put("msg", ex.getMessage());  
         view.setAttributesMap(attributes);  
         mv.setView(view);   
         log.error("“Ï≥£:" + ex.getMessage(), ex);  
         return mv;  
    }  
}  