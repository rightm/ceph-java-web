package com.web.wrapper.hood;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import static com.web.util.util.nullCheck;
import com.ceph.rados.Rados;
import com.ceph.rados.exceptions.RadosException;
/**
 * decorate the rados object will be proper when there are some changes
 * under the hood.
 * @author dell2
 */
public class CephRados {
	private Logger logger = Logger.getLogger(this.getClass() ) ;
	private Rados r;
	private String id ="admin";
	private String configFile = "/etc/ceph/ceph.conf" ;
	
	public CephRados() {
		 r = new Rados(this.id);
         try {
			r.confReadFile(new File(this.configFile));
			r.connect();
		} catch (RadosException e) {
			r = null ;
			logger.error("Can not connection the ceph cluster,configFile>"+this.configFile,e);
		}

	}
	
	public void createPool(String poolName) throws Exception   {
		nullCheck(r) ;
		r.poolCreate(poolName);
	}
	
	public void deletePool(String poolName) throws  Exception{
		nullCheck(r) ;
		r.poolDelete( poolName );
	}
	
	public long lookUpPoolId(String poolName) throws  Exception{
		nullCheck(r);
		return r.poolLookup( poolName ) ;
	}
	
	public List<String> listPools(String poolName) throws  Exception{
		nullCheck( r ) ;
		return Arrays.asList( r.poolList()  );
	}
	
	
	public Rados getRados(){
		nullCheck( r ) ;
		return r ;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getConfigFile() {
		return configFile;
	}

	public void setConfigFile(String configFile) {
		this.configFile = configFile;
	}
	 
}
