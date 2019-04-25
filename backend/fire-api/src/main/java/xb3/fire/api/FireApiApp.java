package xb3.fire.api;



import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import xb3.fire.api.algs.Travel;
import xb3.fire.api.building.Building;
import xb3.fire.csv.Parser;

@SpringBootApplication
public class FireApiApp {

	/**
	 * Function that runs the entire application
	 * 
	 */
	public static void main(String[] args) {

		/*
		List<Building> b =  Parser.parseBuildings("data/data.csv");
		
		System.out.println(b.size());
		Building[] build = new Building[b.size()];
		build = b.toArray(build);
		
		Travel travel = new Travel (build);
		
		System.out.print("The initial distance is : ");
		System.out.println(travel.getDistance()); 
		
		System.out.println("The original travel route is : ");
		
		//travel.printTravel();  
		
		System.out.print("The optimal shortest distance is : ");
		System.out.println(travel.simulateAnnealing2(10000));
		System.out.println(" ------ new print Travel ----- ");
		*/
		
		// run the app
		SpringApplication.run(FireApiApp.class, args);

		//travel.printTravel();  


	}

}
