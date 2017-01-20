package com.web.controller;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.web.wrapper.CephAccessorFactory;
import com.web.wrapper.hood.CephRBDAccessor;

@Controller
@RequestMapping("rbd")
public class CephWebController {
	
	@RequestMapping("hello")
	@ResponseBody
	public String hello(){
		
		HashMap<String,String> map = Maps.newHashMap() ;
		map.put("1",	"one" ) ;
		map.put("2",	"two" ) ;
		map.put("3",	"three" ) ;
		return JSONObject.toJSONString( map ) ;
	}
	
	@RequestMapping("createImg")
	@ResponseBody
	public String createImage(){
		String sessionId = "" ;
		String pool = "" ;
		CephRBDAccessor accessor = CephAccessorFactory.getRbd(sessionId,pool) ;
		
		return null ;
	}
	
	
	
}
