package practice.algo.recursion;

//import java.io.FileInputStream;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Main problem = new Main();
		problem.run();		
	}
	
	class Station {
		double distance;
		double distanceFromDestination;
		double pricePerGallonInCents;
		
		public Station(double dist, double price, double distFromDest) {
			distance = dist;
			pricePerGallonInCents = price;
			distanceFromDestination = distFromDest;
		}
	}

	Scanner sc = null;
	int T, nCase;
	
	double totalDistance, tankCapacityInGallon, milePerGallon, fuelCostAtStart;
	
	int totalStations;
	Station[] stations = null;
	
	private void run() {
//		try {
//			System.setIn(new FileInputStream("UVa00222_Budget_Travel_in.txt"));
//		}catch(Exception e) {}
		
		sc = new Scanner(System.in);
		for(nCase = 1; (totalDistance = sc.nextDouble()) > 0; nCase++) {
			takeInput();
			findSolution();
			printSolution();
		}
	}

	double minTotalCost;
	
	private void printSolution() {
		System.out.printf("Data Set #%d%nminimum cost = $%.2f%n", nCase, minTotalCost / 100.0);
	}

	private void takeInput() {
		tankCapacityInGallon = sc.nextDouble();
		halfTankCapacity = tankCapacityInGallon / 2.0;
				
		milePerGallon = sc.nextDouble();
		fullTankMiles = tankCapacityInGallon * milePerGallon;
		
		fuelCostAtStart = sc.nextDouble();
		fuelCostAtStart = fuelCostAtStart * 100.0; // In cents
		
		totalStations = sc.nextInt();
		END = totalStations;
		
		stations = new Station[totalStations];
		for(int i = 0; i < totalStations; i++) {
			double dist = sc.nextDouble();
			double price = sc.nextDouble();
			stations[i] = new Station(dist, price, totalDistance - dist);
		}
	}
	
	double fixedCost = 200.0;
	
	private void findSolution() {
		minTotalCost = Double.MAX_VALUE; // Reset old case's result
		travel(0, fuelCostAtStart, fullTankMiles, START);
	}
	
	int START = -1;
	int END;
	double halfTankCapacity;
	double fullTankMiles;
	
	private void travel(int pos, double cost, double distanceCanGo, int lastStop) {
		if(cost > minTotalCost) {
			return;
		}
		
		if(distanceCanGo >= totalDistance || pos >= END) {
			if(cost < minTotalCost) {
				minTotalCost = cost;
			}
			return;
		}
						
		if(pos < stations.length - 1 && distanceCanGo > stations[pos + 1].distance) {
			travel(pos + 1, cost, distanceCanGo, lastStop);
		}
		
		double distanceFromLastStation = stations[pos].distance;
		if(lastStop != START)
			distanceFromLastStation = stations[pos].distance - stations[lastStop].distance;
		
		double newRemainingFuel = tankCapacityInGallon - distanceFromLastStation / milePerGallon;
		if((pos < stations.length - 1 && distanceCanGo < stations[pos + 1].distance) || (pos == stations.length - 1 && distanceCanGo < totalDistance) || newRemainingFuel <= halfTankCapacity) {			
			double requiredFuel = tankCapacityInGallon - newRemainingFuel;
			double thisStationCost = (requiredFuel * stations[pos].pricePerGallonInCents);
			thisStationCost = Double.parseDouble(String.format("%.0f", thisStationCost)); // Round to nearest cent
			double newCost = cost + fixedCost + thisStationCost;
			
			travel(pos + 1, newCost, stations[pos].distance + fullTankMiles, pos);
		}
	}
}
