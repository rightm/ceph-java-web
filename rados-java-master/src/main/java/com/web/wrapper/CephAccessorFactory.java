package com.web.wrapper;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.web.util.RbdRemovalListerner;
import com.web.wrapper.hood.CephRBDAccessor;

public final class CephAccessorFactory {
	private static int DEFAULT_CACHE_SIZE =100 ;
	//cache the Ceph socket connection 
	private static Cache<String, Object> cache = CacheBuilder
									.newBuilder()
									.maximumSize(DEFAULT_CACHE_SIZE)
									.concurrencyLevel(DEFAULT_CACHE_SIZE/10)
									.removalListener( new RbdRemovalListerner())
									.build() ;
	
	private CephAccessorFactory(){}
	public static void setCephOp(String key,Object value ){
		cache.put(key, value);
	}
	 
	public static CephRBDAccessor getRbd( String key,String pool){
		key = getRbdKey(key) ;
		Object val = cache.getIfPresent(key) ;
		if( null == val ){
			val = new CephRBDAccessor(pool) ;
			setCephOp(key, val);
		}
		return (CephRBDAccessor) cache.getIfPresent(key) ;
	}
	
	/**
	 * distinguish the rbd with rados
	 * @param key
	 * @return
	 */
	public static String getRbdKey(String key){
		return "rbd_"+key ;
	} 
	 
	public static void main(String[] args) {
		System.out.println( (CephRBDAccessor)null );
	}
}
