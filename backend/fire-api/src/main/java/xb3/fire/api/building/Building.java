package xb3.fire.api.building;

import java.util.Date;

/**
 * @author rishi
 *
 */
public class Building implements Comparable<Building> {
	private int id;
	private double lng;
	private double lat;
	private Date date;
	
	/**
	 * constructs a building
	 * 
	 * @param id
	 * @param lng
	 * @param lat
	 * @param date
	 */
	public Building(int id, double lng, double lat, Date date) {
		super();
		this.id = id;
		this.lng = lng;
		this.lat = lat;
		this.date = date;
	}
	
	
	
	/**
	 * gets a buildings date
	 * 
	 * @return date
	 */
	public Date getDate() {
		return date;
	}
	
	/**
	 * gets the id of a building
	 * 
	 * @return id building id
	 */
	public int getId() {
		return id;
	}
	/**
	 * sets the id of a building
	 * 
	 * @param id building id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	
	/**
	 * gets the longitude
	 * 
	 * @return longitude
	 */
	public double getLng() {
		return lng;
	}
	
	
	/**
	 * sets the longitude
	 * 
	 * @param lng lontitude
	 */
	public void setLng(double lng) {
		this.lng = lng;
	}
	
	
	/**
	 * gets the latitude
	 * 
	 * @return gets latitude
	 */
	public double getLat() {
		return lat;
	}
	
	
	/**
	 * sets the latitude
	 * 
	 * @param latitude
	 */
	public void setLat(double lat) {
		this.lat = lat;
	}
	
	// this is closest in a sense of euclidean distance
	/**
	 * gets the closest building
	 * 
	 * @param travel list of building
	 * @return  a building
	 */
	public Building getClosestBuilding(Building [] travel) {
		double shortestDistance = distanceToBuilding(travel[0]);
		Building closest = travel[0];
		
		for (int index = 1; index < travel.length; index++) {
	        Building current = travel[index];
	        double currentDistance = distanceToBuilding(current);
	        if (currentDistance < shortestDistance) {
	        	shortestDistance = currentDistance;
	        	closest = travel[index];	     
	        }	      
	    }
		return closest;
	}
	
	/**
	 * gets the closest building
	 * 
	 * @param travel list of buildings
	 * @return a building
	 */
	public int getClosestBuildingIndex(Building [] travel) {
		double shortestDistance = distanceToBuilding(travel[0]);
		int idx = 0;
		
		for (int index = 1; index < travel.length; index++) {
	        Building current = travel[index];
	        double currentDistance = distanceToBuilding(current);
	        if (currentDistance < shortestDistance) {
	        	shortestDistance = currentDistance;
	        	idx = index;	     
	        }	      
	    }
		return idx;
	}
	
	
	//this is commented out for testing purpose when using xcoord and ycoord rather than long and lat
	/**
	 * find distance to a building
	 * 
	 * @param building
	 * @return distance in double
	 */
	public double distanceToBuilding(Building building) {
        double lat1 = this.getLat();
        double lon1 = this.getLng();
        double lat2 = building.getLat();
        double lon2 = building.getLat();
        
        
        return distance(lat1, lat2, lon1, lon2, 0.0, 0.0);
    }
	
	/**
	 * distance between two buildings
	 * 
	 * @param lat1
	 * @param lat2
	 * @param lon1
	 * @param lon2
	 * @param el1
	 * @param el2
	 * @return distance between two buildings
	 */
	public static double distance(double lat1, double lat2, double lon1,
	        double lon2, double el1, double el2) {

	    final int R = 6371; // Radius of the earth

	    double latDistance = Math.toRadians(lat2 - lat1);
	    double lonDistance = Math.toRadians(lon2 - lon1);
	    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
	            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
	            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    double distance = R * c * 1000; // convert to meters

	    double height = el1 - el2;

	    distance = Math.pow(distance, 2) + Math.pow(height, 2);

	    return Math.sqrt(distance)/10000;
	}
	



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Building [id=" + id + ", lng=" + lng + ", lat=" + lat + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Building b) {
		if (this.id < b.id)
			return -1;
		else {
			return 1;
		}
	}
	
	
}
