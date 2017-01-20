package com.web.util;

import org.apache.log4j.Logger;

import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.web.wrapper.hood.CephRBDAccessor;

public class RbdRemovalListerner implements RemovalListener<Object, Object> {
	private Logger logger = Logger.getLogger(getClass()) ;
	
	/**
	 * RBD cleaner listener for rightly destroy the rbd reference
	 */
	@Override
	public void onRemoval(RemovalNotification<Object, Object> notification) {
		if( null != notification){
			Object ref = notification.getValue() ;
			if( null != ref && ref instanceof CephRBDAccessor){
				((CephRBDAccessor)ref).clear(); 
			}else{
				logger.warn("the rbd and ioCtx has already destroyed without releasing the io context");
			}
		}
		
	}

}
