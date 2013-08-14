package com.ucl.genelab.ssh;

import java.io.BufferedReader; 
import java.io.InputStream; 
import java.io.InputStreamReader; 
import java.nio.charset.Charset; 
import java.util.ArrayList;
import java.util.Properties; 

import org.apache.log4j.Logger; 

import com.jcraft.jsch.ChannelExec; 
import com.jcraft.jsch.JSch; 
import com.jcraft.jsch.Session; 
 

public class SSHUtil { 
     
     
    private ChannelExec channelExec; 
     
    private Session session = null; 
     
    private int timeout = 60000;
    public String jobid;
    public String buff = "";
    private static final Logger LOG = Logger.getLogger(SSHUtil.class); 
     
    public SSHUtil(SshConfiguration conf) throws Exception 
    { 
        LOG.info("trying to connect to host:" + conf.getHost() + ",username:" + conf.getUsername() + ",password:" + conf.getPassword() + ",port:" + conf.getPort()); 
        JSch jsch = new JSch();  
        session = jsch.getSession(conf.getUsername(), conf.getHost(), conf.getPort()); 
        session.setPassword(conf.getPassword()); 
        Properties config = new Properties(); 
        config.put("StrictHostKeyChecking", "no"); 
        session.setConfig(config); 
        session.setTimeout(timeout);  
        session.connect(); 
    } 
     
     
    public void runCmd(String cmd, String charset) throws Exception 
    { 
        channelExec = (ChannelExec)session.openChannel("exec"); 
        channelExec.setCommand(cmd); 
        channelExec.setInputStream(null); 
        channelExec.setErrStream(System.err); 
        channelExec.connect(); 
        InputStream in = channelExec.getInputStream(); 
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, Charset.forName(charset))); 
        String buf = null; 
        while ((buf = reader.readLine()) != null) 
        { 
            System.out.println(buf); 
            buff = buff +buf;
        } 
        reader.close(); 
        channelExec.disconnect(); 
    } 
    
    public void runCmdout (String cmd, String charset, ArrayList<String> outfilelist) throws Exception 
    {
        channelExec = (ChannelExec)session.openChannel("exec"); 
        channelExec.setCommand(cmd); 
        channelExec.setInputStream(null); 
        channelExec.setErrStream(System.err); 
        channelExec.connect(); 
        InputStream in = channelExec.getInputStream(); 
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, Charset.forName(charset))); 
        String buf = null; 
        while ((buf = reader.readLine()) != null) 
        { 
            System.out.println(buf);
            outfilelist.add(buf);
        } 
        reader.close(); 
        channelExec.disconnect();
    } 
    
    public String getRespond(){
    	return buff;
    }
     
    public void close() 
    { 
        session.disconnect(); 
    } 
} 