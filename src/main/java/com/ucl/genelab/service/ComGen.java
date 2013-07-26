package com.ucl.genelab.service;


import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ucl.genelab.metadata.Bwamem;
import com.ucl.genelab.metadata.Bwaindex;
import com.ucl.genelab.metadata.Inputfilelist;
import com.ucl.genelab.resource.Conf;
import com.ucl.genelab.resource.Init;
import com.ucl.genelab.ssh.GetJobId;
import com.ucl.genelab.ssh.SSHUtil;
import com.ucl.genelab.ssh.SshConfiguration;
import com.ucl.genelab.resource.Conf;
import com.ucl.genelab.metadata.Referencelist;

import org.apache.log4j.Logger;

@Path("/command")
public class ComGen {
	private static Logger logger = Logger.getLogger(ComGen.class);
//	private Shell shell = new Shell(Conf.IP,Conf.PORT,Conf.USERNAME,Conf.PASSWORD);
	private static SshConfiguration conf = new SshConfiguration( Conf.IP,Conf.USERNAME,Conf.PASSWORD,Conf.PORT);
	public static ArrayList<String> reflist = new ArrayList();
	public static ArrayList<String> inputlist = new ArrayList();
	public static Referencelist RL = new Referencelist();
	public static Inputfilelist IFL = new Inputfilelist();
	private static String jobid;
	
	public ComGen(){
	}
	
	@GET
	@Path("/getreflist")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Referencelist getreflist() throws Exception{
		Init init = new Init(conf);
		RL.setReflist(init.reflist);
		System.out.println(reflist.toString());
		return RL;
	}
	
	@GET
	@Path("/getinputlist")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Inputfilelist getinputlist() throws Exception{
		Init init = new Init(conf);
		IFL.setInputlist(init.inputlist);
		System.out.println(inputlist.toString());
		return IFL;
	}
	
	@GET
	@Path("/getjobid")
	@Produces("text/plain")
	public String getJobid() throws Exception{
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^");
		System.out.println(this.jobid);
		return this.jobid;
	}
	
	
	@POST
	@Path("/index")
	@Produces("text/plain")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response genIndex(Bwaindex bwaindex) throws Exception{
//		Init init = new Init(conf);
		String aa = bwaindex.getRefname()+" "+ bwaindex.getRefpath();
//		reflist = init.reflist;
//		System.out.println(reflist.toString());
		java.net.URI location = new java.net.URI("../index.jsp");
	    return Response.status(201).entity(aa).build();
	}
	

	
/*	
	@POST
	@Path("mem")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response genMem (Bwamem mem){
		String jobid = "";
		String command = "hadoop jar GENE.jar "+mem.getRefname()+" "+mem.getInputpath()+" "+mem.getInputpath();
		try {
			   SSHUtil sshUitl = new SSHUtil(conf);
			   sshUitl.runCmdHadoop(command, "UTF-8");
			   jobid = sshUitl.jobid;
			   sshUitl.close();
			  } catch (Exception e) {
			   e.printStackTrace();
			  }
		String displayMessage = "Your job is running, jobid = "+ jobid;
		ResponseBuilder builder = Response.ok(displayMessage);
		builder.language("en").header("Some-Header", "some value");  
	    return builder.build();
	}
	
	*/
	@POST
	@Path("mem")
	@Produces("text/plain")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response genMem (Bwamem mem) throws URISyntaxException{

		String command = "cd /home/chenhao/hadoop && ./bin/hadoop jar hadoop-examples-1.1.2.jar wordcount hdfs://localhost:9000/tmp/wordcount/word.txt hdfs://localhost:9000/tmp/wordcount/out";
//		String command = "hadoop jar GENE.jar "+mem.getRefname()+" "+mem.getInputpath()+" "+mem.getInputpath();
		this.jobid = GetJobId.getjobid(mem);
		String displayMessage = "Your job is running, jobid = "+ jobid;
		System.out.println("*******************************");
		String ccc = "hadoop jar GENE.jar "+mem.getRefname()+" "+mem.getInputpath()+" "+mem.getInputpath();
		System.out.println(displayMessage);
		System.out.println("!!!!!!!!!!!!!!!!!!!!");
		System.out.println(this.jobid);
//	    return Response.temporaryRedirect(location).build();
	    return Response.status(201).entity(displayMessage).build();
	}
	
	
	
	
}
