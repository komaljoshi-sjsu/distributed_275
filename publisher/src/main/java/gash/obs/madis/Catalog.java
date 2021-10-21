package gash.obs.madis;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class Catalog {
	private HashMap<String, Station> map = new HashMap<String, Station>();
	static boolean includeHeader = false;

	public boolean validateID(String id) {
		return (map.containsKey(id));
	}

	public boolean addStation(Station station) {
		if (station == null || station.getId() == null)
			return false;

		if (map.containsKey(station.getId()))
			return true;

		map.put(station.getId(), station);
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		int count = 0;
		if(includeHeader)
			sb.append("# id,name,mesonet,lat,lon,elevation,agl,cit,state,country,active,temperature,timeObs\n");
		for (Station s : map.values()) {
			sb.append(s.id).append(",");
			sb.append(s.name).append(",");
			sb.append(s.mesonet).append(",");
			sb.append(s.lat).append(",");
			sb.append(s.lon).append(",");
			sb.append(s.elevation).append(",");
			sb.append(s.agl).append(",");
			sb.append(s.city).append(",");
			sb.append(s.state).append(",");
			sb.append(s.country).append(",");
			sb.append(s.active).append(",");
			sb.append(s.temperature).append(",");
			sb.append(s.timeObs).append("\n");
			count++;
		}
		System.out.println("Number of lines read: "+count);

		return sb.toString();
	}

	public boolean load(File catFile) {
		if (catFile == null || !catFile.exists())
			return false;

		// TODO open file, read contents

		return true;
	}

	public boolean save(File catFile) {
		if (catFile == null)
			return false;

		try {
			BufferedWriter w;
			if(catFile.exists()) {
				w = new BufferedWriter(new FileWriter(catFile,true));
				includeHeader = false;
			} else {
				w = new BufferedWriter(new FileWriter(catFile));
				includeHeader = true;
			}
			w.write(this.toString());
			w.close();
		} catch (IOException e) {
			// TODO add appropriate error handling
			e.printStackTrace();
		}

		return false;
	}

}
