package com.ucl.genelab.ssh;

import java.io.BufferedReader; 
import java.io.InputStream; 
import java.io.InputStreamReader; 
import java.nio.charset.Charset; 
import java.util.ArrayList;
import java.util.Properties; 

import org.apache.log4j.Logger; 

import com.jcraft.jsch.ChannelExec; 
import com.jcraft.jsch.ChannelSftp; 
import com.jcraft.jsch.JSch; 
import com.jcraft.jsch.Session; 
 

public class SSHUtil { 
     
    private ChannelSftp channelSftp; 
     
    private ChannelExec channelExec; 
     
    private Session session = null; 
     
    private int timeout = 60000;
    public String jobid;
    public String buff = "";
    private static final Logger LOG = Logger.getLogger(SSHUtil.class); 
     
    public SSHUtil(SshConfiguration conf) throws Exception 
    { 
        LOG.info("trying to connect to host:" + conf.getHost() + ",username:" + conf.getUsername() + ",password:" + conf.getPassword() + ",port:" + conf.getPort()); 
        JSch jsch = new JSch(); // 创建JSch对象  
        session = jsch.getSession(conf.getUsername(), conf.getHost(), conf.getPort()); // 根据用户名，主机ip，端口获取一个Session对象  
        session.setPassword(conf.getPassword()); // 设置密码  
        Properties config = new Properties(); 
        config.put("StrictHostKeyChecking", "no"); 
        session.setConfig(config); // 为Session对象设置properties  
        session.setTimeout(timeout); // 设置timeout时间  
        session.connect(); // 通过Session建立链接  
    } 
     
    public void download(String src, String dst) throws Exception 
    { 
        channelSftp = (ChannelSftp)session.openChannel("sftp"); 
        channelSftp.connect(); 
        channelSftp.get(src, dst, new FileProgressMonitor(), ChannelSftp.OVERWRITE); 
        channelSftp.quit(); 
    } 
     
    public void upload(String src, String dst) throws Exception 
    { 
        channelSftp = (ChannelSftp)session.openChannel("sftp"); 
        channelSftp.connect(); 
        channelSftp.put(src, dst, new FileProgressMonitor(), ChannelSftp.OVERWRITE); 
        channelSftp.quit(); 
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
    
    public void runCmdHadoop(String cmd, String charset) throws Exception 
    { 
        channelExec = (ChannelExec)session.openChannel("exec"); 
        channelExec.setCommand(cmd); 
        channelExec.setInputStream(null); 
        channelExec.setErrStream(System.err); 
        channelExec.connect(); 
        InputStream in = channelExec.getInputStream(); 
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, Charset.forName(charset)));
        
        byte[] tmp=new byte[1024];
        while(true){
          while(in.available()>0){
            int i=in.read(tmp, 0, 1024);
            if(i<0)break;
            System.out.print(new String(tmp, 0, i));
          }
          if(channelExec.isClosed()){
              System.out.println("exit-status: "+channelExec.getExitStatus());
              break;
            }
            try{Thread.sleep(1000);}catch(Exception ee) {ee.printStackTrace();}
          }
        
        /*
        String buf = null; 
        while ((buf = reader.readLine()) != null) 
        { 
        	LOG.info("buf="+buf);
        	jobid = buf.substring(buf.indexOf("Running job")+12,buf.indexOf("Running job")+33);
            System.out.println(buf); 
            buff = buff +buf;
        } 
        */
        LOG.info("jobid="+jobid);
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