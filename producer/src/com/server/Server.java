package com.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Server {
	final static int portNumber = 8080;
	
	public static void main(String[] args) {
		try(ServerSocket serverSocket = new ServerSocket(portNumber)) {
			JSONParser parser = new JSONParser();
			System.out.println("Server running at "+portNumber);
			while(true) {
				Socket clientSocket = serverSocket.accept();
				
				BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				String line = "";
				StringBuilder question = new StringBuilder();
				while((line = br.readLine())!=null) {
					question.append(line);
				}
				String response = question.toString().trim();
				
				
				JSONObject respObj = (JSONObject) parser.parse(response);
				
				JSONObject message = (JSONObject) respObj.get("message");
				System.out.println(respObj.get("producer")+" says to give following message to topic "+message.get("topicName")+":\n"+message.get("body"));
				
				br.close();
			}
//			
		} catch(IOException | ParseException ioe) {
			System.err.println(ioe.getMessage());
		}
	}

}
