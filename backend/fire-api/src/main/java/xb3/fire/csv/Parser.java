package xb3.fire.csv;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import xb3.fire.api.algs.Merge;
import xb3.fire.api.building.Building;
import xb3.fire.api.building.BuildingFilters;

public class Parser {
	/**
	 * reads csv and creates building array
	 * 
	 * @param buildingFile
	 * @return list of buildings
	 */
	public static Building[] parseBuildings(String buildingFile) {
		
		Path pathToFile = Paths.get(buildingFile);
		List<Building> buildings = new ArrayList<Building>();
		Set<String> buildingSet = new HashSet<String>();
		
		try (BufferedReader br = Files.newBufferedReader(pathToFile)){
			String line = br.readLine();
			while (line != null) {
				String[] cells = line.split(",");
				//System.out.println(cells[0]);
				if ((cells[3].length() == 0) || (cells[4].length() == 0 || (buildingSet.contains(cells[9])))) {
					line = br.readLine();
					continue;
				}
				buildingSet.add(cells[9]);
				// Note we're parsing we MM then dd!!!
				Date date = new SimpleDateFormat("MM/dd/yyyy").parse(cells[8]);
				Building b = new Building(Integer.parseInt(cells[9]), Double.parseDouble(cells[4]),  Double.parseDouble(cells[3]), date);
				buildings.add(b);
				line = br.readLine();
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Building[] build = new Building[buildings.size()];
		build = buildings.toArray(build);
		
		// sort
		Merge.sort(build, build.length);
		return build;
			
	}
}
