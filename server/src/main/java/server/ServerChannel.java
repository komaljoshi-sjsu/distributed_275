package server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.message.QueryProcessor;
import com.message.proto.QueryProcessorGrpc;

import io.grpc.ManagedChannel;
import io.grpc.Server;
import io.grpc.ServerBuilder;

public class ServerChannel {
	int serverNum;
	
	public ServerChannel(int p) {
		serverNum = p;
	}

	public JSONArray  searchCsv(JSONObject spatial) {
		System.out.println("Request: "+spatial);
		JSONArray result = new JSONArray();
		try {
			File csv = new File("./category.csv");
			Scanner sc = new Scanner(csv);  
			String headerStr = sc.nextLine();
			String header[] = headerStr.split(",");
			int latIndex = -1;
			int lonIndex = -1;
			int eleIndex = -1;
			int temperature = -1;
			for(int i = 0;i<header.length;i++) {
				if("lat".equals(header[i])) {
					latIndex = i;
				} else if("lon".equals(header[i])) {
					lonIndex = i;
				} else if("elevation".equals(header[i])) {
					eleIndex = i;
				} else if("temperature".equals(header[i])) {
					temperature = i;
				}
			}
			System.out.println();
			while (sc.hasNext()) { //returns a boolean value  
				double lat = Double.parseDouble(spatial.get("lat").toString());
				double lon = Double.parseDouble(spatial.get("lon").toString());
				double elevation = Double.parseDouble(spatial.get("elevation").toString());
				
				String values[] = sc.nextLine().split(",");
				
				Double csvLat = Double.parseDouble(values[latIndex]);
				Double csvLon = Double.parseDouble(values[lonIndex]);
				Double csvEle = Double.parseDouble(values[eleIndex]);
				
				if(csvLat>lat && csvLon>lon && csvEle> elevation) {
					JSONObject res = new JSONObject();
					res.put("location", values[1]);
					res.put("temperature", values[temperature]);
					result.add(res);
				}
				
			}   
			sc.close();  //closes the scanner  
		} catch(FileNotFoundException fnf) {
			fnf.printStackTrace();
		}
		return result;
	}

	public static void main(String[] args) {
		
		for(int i=0;i<=2;i++) {
			final int p = i;
			int port = 8080+p;
			ServerChannel s = new ServerChannel(p);
			Server server = ServerBuilder.forPort(port).addService(new QueryProcessor(s,p)).build();
			Thread thread = new Thread() {
				public void run() {
					try {
						server.start();
						System.out.println("Server "+p+" listening on port "+server.getPort());
						server.awaitTermination();
					} catch(InterruptedException | IOException ie) {
						ie.printStackTrace();
					}
				}
			};
			thread.start();
			
		}
		
	}

}
