package com.liberty.zookeeper;

import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooDefs.Perms;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

public class ZooKeeperContainer {

	public static void main(String[] args) throws Exception {

		ZooKeeperContainer zooKeeperContainer = new ZooKeeperContainer();
		
		ZooKeeper zooKeeper = new ZooKeeper("localhost:2181", 5000,
				new Watcher() {

					@Override
					public void process(WatchedEvent event) {
						System.out.println("事件被触发：" + event.getPath());

					}
				});

		String root = "/my";
		String child = "/my/test";
		String childTwo = "/my/schema.xml";
		// 创建一个目录节点
		if (zooKeeper.exists(root, true) == null) {
			zooKeeper.create(root, "rootPath".getBytes(), Ids.OPEN_ACL_UNSAFE,
					CreateMode.PERSISTENT);
		}

		String auth_type = "digest";
		String auth = "zhao:test2";
		zooKeeper.addAuthInfo(auth_type, auth.getBytes());
		// 创建一个子目录节点
		if (zooKeeper.exists(child, true) == null) {
			zooKeeper.create(child, "childPath".getBytes(),
					Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);
		}

		auth_type = "digest";
		auth = "zhao:test";
		zooKeeper.addAuthInfo(auth_type, auth.getBytes());

		// 取出子目录节点列表
		System.out.println(zooKeeper.getChildren(root, true));

		// 修改子目录节点数据
		zooKeeper.setData(child, "modifyChild".getBytes(), -1);
		System.out.println("目录节点状态：[" + zooKeeper.exists(root, true) + "]");

		File file = new File("D:/solr/conf/schema.xml");

		/**
		 * 获取ACL，access control
		 */
		List<ACL> acls = zooKeeperContainer.obtainAcls(Perms.ALL, auth_type, auth);
		
		// 创建另外一个子目录节点
		if (zooKeeper.exists(childTwo, true) == null) {
			zooKeeper.create(childTwo, FileUtils.readFileToByteArray(file),
					acls, CreateMode.PERSISTENT);
			System.out.println(new String(zooKeeper.getData(childTwo, true,
					null)));
		}

		// 删除子目录节点
		// zooKeeper.delete(child, -1);
		// zooKeeper.delete(childTwo, -1);
		//
		// // 删除父目录节点
		// zooKeeper.delete(root, -1);

		// 关闭连接
		zooKeeper.close();
	}

	/**
	 * 权限相关样例，auth设置与节点创建有关，如上比如node1创建前调用了这个方法，那么node1的auth1，node2创建前再调用这个方法，
	 * 那么node2的auth是auth2
	 */
	public void zookeeperAuth(ZooKeeper zooKeeper) {

		String auth_type = "digest";
		String auth = "zhao:test";
		zooKeeper.addAuthInfo(auth_type, auth.getBytes());
	}

	/**
	 * scheme: world、auth、digest、ip、super、sasl
	 * id=SHA1(username:password)
	 * perms权限，包括CREATE(c)、DELETE(d)、READ(r)、WRITE(w)、ADMIN(a)
	 * 
	 * @param args
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public List<ACL> obtainAcls(int perms, String... args)
			throws NoSuchAlgorithmException {

		List<ACL> acls = new ArrayList<>();
		for (int i = 0; i < args.length; i = i + 2) {
			ACL acl = new ACL(Perms.ALL, new Id(args[i],
					DigestAuthenticationProvider.generateDigest(args[i + 1])));
			acls.add(acl);
		}
		return acls;
	}
}
