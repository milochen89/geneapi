package com.ucl.genelab.ssh;

import ch.ethz.ssh2.ChannelCondition;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import com.ucl.genelab.metadata.Completion;
import com.ucl.genelab.resource.Conf;
public class GetJobProcess {
    public static void getjobprogcess (String jobid, Completion completion) {
    	
     try
  {
   /* Create a connection instance */
   Connection conn = new Connection(Conf.IP);
   
   /* Now connect */
   conn.connect();
   /* Authenticate */
   boolean isAuthenticated = conn.authenticateWithPassword(Conf.USERNAME,Conf.PASSWORD);
   if (isAuthenticated == false)
    throw new IOException("Authentication failed. Please check hostname, username and password.");
   /* Create a session */
   Session sess = conn.openSession();
   // sess.execCommand("uname -a && date && uptime && who");
   System.out.println("start exec command.......");
   
   //sess.execCommand("echo /"Text on STDOUT/"; echo /"Text on STDERR/" >&2");
   //sess.execCommand("env");
            sess.requestPTY("bash");
           
            sess.startShell();
           
           
   InputStream stdout = new StreamGobbler(sess.getStdout());
   InputStream stderr = new StreamGobbler(sess.getStderr());
 
   BufferedReader stdoutReader = new BufferedReader(new InputStreamReader(stdout));
   BufferedReader stderrReader = new BufferedReader(new InputStreamReader(stderr));

   PrintWriter out =new PrintWriter(sess.getStdin());
   String command = "cd /home/chenhao/hadoop && ./bin/hadoop job -status "+jobid;
//   out.println("cd /home/chenhao/hadoop && ./bin/hadoop jar hadoop-examples-1.1.2.jar wordcount hdfs://localhost:9000/tmp/wordcount/word.txt hdfs://localhost:9000/tmp/wordcount/out");
   
   out.println(command);

   out.close();
   sess.waitForCondition(ChannelCondition.CLOSED | ChannelCondition.EOF | ChannelCondition.EXIT_STATUS, 2000);
   
   System.out.println("Here is the output from stdout:");
   
   while (true)
   {
    String line = stdoutReader.readLine();
    if (line == null)
    {
     break;
    }
    
    if ( line.indexOf("map() completion:") != -1)
    {
    	System.out.println(line);
    	completion.setMapcompletion( Double.parseDouble(line.substring(line.indexOf("map() completion:")+18)));
    	System.out.println(completion.getMapcompletion());
    }
    if ( line.indexOf("reduce() completion:") != -1)
    {
    	System.out.println(line);
    	completion.setReducecompletion( Double.parseDouble(line.substring(line.indexOf("reduce() completion:")+21)));
    	System.out.println(completion.getReducecompletion());
    	 break;
    }
    if ( line.indexOf("Could not find job") != -1)
    {
    	System.out.println(line);
    	completion.setMapcompletion(-1);
    	System.out.println(completion.getMapcompletion());
    	 break;
    }
    if ( line.indexOf("is not properly formed") != -1)
    {
    	System.out.println(line);
    	completion.setMapcompletion(-1);
    	System.out.println(completion.getMapcompletion());
    	 break;
    }
    if ( line.indexOf("java.lang.NullPointerException") != -1)
    {
    	System.out.println(line);
    	completion.setMapcompletion(-1);
    	System.out.println(completion.getMapcompletion());
    	 break;
    }
    
    System.out.println(line);
    
   }
   /*
   System.out.println("Here is the output from stderr:");
   while (true)
   {
    String line = stderrReader.readLine();
    if (line == null || line.indexOf("Running job") != -1)
     break;
    System.out.println(line);
    
   }
   */
   /* Show exit status, if available (otherwise "null") */
   System.out.println("ExitCode: " + sess.getExitStatus());
   sess.close();/* Close this session */   
   conn.close();/* Close the connection */
 
  }
  catch (IOException e)
  {
   e.printStackTrace(System.err);
   System.exit(2);
  }
     return;
  }
 }