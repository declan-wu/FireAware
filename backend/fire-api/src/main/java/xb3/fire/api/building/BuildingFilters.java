package xb3.fire.api.building;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import xb3.fire.api.algs.Search;

public class BuildingFilters {

	/**
	 * Filters buildings by a date
	 * 
	 * @param list - list of buildings
	 * @param date - a date
	 * @return list<Building> returns a filter building by date
	 */
	public static List<Building> filterBuildingDate (Building[] list, Date date) {
		List<Building> newList = new ArrayList<Building>();
		
		for (Building b : list) {
			if (b.getDate().compareTo(date) < 0) {
				newList.add(b);				
			}
		}
		
		return newList;
		
	}
	
	/**
	 * Finds a building by id
	 * 
	 * @param list - list of buildings
	 * @param String - building ID
	 * @return list<Building> returns a list of buildings
	 */
	public static List<Building> findBuilding(Building[] list, String buildingID) {
		List<Building> output = new ArrayList<Building>();
		output.add(Search.search(list, Integer.parseInt(buildingID)));
		return output;
	}
	
	/**
	 * Filters buildings by many ids
	 * 
	 * @param list - list of buildings
	 * @param List<String> - list of ids
	 * @return list<Building> returns a list of buildings
	 */
	public static List<Building> filterManyBuilds(Building[] list, List<String> ids) {
		List<Building> output = new ArrayList<Building>();
		
		for (String id : ids) {
			output.add(findBuilding(list, id).get(0));
		}
				
		return output;
		
	}
}
