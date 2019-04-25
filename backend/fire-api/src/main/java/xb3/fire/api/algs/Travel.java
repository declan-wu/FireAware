package xb3.fire.api.algs;

import java.util.Arrays;
import java.util.Random;

import xb3.fire.api.building.Building;

public class Travel {
	/**
	 * @author Declan Wu
	 */

	private Building [] travel;
	private Building [] previousTravel;
	private int size;
	
	/**
	 * Function to construct a new travel route
	 * 
	 * @param travel - the list of buildings to travel
	 */
	public Travel(Building [] travel) {
		this.travel = travel;
		this.previousTravel = Arrays.copyOf(travel, travel.length);
		this.size = travel.length;
	}
	
	
	/**
	 * Function to get the current travel route
	 * 
	 * @return return the state variable representing the travel route
	 */
	public Building[] getTravel() {
		return travel;
	}
	
	/**
	 * To print the current travel route
	 * 
	 */
	public void printTravel() {
		for (int index = 0; index < size; index++) {
	        Building current = travel[index];	 
	        System.out.println(current);
	    }
	}

	/**
	 * Function to get the previous travel route
	 * 
	 * @return return the state variable - previous travel route 
	 */
	public Building[] getPreviousTravel() {
		return previousTravel;
	}


	/**
	 * Function to get the size of this travel route, i.e how many buildings are there in the route
	 * 
	 * @return return an integer value presents the number of buildings
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Function to generate random index within the range of (0..size)
	 * 
	 * @return return integer value within (0..size)
	 */
	public int generateRandomIndex() {
		Random rand = new Random(); 
		int min = 0;
		int max = size;
		return rand.nextInt(max - min) + min;
	}
	
	/**
	 * To swap two buildings based on two random index
	 * 
	 */
	public void swapBuildings() {
	    int a = generateRandomIndex();
	    int b = generateRandomIndex();
	    previousTravel = Arrays.copyOf(travel, travel.length);	//keep track of the original travel, in case we want to revert it.  
	    Building x = travel[a];
	    Building y = travel[b];
	    travel[a] = y;
	    travel[b] = x;
	}
	
	/**
	 * To revert the swap of two buildings 
	 * 
	 */
	public void revertSwap() {
	    travel = Arrays.copyOf(previousTravel, previousTravel.length);
	}
	
	/**
	 * calculate the total distance of the current route
	 * @return double value represents the total distance 
	 */
	
	public double getDistance() {
	    double distance = 0;
	    for (int index = 0; index < size; index++) {
	        Building starting = travel[index];
	        Building destination;
	        if (index + 1 < size) {
	            destination = travel[index + 1];
	        } else {
	            destination = travel[0];
	        }
	        
	        distance += starting.distanceToBuilding(destination);
	    }
	    return distance;
	}
	
	/**
	 * for small solution spaces it’s better to lower the starting temperature and increase the cooling rate, as it will reduce the simulation time, without lose of quality
	 * for bigger solution spaces please choose the higher starting temperature and small cooling rate, as there will be more local minima
	 * always provide enough time to simulate from the high to low temperature of the system
	 * @param startingTemperature - the starting temperature
	 * @param numberOfIterations - the number of iterations we want
	 * @param coolingRate - the rate of how we cool the temperature down 
	 */
	public double simulateAnnealing(double startingTemperature, int numberOfIterations, double coolingRate) {
		// generateInitialTravel();
	    
		double t = startingTemperature;
	    double bestDistance = getDistance();
    
	    for (int i = 0; i < numberOfIterations; i++) {
	        if (t > 0.1) {
	        	swapBuildings();
	    	    double currentDistance = getDistance();
	    	    
	    	    // if the move is good, accept the move
	    	    if (currentDistance < bestDistance) {
	    	        bestDistance = currentDistance;
	    	    } 
	    	    // else if the move is bad, and the probability is low, we revert the swap
	    	    else if (Math.exp((bestDistance - currentDistance) / t) < Math.random()) {
	    	        revertSwap();
	    	    }
	    	    
	    	    t *= coolingRate; 
	        } else {
	        	// with low temperatures the optimization differences are almost not visible
	            continue;
	        }
	    }    
	    return bestDistance;	    
	}
	
	/**
	 * a modified version of simulate annealing where we only have number of iterations as input to simplify things 
	 * @param startingTemperature - the starting temperature
	 * @param numberOfIterations - the number of iterations we want
	 * @param coolingRate - the rate of how we cool the temperature down 
	 */
	public double simulateAnnealing2(int numberOfIterations) {
		double bestDistance = getDistance();   
	    for (int i = 0; i < numberOfIterations; i++) {
	        swapBuildings();
	        double currentDistance = getDistance();	    
	    	// if the move is good, accept the move
	   	    if (currentDistance < bestDistance) {
	   	        bestDistance = currentDistance;
	   	    } 
	   	    // else if the move is bad,  we revert the swap
    	    else {	    	        
    	    	revertSwap();
	    	}
	    }	
	    return bestDistance;
	}
	

	
	

	
}

