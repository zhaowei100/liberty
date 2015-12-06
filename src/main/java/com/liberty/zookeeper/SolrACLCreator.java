//package com.liberty.zookeeper;
//
//import java.util.List;
//
//import org.apache.zookeeper.data.ACL;
//
//public class SolrACLCreator extends ZkACLProvider{
//
//	
//	private List<ACL> globalACLsToAdd;
//	  
//	  @Override
//	  public List<ACL> getACLsToAdd(String zNodePath) {
//	    // In default (simple) implementation use the same set of ACLs for all znodes
//	    if (globalACLsToAdd == null) {
//	      synchronized (this) {
//	        if (globalACLsToAdd == null) globalACLsToAdd = createGlobalACLsToAdd();
//	      }
//	    }
//	    return globalACLsToAdd;
//
//	  }
//}
