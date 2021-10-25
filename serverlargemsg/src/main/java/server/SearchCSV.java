package server;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class SearchCSV {
	public static JSONArray  searchCsv(JSONObject spatial) {
		String latKey =spatial.get("country").toString();
		
		//search map first
		JSONArray cacheResult= LRUCache.get(latKey);
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
			int ctIndex = -1;
			int lonIndex = -1;
			int eleIndex = -1;
			int temperature = -1;
			
			for(int i = 0;i<header.length;i++) {
				if("country".equals(header[i])) {
					ctIndex = i;
				} else if("temperature".equals(header[i])) {
					temperature = i;
				}
			}
			System.out.println();
			while (sc.hasNext()) { //returns a boolean value  
				String cnt = spatial.get("country").toString();
				
				String values[] = sc.nextLine().split(",");
				
				String csvCnt =values[ctIndex].toString();
				
				if(csvCnt.equals(cnt)) {
					JSONObject res = new JSONObject();
					res.put("location", values[1]);
					res.put("temperature", values[temperature]);
					result.add(res);
				}
				
			}  
			LRUCache.put(latKey, result);
			sc.close();  //closes the scanner  
		} catch(FileNotFoundException fnf) {
			fnf.printStackTrace();
		}
		return result;
	}
}
