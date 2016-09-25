package submission.uva.recursion;

import java.util.Scanner;

public class UVa00222 {
	
	public static void main(String[] args) {
		UVa00222 problem = new UVa00222();
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
	
	double totalDistance;
	
	private void run() {
		sc = new Scanner(System.in);
		for(nCase = 1; (totalDistance = sc.nextDouble()) > 0; nCase++) {
			takeInput();
			findSolution();
			printSolution();
		}
	}

	private void printSolution() {
		System.out.printf("Data Set #%d%nminimum cost = $%.2f%n", nCase, minTotalCost / 100.0);
	}

	double tankCapacityInGallon;
	double milePerGallon;
	double fuelCostAtStart;
	
	int totalStations;
	Station[] stations = null;
	
	private void takeInput() {
		tankCapacityInGallon = sc.nextDouble();				
		milePerGallon = sc.nextDouble();		
		fuelCostAtStart = sc.nextDouble();
		totalStations = sc.nextInt();		
		stations = new Station[totalStations]; // Initialize Station Array
		for(int i = 0; i < totalStations; i++) {
			double dist = sc.nextDouble();
			double price = sc.nextDouble();
			stations[i] = new Station(dist, price, totalDistance - dist);
		}
	}
	
	double fixedCost = 200.0; // Snacks Cost
	int ORIGIN = -1;
	int DESTINATION;
	double halfTankCapacity;
	double fullTankMilez;
	
	double minTotalCost; // Result
	
	private void findSolution() {
		halfTankCapacity = tankCapacityInGallon / 2.0;
		fullTankMilez = tankCapacityInGallon * milePerGallon;
		fuelCostAtStart = fuelCostAtStart * 100.0; // In cents
		DESTINATION = totalStations;
		
		minTotalCost = Double.MAX_VALUE; // Reset old case's result
		travel(0, fuelCostAtStart, fullTankMilez, ORIGIN);
	}
	
	private void travel(int pos, double cost, double distanceCanGo, int lastStop) {
		if(cost > minTotalCost) {
			return;
		}
		
		// Base Case 1: Regardless the station number it can safely reach the destination
		// Base Case 2: Reached the destination
		
		if(distanceCanGo >= totalDistance || pos >= DESTINATION) {
			if(cost < minTotalCost) {
				minTotalCost = cost;
			}
			return;
		}
		
		// Case 1: No need fuel: it is not the last station but it can safely reach next station
		
		if(pos < stations.length - 1 && distanceCanGo > stations[pos + 1].distance) {
			travel(pos + 1, cost, distanceCanGo, lastStop);
		}
		
		// Case 2: Need fuel:
		//    a. it is not the last station and cannot reach the next station
		// OR b. it is last station and cannot reach destination
		// OR c. more than half capacity fuel consumed
		
		double distanceFromLastStation = stations[pos].distance;
		if(lastStop != ORIGIN) {
			distanceFromLastStation = stations[pos].distance - stations[lastStop].distance;
		}		
		double newRemainingFuel = tankCapacityInGallon - distanceFromLastStation / milePerGallon;
		if((pos < stations.length - 1 && distanceCanGo < stations[pos + 1].distance) || (pos == stations.length - 1 && distanceCanGo < totalDistance) || newRemainingFuel <= halfTankCapacity) {			
			double requiredFuel = tankCapacityInGallon - newRemainingFuel;
			double thisStationCost = (requiredFuel * stations[pos].pricePerGallonInCents);
			thisStationCost = Double.parseDouble(String.format("%.0f", thisStationCost)); // Round to nearest cent
			double newCost = cost + fixedCost + thisStationCost;
			
			travel(pos + 1, newCost, stations[pos].distance + fullTankMilez, pos);
		}
	}
}