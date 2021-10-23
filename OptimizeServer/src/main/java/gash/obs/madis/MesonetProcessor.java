package gash.obs.madis;

import java.awt.Rectangle;
import java.io.File;
import java.io.FileFilter;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class MesonetProcessor {

	/** file name prefix for general data */
	public static final String sGeneralTag = "madis-mesonet";

	public MesonetProcessor(File catalogF, File outputDir) {
		// TODO read catalog
		// TODO read data

		// TODO if needed, save catalog w/ new stations
	}

	/**
	 * assumption: data is received once every 15 minutes or so.
	 * 
	 * This will perform batch processing.
	 * 
	 * @param args
	 */
	public static final void main(String[] args) {
//		if (args.length != 3) {
//			System.out.println("Usage: dataSource catalogFile outputDir");
//			System.exit(2);
//		}

		File dataSource = new File("./data.gz");
		MesonetReader mr = new MesonetReader();
		mr.info(dataSource);
		File catF = new File("./category.csv");
		File outdir = new File("./output.csv");

		// filters
		Date startDate = null;
		Date endDate = null;
		Rectangle region = null;
		Set<String> stationIDs = null;

		System.out.println("\n\nSource: " + dataSource + "\nCatalog: " + catF.getAbsolutePath() + "\nOutput: "
				+ outdir.getAbsolutePath());

		long startTime = System.currentTimeMillis();
		try {
			Catalog cat = new Catalog();
			if (!cat.load(catF)) {
				System.out.println("-- new catalog file created");
			}

			MesonetReader rawReader = new MesonetReader();

			/**
			 * note use readFile() to perform the same steps
			 */

			if (!dataSource.exists())
				return;
			if (dataSource.isFile()) {
				List<Station> stations = rawReader.extractCatalog(dataSource);
				if (stations != null) {
					for (Station s : stations)
						cat.addStation(s);
				}

				List<MesonetData> data = rawReader.extract(dataSource, startDate, endDate, region, stationIDs);
				System.out.println("processed " + data.size() + " entries");

				// now do something with the data
				// 1. send to the cluster or cloud

//				for (MesonetData d : data) {
//					// TODO do something other than print!
//					System.out.println("Obs: " + d.getStationID() + " T = " + d.getTemperature() + ", WS = "
//							+ d.getWindSpeed() + ", WD = " + d.getWindDir() + ", RH = " + d.getRelHumidity());
//				}
			} else {
				FileFilter filter = new FileFilter() {
					public boolean accept(File pathname) {
						return (pathname.isFile() && !pathname.getName().startsWith(".")
								&& !pathname.getName().endsWith(".gz"));
					}
				};

				// TODO walk through accepted files and process
				System.out.println("TODO: process files");

			}

			// save catalog
			cat.save(catF);

			long stopTime = System.currentTimeMillis();
			System.out.println(
					"MADIS Mesonet - total processing time is " + ((stopTime - startTime) / 1000.0) + " seconds");
		} catch (Throwable t) {
			System.out.println(
					"Unable to process mesowest data in " + dataSource.getAbsolutePath() + ": " + t.getMessage());
		}
	}
}
