package com.ucl.genelab.resource;

import java.util.ArrayList;

import com.ucl.genelab.ssh.SSHUtil;
import com.ucl.genelab.ssh.SshConfiguration;

public class Init {
	public ArrayList<String> reflist = new ArrayList();
	public ArrayList<String> inputlist = new ArrayList();
	public Init(SshConfiguration conf) throws Exception {
		
		/*
		String reffilecommand = "cd /mapr/mapr-m3-student/myvolume/genelab/reference/ && ls";
		String inputfilecommand = "cd /mapr/mapr-m3-student/myvolume/genelab/input/ && ls";
		*/
		String reffilecommand = "cd /home/chenhao/git/ && ls";
		String inputfilecommand = "cd /home/chenhao/git/ && ls";
		SSHUtil sshUitl = new SSHUtil(conf);
		sshUitl.runCmdout(reffilecommand, "UTF-8", reflist);
//		sshUitl.runCmdout("ls", "UTF-8", reflist);
		sshUitl.runCmdout(inputfilecommand, "UTF-8", inputlist);
//		sshUitl.runCmdout("ls", "UTF-8", inputlist);
		sshUitl.close();
	}
	
	
}
