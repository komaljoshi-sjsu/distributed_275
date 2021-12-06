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

public class Consumer {
	final static int portNumber = 8080;
	final static String hostName = "127.0.0.1";
    private static BufferedReader  input   = null;
    private static PrintWriter out     = null;
    final static String consumerName = "Consumer_1";
	
	public static void main(String[] args) {
		try {
			Socket serverSocket = new Socket(hostName, portNumber);
			System.out.println("Hey I am client and I will automatically fire questions");
			out    = new PrintWriter(serverSocket.getOutputStream());
			JSONObject job =  new JSONObject();
			String topics = "";
			//send topic names to server
			for(int i=0;i<args.length;i++) {
				if(i == args.length-1) {
					topics += args[i];
				} else {
					topics += args[i]+",";
				}
			}
			if(topics.length()==0) {
				System.err.println("Please restart Consumer with topic subscription");
				return;
			}
			job.put("type","Consumer");
			job.put("topics", topics);
			out.write(job.toJSONString());
			out.flush();
			serverSocket.shutdownOutput();
			out.close();
			serverSocket.close();
			Scanner sc = new Scanner(System.in);
			while(true) {
				serverSocket = new Socket(hostName, portNumber);
				input = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
				String line = "";
				StringBuilder response = new StringBuilder();
				while((line = input.readLine())!=null) {
					response.append(line);
				}
				System.out.println("Server pushed message: "+response);
				input.close();
				serverSocket.close();
			}

		} catch(IOException ioe) {
			System.err.println(ioe.getMessage());
		}
	}
}
