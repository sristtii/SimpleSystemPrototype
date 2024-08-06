
import java.util.*;

public class AirlineReservation {

	public static void main(String[] args) {
		List<Passenger> passengers = new ArrayList<>();
		List<Integer> business = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12));
		List<Integer> economy = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12));
		// TODO Auto-generated method stub
		Queue<Passenger> bwait = new LinkedList<>();// business class waiting
		Queue<Passenger> ewait = new LinkedList<>();// economy class waiting
		Scanner sc = new Scanner(System.in);
		while (true) {
			// normal display menu bar
			System.out.println("==============MENU===============");
			System.out.println("1.Seat Availability");
			System.out.println("2.Ticket Reservation ");
			System.out.println("3.Passenger Details");
			System.out.println("4.Cancellation of Ticket");
			System.out.println("5.Waiting Status");
			System.out.println("6.Download Ticket");
			System.out.println("7.Exit");
			System.out.println("=================================");
			System.out.println("Enter your choice: ");

			int choice = sc.nextInt();
			// switch case to select menu
			switch (choice) {
			// seats that are available
			case 1:// NORMAL PRINTING OF SEATS
				System.out.println("Seat Availability");
				System.out.println("BUSINESS:");
				for (Integer i : business) {
					System.out.print(i + " ");
					if (i % 3 == 0) {
						System.out.println();
					}
				}
				System.out.println();
				System.out.println("ECONOMY");
				for (Integer i : economy) {
					System.out.print(i + " ");
					if (i % 3 == 0) {
						System.out.println();
					}
				}
				System.out.println();
				break;
			// reservation of that ticket
			case 2:
				System.out.println("Ticket reservation\nEnter passenger details:");
				System.out.println("Enter ID: ");
				int id = sc.nextInt();
				sc.nextLine();
				System.out.println("Enter Name: ");
				String name = sc.next();
				System.out.println("Enter flight class(B/E): ");
				String airclass = sc.next();
				System.out.println("Enter seat number: ");
				int seat = sc.nextInt();

				Check available = new Check();
				// pass the parameter into the object creation with no dt
				if (available.check(airclass, seat, business, economy)) {
					System.out.println("TICKET BOOKED......");
					// add details onto passenger list
					passengers.add(new Passenger(id, name, airclass, seat));
				} else {
					// later add the waiting data onto this list
					System.out.println("CANT BOOK TICKET PLZ WAIT..");
					System.out.println("Adding onto waiting list..");
					Passenger wait = new Passenger(id, name, airclass, seat);
					// check airclass to add to queue
					if (airclass.equalsIgnoreCase("B")) {
						bwait.add(wait);
					} else if (airclass.equalsIgnoreCase("e")) {
						ewait.add(wait);
					}
				}
				break;
			// just print the passenger details for the admin purposes
			case 3:
				System.out.println("Passenger Details:");
				// using for each loop to iterate through ist of passengers
				for (Passenger p : passengers) {
					System.out.println("ID: " + p.id);
					System.out.println("Name: " + p.name);
					System.out.println("Airclass: " + p.airclass);
					System.out.println("SeatNo.: " + p.seat);
					System.out.println();
				}
				break;
			// cancellation of the ticket
			case 4:
				System.out.println("Cancellation of the Ticket");
				System.out.println("Enter the ticket ID:");
				// once we get the ticket id we get the passenger details
				int cancel = sc.nextInt();
				boolean found = false;
				for (Passenger p : passengers) {
					if (cancel == p.id) {
						found = true;
						switch (p.airclass.toLowerCase()) {
						case "b":
							business.add(p.seat);
							Collections.sort(business);
							// Handling the queue of the waiting list for business class
							if (!bwait.isEmpty()) {
								Passenger wait = bwait.poll();// top mist passenger
								wait.seat = business.remove(0);
								System.out.println(
										"Waiting passenger ID " + wait.id + " moves to (cancelled)seat " + wait.seat);
							}
							break;
						case "e":
							economy.add(p.seat);
							Collections.sort(economy);
							// Handling the queue of the waiting list for economy class
							if (!ewait.isEmpty()) {
								Passenger wait = ewait.poll();
								wait.seat = economy.remove(0);
								System.out.println(
										"Waiting passenger ID " + wait.id + " moves to (cancelled)seat " + wait.seat);
							}

							break;
						}
						passengers.remove(p);
						System.out.println("TICKET CANCELLED...");
						break;
					}
				}
				if (!found) {
					System.out.println("TICKET UNABLE TO CANCEL");
					System.out.println("Enter valid ID to cancel");
				}

				break;
			case 5:// waiting list = first com first serve hence FIFO==Queue
				System.out.println("Waiting Status:");

				System.out.println("BUSINESS CLASS WAITING LIST:");
				if (bwait.isEmpty()) {
					System.out.println("No waiting passengers.");
				} else {
					for (Passenger p : bwait) {
						System.out.println("ID: " + p.id);
						System.out.println("Name: " + p.name);
						System.out.println("Airclass: " + p.airclass);
						System.out.println("SeatNo.: " + p.seat);
						System.out.println("WAITING....");
					}
				}
				System.out.println("ECONOMY CLASS WAITING LIST:");
				if (ewait.isEmpty()) {
					System.out.println("No waiting passengers.");
				} else {
					for (Passenger p : ewait) {
						System.out.println("ID: " + p.id);
						System.out.println("Name: " + p.name);
						System.out.println("Airclass: " + p.airclass);
						System.out.println("SeatNo.: " + p.seat);
						System.out.println("WAITING....");
					}
				}

				break;
			case 6:// displaying the details for ticket
				System.out.println("Downloading Ticket");
				System.out.println("Enter ID to download: ");
				int downloadid = sc.nextInt();
				for (Passenger p : passengers) {
					if (p.id == downloadid) {
						System.out.println("ID: " + p.id);
						System.out.println("Name: " + p.name);
						System.out.println("Airclass: " + p.airclass);
						System.out.println("SeatNo.: " + p.seat);
						System.out.println("DOWNLOADED....");
					}
				}
				break;
			// normal exiting of case 7
			case 7:
				System.out.println("EXITED...");
				System.exit(0);
				break;
			// incase if invalid choice entered
			default:
				System.out.println("Enter the Valid Choice");
				break;

			}
		}

	}

}

//for the second method creating a class check
class Check {
	// passing arguments
	public boolean check(String airclass, int seat, List<Integer> business, List<Integer> economy) {
		switch (airclass.toLowerCase()) {// identify class of flight
		case "b":
			// taking an int seat out
			return business.remove(Integer.valueOf(seat));
		case "e":
			// similar to above
			return economy.remove(Integer.valueOf(seat));
		default:
			return false;
		}
	}
}

//create a class for Passengers
class Passenger {
	int id;
	String name;
	String airclass;
	int seat;

	// construstor for that object
	Passenger(int id, String name, String airclass, int seat) {
		this.airclass = airclass;
		this.id = id;
		this.name = name;
		this.seat = seat;
	}
}
