package com.ucl.genelab.ssh;

public class SshConfiguration { 
    
    private String host; 
     
    private String username; 
     
    private String password; 
     
    private int port; 
 
    
    
    public SshConfiguration(String host, String username, String password,
			int port) {
		super();
		this.host = host;
		this.username = username;
		this.password = password;
		this.port = port;
	}

	public SshConfiguration() {
		// TODO Auto-generated constructor stub
	}

	public String getHost() { 
        return host; 
    } 
 
    public void setHost(String host) { 
        this.host = host; 
    } 
 
    public String getUsername() { 
        return username; 
    } 
 
    public void setUsername(String username) { 
        this.username = username; 
    } 
 
    public String getPassword() { 
        return password; 
    } 
 
    public void setPassword(String password) { 
        this.password = password; 
    } 
 
    public int getPort() { 
        return port; 
    } 
 
    public void setPort(int port) { 
        this.port = port; 
    } 
} 