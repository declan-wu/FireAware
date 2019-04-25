public class date implements Comparable<date> {
	private int day, month, year;
	private String dateline;
	
	public date(String dateline) {
		String[] parts = dateline.split("/");
		this.month = Integer.parseInt(parts[0]);
		this.day = Integer.parseInt(parts[1]);
		this.year = Integer.parseInt(parts[2]);
		this.dateline = dateline;
	}

	public int getYear() {
		return this.year;
	}
	
	public int getMonth() {
		return this.month;
	}

	public int getDay() {
		return this.day;
	}
	
	public void setDate(int month, int day, int year) {
		this.month = month;
		this.day = day;
		this.year = year;
		this.dateline = month + "/" + day + "/" + year;
	}
	
	public String toString() {
		return dateline;
	}
	
	@Override
	public int compareTo(date other) {
		if (this.year < other.getYear())
			return -1;
		else if(this.year > other.getYear())
			return 1;
		else if (this.month < other.getMonth())
			return -1;
		else if (this.month > other.getMonth())
			return 1;
		else if (this.day < other.getDay())
			return -1;
		else if (this.day > other.getDay())
			return 1;
		else
			return 0;
	}

}
