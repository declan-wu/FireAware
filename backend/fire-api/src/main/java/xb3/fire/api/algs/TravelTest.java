package xb3.fire.api.algs;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.Test;
import java.lang.*;

public class testTravel {
	
	// set up variables
	/**
	 * finland - brasil : 11950 km
	 * brasil - southAfrica : 8195 km
	 * southAfrica - finland : 9005 km
	 * total distance would be 29150 km
	 */
	Building finland = new Building(0, 59.957760, 45.985366, System.currentTimeMillis());
	Building brasil = new Building(1, -15.151672, -52.760659, System.currentTimeMillis());
	Building southAfrica = new Building(2, -19.181490, 24.900684, System.currentTimeMillis());
	Building [] t = {finland, brasil, southAfrica};
	Travel travel = new Travel(t);

	@Test 
	public void testGetTravel() {
		for(int i = 0; i < 3; i++){
			assertTrue( travel.getTravel()[i] == t[i] );
		}     
	}

	@Test
	public void testGetDistance() {
		// not sure what travel.getDistance() will produce
		System.out.println( travel.getDistance() );
        assertTrue( Math.abs(travel.getDistance() - 29150) <= 10 );
	}

	@Test
	public void testGetPreviousTravel() {
        for(int i = 0; i < 3; i++){
			assertTrue( travel.getPreviousTravel()[i] == t[i] );
		}   
	}

	@Test
	public void testSize() {
        assertTrue( travel.getSize() == 3);
	}

	@Test
	public void testSwapBuildings() {
        travel.swapBuildings();
        travel.revertSwap();
        for(int i = 0; i < 3; i++){
			assertTrue( travel.getTravel()[i] == t[i] );
		} 
	}

	@Test
	public void testRevertSwap() {
        travel.swapBuildings();
        travel.revertSwap();
        for(int i = 0; i < 3; i++){
			assertTrue( travel.getTravel()[i] == t[i] );
		} 
	}

	@Test
	public void testSimulateAnnealing2() {
        travel.simulateAnnealing2(2000000);
        assertTrue( Math.abs(travel.getDistance() - 29150) <= 10 );
	}
}
