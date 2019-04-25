import java.util.LinkedList;

public class Building implements Comparable<Building> {
	private String borough;
	private int bID, totalInspections;
	private double[] coordinate = new double[2];
	private LinkedList<date> inspectionsList = new LinkedList<date>();

	public Building(String borough, double latitude, double longitude, String dateline, int bID) {
		this.bID = bID;
		this.borough = borough;
		this.coordinate[0] = latitude;
		this.coordinate[1] = longitude;
		this.inspectionsList.addFirst(new date(dateline));
		this.totalInspections = 1;
	}

	public double[] getCoordinate() {
		return this.coordinate;
	}

	public int getBuildingID() {
		return this.bID;
	}

	public String getBorough() {
		return this.borough;
	}

	public Object[] getAllInspections() {
		return this.inspectionsList.toArray();
	}

	public int totalInspections() {
		return this.totalInspections;
	}

	public date lastInspected() {
		return this.inspectionsList.getFirst();
	}

	public void setNewDate(String dateline) {
		this.inspectionsList.addFirst(new date(dateline));
		this.totalInspections++;
	}

	@Override
	public int compareTo(Building that) {
		if (this.bID < that.bID)
			return -1;
		else if (this.bID > that.bID)
			return 1;
		return 0;
	}

}
