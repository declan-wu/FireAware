package xb3.fire.api.building;

import java.text.SimpleDateFormat;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GeoJson {
	
	// Converts an ArrayList of buildings to GeoJSON format
	/**
	 * converts list of buildings to geojson format
	 * 
	 * @param list of buildings
	 * @return geojson of buildings
	 * @throws JSONException
	 */
	public static JSONObject toGeoJson(List<Building> list) throws JSONException {
		
		// Follows the spec defined: http://geojson.org/
	    JSONObject featureCollection = new JSONObject();
	    featureCollection.put("type", "FeatureCollection");
	        JSONArray featureList = new JSONArray();
	        for (Building building : list) {
	            JSONObject point = new JSONObject();
	            JSONArray coord = new JSONArray("["+building.getLng()+","+building.getLat()+"]");
	            point.put("coordinates", coord);
	            JSONObject feature = new JSONObject();
	            point.put("type", "Point");
	            feature.put("geometry", point);
	            JSONObject date = new JSONObject();    
	            date.put("date", new SimpleDateFormat("dd/MM/yyyy").format(building.getDate()));
	            date.put("id", building.getId());
	            feature.put("properties", date);
	            feature.put("type", "Feature");
	            featureList.put(feature);
	            featureCollection.put("features", featureList);
	        }
	        return featureCollection;
		
	}
	
	
	// Converts an ArrayList of buildings to GeoJSON format
	/**
	 * converts array of buildings to geojson format
	 * 
	 * @param array of buildings
	 * @return geojson
	 * @throws JSONException
	 */
	public static JSONObject toGeoJson(Building[] list) throws JSONException {
		
		// Follows the spec defined: http://geojson.org/
	    JSONObject featureCollection = new JSONObject();
	    featureCollection.put("type", "FeatureCollection");
	        JSONArray featureList = new JSONArray();
	        for (Building building : list) {
	            JSONObject point = new JSONObject();
	            JSONArray coord = new JSONArray("["+building.getLng()+","+building.getLat()+"]");
	            point.put("coordinates", coord);
	            JSONObject feature = new JSONObject();
	            point.put("type", "Point");
	            feature.put("geometry", point);
	            JSONObject date = new JSONObject();    
	            date.put("date", new SimpleDateFormat("dd/MM/yyyy").format(building.getDate()));
	            date.put("id", building.getId());
	            feature.put("properties", date);
	            feature.put("type", "Feature");
	            featureList.put(feature);
	            featureCollection.put("features", featureList);
	        }
	        return featureCollection;
		
	}
}
