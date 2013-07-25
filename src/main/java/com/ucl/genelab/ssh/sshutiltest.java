package com.ucl.genelab.ssh;

import com.ucl.genelab.resource.Conf;

public class sshutiltest {
	 /**
	  * @param args
	  */
	 public static void main(String[] args) {
	  SshConfiguration conf = new SshConfiguration();
	  conf.setHost(Conf.IP);
	  conf.setUsername(Conf.USERNAME);
	  conf.setPassword(Conf.PASSWORD);
	  conf.setPort(Conf.PORT);
	  String buf = "dhska sak sja sahj Running job: dhjsa";
	  System.out.println(buf.substring(buf.indexOf("Running job")+12));
/*	  try {
	   SSHUtil sshUitl = new SSHUtil(conf);
	   sshUitl.runCmd("ls", "UTF-8");
	   sshUitl.close();
	  } catch (Exception e) {
	   e.printStackTrace();
	  }*/
	 }
}
