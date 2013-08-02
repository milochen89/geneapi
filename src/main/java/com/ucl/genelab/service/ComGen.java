package com.ucl.genelab.service;


import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ucl.genelab.metadata.Bwamem;
import com.ucl.genelab.metadata.Bwaindex;
import com.ucl.genelab.metadata.Completion;
import com.ucl.genelab.metadata.Inputfilelist;
import com.ucl.genelab.metadata.Outputfilelist;
import com.ucl.genelab.resource.Conf;
import com.ucl.genelab.resource.Init;
import com.ucl.genelab.ssh.GetJobId;
import com.ucl.genelab.ssh.GetJobProcess;
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
	public static ArrayList<String> outputlist = new ArrayList();
	public static Referencelist RL = new Referencelist();
	public static Inputfilelist IFL = new Inputfilelist();
	public static Outputfilelist OFL = new Outputfilelist();
	public static String jobid;
	public static Completion completion = new Completion();
	
	public ComGen(){
	}
	
	@GET
	@Path("/getreflist")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Referencelist getreflist() throws Exception{
		Init init = new Init(conf);
		RL.setReflist(init.reflist);
		reflist = RL.getReflist();
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
		inputlist = IFL.getInputlist();
		System.out.println(inputlist.toString());
		return IFL;
	}
	
	@GET
	@Path("/getoutputlist")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Outputfilelist getoutputlist() throws Exception{
		Init init = new Init(conf);
		OFL.setOutputlist(init.outputlist);
		outputlist = OFL.getOutputlist();
		System.out.println(outputlist.toString());
		return OFL;
	}
	
	@GET
	@Path("/getjobid")
	@Produces("text/plain")
	public String getJobid() throws Exception{
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^");
		System.out.println(this.jobid);
		return this.jobid;
	}
	
	@GET
	@Path("/getprocess")
	@Produces(MediaType.APPLICATION_JSON)
	public Completion getprocess() throws Exception{
		return completion;
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
	

	
	@POST
	@Path("mem")
	@Produces("text/plain")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response genMem (Bwamem mem) throws URISyntaxException{

//		String command = "cd /home/chenhao/hadoop && ./bin/hadoop jar hadoop-examples-1.1.2.jar wordcount hdfs://localhost:9000/tmp/wordcount/word.txt hdfs://localhost:9000/tmp/wordcount/out";
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
	
	@POST
	@Path("checkprocess")
	@Produces(MediaType.APPLICATION_JSON)
	public Response checkProcess (@FormParam("jobid") String clientjobid) throws URISyntaxException{

//		String command = "cd /home/chenhao/hadoop && ./bin/hadoop jar hadoop-examples-1.1.2.jar wordcount hdfs://localhost:9000/tmp/wordcount/word.txt hdfs://localhost:9000/tmp/wordcount/out";
//		String command = "hadoop jar GENE.jar "+mem.getRefname()+" "+mem.getInputpath()+" "+mem.getInputpath();
		GetJobProcess.getjobprogcess(clientjobid, completion);
		String displayMessage = "mapcompletion "+ completion.getMapcompletion() + " reducecompletion "+ completion.getReducecompletion();
		System.out.println("*******************************");
		System.out.println(clientjobid);
		System.out.println("!!!!!!!!!!!!!!!!!!!!");
		System.out.println(this.jobid);
//	    return Response.temporaryRedirect(location).build();
	    return Response.status(201).entity(completion).build();
	}
	
	
}
