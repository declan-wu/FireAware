package xb3.fire.api.building;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import xb3.fire.api.algs.Travel;
import xb3.fire.csv.Parser;

@RestController
public class BuildingController {
	 private Building[] b =  Parser.parseBuildings("data/flipped_data.csv");
	

	/**
	 * gets all the buildings
	 * 
	 * @return all buildings
	 * @throws JSONException
	 */
	@CrossOrigin
	@RequestMapping("/api/all")
	public String all() throws JSONException {
		return GeoJson.toGeoJson(b).toString();

	}
	
	/**
	 * returns buildings filtered by date
	 * 
	 * @return returns buildings before a date
	 * @throws JSONException
	 * @throws ParseException
	 */
	@CrossOrigin
	@RequestMapping("/api/filter/date")
	public String filterDate() throws JSONException, ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date d = sdf.parse("03/01/2013");
		return GeoJson.toGeoJson(BuildingFilters.filterBuildingDate(b, d)).toString();

	}
	
	
	/**
	 * finds a buildin by its id
	 * 
	 * @param id building
	 * @return a building
	 * @throws JSONException
	 */
	@CrossOrigin
	@RequestMapping("/api")
	public String getBuilding(@RequestParam("id") String id) throws JSONException {
		if (id == "") return "";
		return GeoJson.toGeoJson(BuildingFilters.findBuilding(b, id)).toString();

	}
	
	
	/**
	 * returns 5 buildings given 5 ids
	 * 
	 * @param id1
	 * @param id2
	 * @param id3
	 * @param id4
	 * @param id5
	 * @return list of buildings in GeoJson format
	 * @throws JSONException
	 */
	@CrossOrigin
	@RequestMapping("/api/multi")
	public String getMulti(@RequestParam("id1") String id1, @RequestParam("id2") String id2, @RequestParam("id3") String id3, @RequestParam("id4") String id4, @RequestParam("id5") String id5) throws JSONException {
		List<String> ids = new ArrayList<String>(){{
			add(id1);
			add(id2);
			add(id3);
			add(id4);
			add(id5);

		}};
	
		return GeoJson.toGeoJson(BuildingFilters.filterManyBuilds(b, ids)).toString();

	}
	
	/**
	 * returns most optimal path between 5 buildings
	 * 
	 * @param id1
	 * @param id2
	 * @param id3
	 * @param id4
	 * @param id5
	 * @return a path between the 5 buildings
	 * @throws JSONException
	 */
	@CrossOrigin
	@RequestMapping("/api/path")
	public Building[] getPath(@RequestParam("id1") String id1, @RequestParam("id2") String id2, @RequestParam("id3") String id3, @RequestParam("id4") String id4, @RequestParam("id5") String id5) throws JSONException {
		List<String> ids = new ArrayList<String>(){{
			add(id1);
			add(id2);
			add(id3);
			add(id4);
			add(id5);
		}};
		
		Building[] build = new Building[5];
		build = BuildingFilters.filterManyBuilds(b, ids).toArray(build);
		
		Travel travel = new Travel (build);
		System.out.println("---------");

		travel.printTravel();
		travel.simulateAnnealing2(2000000);
		//start temp
		// iter
		//cooling
		System.out.println("---------");

		travel.printTravel();  
		System.out.println("---------");

		
		
		return travel.getTravel();

	}
}
