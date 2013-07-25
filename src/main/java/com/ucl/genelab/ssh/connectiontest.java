package com.ucl.genelab.ssh;
import com.ucl.genelab.resource.*;

public class connectiontest {

	public static void main(String[] args) throws Exception {
		Shell shell = new Shell(Conf.IP,Conf.PORT,Conf.USERNAME,Conf.PASSWORD);
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
