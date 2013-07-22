package com.ucl.genelab.resource;
import com.ucl.genelab.ssh.Shell;
public class connectiontest {

	public static void main(String[] args) throws Exception {
		Shell shell = new Shell("127.0.0.1",22,"username","password");
		String[] abc = {"ls"};
		String[] abc2 = {"touch me.txt"};
		boolean iss = shell.executeCommands(abc);
		System.out.println("*********111************");
		System.out.println(shell.getResponse());
		System.out.println("*********111************");
		System.out.println(iss);
		iss = shell.executeCommands(abc2);
		System.out.println("*********222************");
		System.out.println(shell.getResponse());
		System.out.println("*********222************");
		System.out.println(iss);
		System.out.println("*********222************");
		shell.disconnect();
	}
}
