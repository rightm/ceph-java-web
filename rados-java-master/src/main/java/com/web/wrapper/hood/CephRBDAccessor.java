package com.web.wrapper.hood;

import static com.web.util.util.nullCheck;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ceph.rados.IoCTX;
import com.ceph.rados.exceptions.RadosException;
import com.ceph.rbd.Rbd;
import com.web.exception.CephWebException;

/**
 * create a ceph rbd for a different pool
 * @author dell2
 */
public class CephRBDAccessor {
	private CephRados rados ;
	private Rbd executor ;
	private Logger logger = Logger.getLogger( getClass() ) ;
	private IoCTX ioCtx ;
	private String pool ;
	
	public CephRBDAccessor(String pool) {
		this.pool = pool ;
	}
	
	/**
	 * create a ioCtx first ,then the rbd can be used
	 */
	public void connect(ServletContext sc) throws Exception{
		if( null == rados ){
			executor = null ;
			rados = WebApplicationContextUtils.getRequiredWebApplicationContext(sc).getBean(CephRados.class);
		}
		
		try {
			ioCtx= rados.getRados().ioCtxCreate( pool);
			executor= new Rbd( ioCtx );
		} catch (RadosException e) {
			logger.error("Can not instance rbd",e);
			throw new CephWebException("Can not create io context") ;
		}
	}
	
	/**
	 * create a rbd v2 image,default features bit will be assign 3
	 * default features refer http://www.zphj1987.com/2016/06/07/rbd%E6%97%A0%E6%B3%95map-rbd-feature-disable/
	 * @param name The size of the new image in bytes
	 * @param size The size of the new image in bytes
	 * @throws Exception
	 */
	public void createImg(String name,long size) throws  Exception{
		nullCheck(executor);
		executor.create(name,size,(long)3) ;
	}
	
	public void deleteImg(String name)throws  Exception{
		nullCheck(executor);
		executor.remove(name);
	}
	
	public void renameImg(String srcName,String destName) throws Exception{
		nullCheck(executor);
		executor.rename(srcName, destName);
	}
	
	public void clear(){
		if( null != this.rados && null != ioCtx ){
			rados.getRados().ioCtxDestroy( this.ioCtx );
			//don't destroy the rados handle yet.
			//rados.getRados().shutDown();
		}
	}
	
}
