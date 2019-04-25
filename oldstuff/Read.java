import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Rishi Arora, Jennifer Cheng, Esam Haris, Declan Wu
 *
 *
 */
public class Read {
	
	Building[] buildings = new Building[300000];
	
	public void readFile() {
		// Reading file.
		Scanner sc;
		int i = 0;	// Counter to populate building array
		try {
			sc = new Scanner(new File("data/Mandatory_Inspections_By_Fire_Companies.csv"));
			String curr;
			while (sc.hasNextLine()) {
				curr = sc.nextLine();	// current String
				if (i == 0) {
					i++;
					continue;
				}
				String[] x = curr.split(",");	// List of building properties
				/* Index:
				 * 0 is inspection type,
				 * 1 is type of inspection
				 * 2 is borough
				 * 3 is latitude
				 * 4 is longitude
				 * 5 is community district board
				 * 6 is city council district
				 * 7 is inspection id
				 * 8 is date
				 * 9 is building ID #
				 * 10 is building block lot
				 */
				if ( x[3].isEmpty() || x[4].isEmpty() || x[9].isEmpty() ) {
					continue;
				}
				buildings[i] = new Building(x[2], Double.parseDouble(x[3]), 
						Double.parseDouble(x[4]), x[8], Integer.parseInt(x[9]));
				System.out.println(buildings[i]);
				i++;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Read r = new Read();
		r.readFile();
	}
}
