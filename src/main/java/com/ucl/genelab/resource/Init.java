package com.ucl.genelab.resource;

import java.util.ArrayList;

import com.ucl.genelab.ssh.SSHUtil;
import com.ucl.genelab.ssh.SshConfiguration;
import com.ucl.genelab.resource.ReadConfig;

public class Init {
	public ArrayList<String> reflist = new ArrayList();
	public ArrayList<String> inputlist = new ArrayList();
	public ArrayList<String> outputlist = new ArrayList();
	public Init(SshConfiguration conf) throws Exception {
		
		/*
		String reffilecommand = "cd /home/chenhao/git/ && ls";
		String inputfilecommand = "cd /home/chenhao/git/ && ls";
		String outputfilecommand = "cd /home/chenhao/git/ && ls";
		*/
		SSHUtil sshUitl = new SSHUtil(conf);
		sshUitl.runCmdout(ReadConfig.reffilecommand, "UTF-8", reflist);
//		sshUitl.runCmdout("ls", "UTF-8", reflist);
		sshUitl.runCmdout(ReadConfig.inputfilecommand, "UTF-8", inputlist);
//		sshUitl.runCmdout("ls", "UTF-8", inputlist);
		sshUitl.runCmdout(ReadConfig.outputfilecommand, "UTF-8", outputlist);
		sshUitl.close();
	}
	
	
}
