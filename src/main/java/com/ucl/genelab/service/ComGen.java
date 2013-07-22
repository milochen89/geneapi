package com.ucl.genelab.service;


import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ucl.genelab.metadata.Index;
import com.ucl.genelab.ssh.Shell;

import org.apache.log4j.Logger;

@Path("/command")
public class ComGen {
	private static Logger logger = Logger.getLogger(ComGen.class);
	private Shell shell = new Shell("newgate.cs.ucl.ac.uk",22,"username","password");
	
	@GET
	@Path("index")
	@Produces("text/plain")
//	@Consumes(MediaType.APPLICATION_JSON)
	public String genIndex (){
		String[] abc = {"echo -e \"\033[44;37;5m ME \033[0m COOL\""};
		shell.executeCommands(abc);
		System.out.println("*********222************");
		System.out.println(shell.getResponse());
		System.out.println("*********222************");
		shell.disconnect();
		String aaa = shell.getResponse();
		System.out.println("*********222************");
		System.out.println(shell.getResponse());
		System.out.println("*********222************");
		shell.executeCommands(abc);
		System.out.println("*********333************");
		System.out.println(shell.getResponse());
		System.out.println("*********333************");
		aaa = aaa + shell.getResponse();
		shell.disconnect();
		return aaa;
		
	}
	
	
}
