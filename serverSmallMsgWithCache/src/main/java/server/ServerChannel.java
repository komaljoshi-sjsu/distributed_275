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
		String cntKey =spatial.get("country").toString();
		Double latKey =Double.parseDouble(spatial.get("lat").toString());
		//search map first
		JSONArray cacheResult= LRUCache.get(cntKey);
		if(cacheResult!=null && cacheResult.size()>0) {
			return cacheResult;
		}
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
			int cIndex = -1;
			for(int i = 0;i<header.length;i++) {
				if("lat".equals(header[i])) {
					latIndex = i;
				} else if("lon".equals(header[i])) {
					lonIndex = i;
				} else if("elevation".equals(header[i])) {
					eleIndex = i;
				} else if("temperature".equals(header[i])) {
					temperature = i;
				} else if("country".equals(header[i])) {
					cIndex = i;
				}
			}
			System.out.println();
			//search cache
			if(cacheResult!=null && cacheResult.size()>0) {
				JSONArray resCacheArr = new JSONArray();
				for(Object obj: cacheResult) {
					JSONObject res = new JSONObject();
					JSONObject ob = (JSONObject) obj;
					double csvEl = Double.parseDouble(ob.get("elevation").toString());
					if(latKey >= csvEl) {
						res.put("location", ob.get("location"));
						res.put("temperature", ob.get("temperature"));
						res.put("elevation", ob.get("elevation"));
						resCacheArr.add(res);
					}
				}
				LRUCache.put(cntKey, resCacheArr);
				return resCacheArr;
			}
			while (sc.hasNext()) { //returns a boolean value  
				double lat = Double.parseDouble(spatial.get("lat").toString());
				double lon = Double.parseDouble(spatial.get("lon").toString());
				double elevation = Double.parseDouble(spatial.get("elevation").toString());
				String cnt = spatial.get("country").toString();
				
				String values[] = sc.nextLine().split(",");
				
				Double csvLat = Double.parseDouble(values[latIndex]);
				Double csvLon = Double.parseDouble(values[lonIndex]);
				Double csvEle = Double.parseDouble(values[eleIndex]);
				String csvCnt = values[cIndex].toString();
				
				if(csvLat>lat  && csvCnt.equals(cnt)) {
					JSONObject res = new JSONObject();
					res.put("location", values[1]);
					res.put("temperature", values[temperature]);
					result.add(res);
				}
				
			}  
			LRUCache.put(cntKey, result);
			sc.close();  //closes the scanner  
		} catch(FileNotFoundException fnf) {
			fnf.printStackTrace();
		}
		return result;
	}

	public static void main(String[] args) {
		//init cache
		LRUCache cache = new LRUCache(10000,0);
		
		for(int i=0;i<1;i++) {
			final int p = i;
			int port = 9090+p;
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
