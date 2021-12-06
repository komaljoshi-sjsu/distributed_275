package com.client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONObject;

public class Producer {
	final static int portNumber = 8080;
	final static String hostName = "127.0.0.1";
    private static BufferedReader  input   = null;
    private static PrintWriter out     = null;
    final static String producerName = "Producer_1";
	
	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in)) {
			Socket serverSocket = null;
			String topicName = args[0];
			System.out.println("Hey I am "+producerName);
			
			while(true) {
				serverSocket = new Socket(hostName, portNumber);
				JSONObject job = new JSONObject();
				JSONObject msg = new JSONObject();
				
				String msgText = sc.nextLine();
				msg.put("body", msgText);
				msg.put("id", System.currentTimeMillis());
				msg.put("topicName", topicName);
				job.put("message", msg);
				job.put("producer", producerName);
				out    = new PrintWriter(serverSocket.getOutputStream());
				out.write(job.toJSONString());
				out.flush();
				serverSocket.shutdownOutput();
				out.close();
				serverSocket.close();
			}
		} catch(IOException ioe) {
			System.err.println(ioe.getMessage());
		}
	}
}
