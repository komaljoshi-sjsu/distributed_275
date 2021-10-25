package server;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class SearchCSV {
	public static JSONArray  searchCsv(JSONObject spatial) {
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
			while (sc.hasNext()) { //returns a boolean value  
				double lat = Double.parseDouble(spatial.get("lat").toString());
//				double lon = Double.parseDouble(spatial.get("lon").toString());
//				double elevation = Double.parseDouble(spatial.get("elevation").toString());
				String cnt = spatial.get("country").toString();
				
				String values[] = sc.nextLine().split(",");
				
				Double csvLat = Double.parseDouble(values[latIndex]);
//				Double csvLon = Double.parseDouble(values[lonIndex]);
//				Double csvEle = Double.parseDouble(values[eleIndex]);
				String csvCnt = values[cIndex].toString();
				
				if(csvLat>lat && csvCnt.equals(cnt)) {
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
}
