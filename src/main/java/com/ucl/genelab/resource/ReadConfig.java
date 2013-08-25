package com.ucl.genelab.resource;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.io.File;

/**
 * @author Hao Chen
 */
public class ReadConfig {
	
	public static String IP = "128.16.8.148";
    public static int PORT = 22;
    public static String USERNAME = "kpaligia";
    public static String PASSWORD = "dEE87DDn";
	public static String reffilecommand = "cd /mapr/mapr-m3-student/myvolume/genelab/reference/ && ls";
	public static String inputfilecommand = "cd /mapr/mapr-m3-student/myvolume/genelab/input/ && ls";
	public static String outputfilecommand = "cd /mapr/mapr-m3-student/myvolume/genelab/output/ && ls";
	public static String GENEJARPATH = "/mapr/mapr-m3-student/myvolume/genelab/GENE.jar";
    

	static {
        try {
            File f = new File("config.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(f);
            NodeList nl = doc.getElementsByTagName("web-config");

         
            if (!doc.getElementsByTagName("CLUSTERIP").item(0)
                    .getFirstChild().getNodeValue().equals("")) {
                IP = doc.getElementsByTagName("CLUSTERIP").item(0)
                        .getFirstChild().getNodeValue();
            }

            if (!Integer.valueOf(doc
                    .getElementsByTagName("CLUSTERPORT").item(0)
                    .getFirstChild().getNodeValue()).equals("")) {
                PORT = Integer.valueOf(doc
                        .getElementsByTagName("CLUSTERPORT").item(0)
                        .getFirstChild().getNodeValue());
            }

            if (!doc.getElementsByTagName("USERNAME").item(0)
                    .getFirstChild().getNodeValue().equals("")) {
            	USERNAME = doc.getElementsByTagName("USERNAME").item(0)
                        .getFirstChild().getNodeValue();
            }

            if (!doc.getElementsByTagName("PASSWORD").item(0)
                    .getFirstChild().getNodeValue().equals("")) {
                PASSWORD = doc.getElementsByTagName("PASSWORD").item(0)
                        .getFirstChild().getNodeValue();
            }
            if (!doc.getElementsByTagName("REFERENCEFILEPATH").item(0)
                    .getFirstChild().getNodeValue().equals("")) {
            	reffilecommand = "cd "+doc.getElementsByTagName("REFERENCEFILEPATH").item(0)
                        .getFirstChild().getNodeValue()+" && ls";
            }
            if (!doc.getElementsByTagName("INPUTFILEPATH").item(0)
                    .getFirstChild().getNodeValue().equals("")) {
            	inputfilecommand ="cd "+doc.getElementsByTagName("INPUTFILEPATH").item(0)
                        .getFirstChild().getNodeValue()+" && ls";
            }
            if (!doc.getElementsByTagName("OUTPUTFILEPATH").item(0)
                    .getFirstChild().getNodeValue().equals("")) {
            	outputfilecommand ="cd "+ doc.getElementsByTagName("OUTPUTFILEPATH").item(0)
                        .getFirstChild().getNodeValue()+" && ls";
            }
            if (!doc.getElementsByTagName("GENEJARPATH").item(0)
                    .getFirstChild().getNodeValue().equals("")) {
            	GENEJARPATH =doc.getElementsByTagName("GENEJARPATH").item(0)
                        .getFirstChild().getNodeValue();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


	public static String getIP() {
		return IP;
	}


	public static void setIP(String iP) {
		IP = iP;
	}


	public static int getPORT() {
		return PORT;
	}


	public static void setPORT(int pORT) {
		PORT = pORT;
	}


	public static String getUSERNAME() {
		return USERNAME;
	}


	public static void setUSERNAME(String uSERNAME) {
		USERNAME = uSERNAME;
	}


	public static String getPASSWORD() {
		return PASSWORD;
	}


	public static void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}


	public static String getReffilecommand() {
		return reffilecommand;
	}


	public static void setReffilecommand(String reffilecommand) {
		ReadConfig.reffilecommand = reffilecommand;
	}


	public static String getInputfilecommand() {
		return inputfilecommand;
	}


	public static void setInputfilecommand(String inputfilecommand) {
		ReadConfig.inputfilecommand = inputfilecommand;
	}


	public static String getOutputfilecommand() {
		return outputfilecommand;
	}


	public static void setOutputfilecommand(String outputfilecommand) {
		ReadConfig.outputfilecommand = outputfilecommand;
	}


	public static String getGENEJARPATH() {
		return GENEJARPATH;
	}


	public static void setGENEJARPATH(String gENEJARPATH) {
		GENEJARPATH = gENEJARPATH;
	}
}