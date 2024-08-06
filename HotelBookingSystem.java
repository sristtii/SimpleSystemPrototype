import java.util.*;;

public class HotelBookingSystem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Customer> customers = new ArrayList<>();
		List<Integer> m1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
		List<Integer> m2 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
		// waiting list
		Queue<Customer> swait = new LinkedList<>();
		Queue<Customer> dwait = new LinkedList<>();
		Scanner sc = new Scanner(System.in);
		while (true) {
			displayMenu();
			System.out.println("Enter choice");
			int choice = sc.nextInt();
			System.out.println();
			switch (choice) {
			case 1:
				availablerooms(m1, m2);
				break;
			case 2:
				// booking a room
				bookroom(customers, m1, m2, swait, dwait);
				break;
			case 3:
				// cancellation of a room
				cancelroom(customers, m1, m2, swait, dwait);
				break;
			case 4:// booking history
				history(customers);
				break;
			case 5:// passenger details for admin panel
				customerdetails(customers);
				break;
			case 6:// update customer details
				updatecustomer(customers);
				break;
			case 7:
				// waiting list
				waitinglist(swait, dwait);
				break;
			case 8:
				System.out.println("EXITED....");
				System.exit(0);
				break;
			default:
				System.out.println("Enter Valid Choice:");
				break;
			}
		}
	}

	private static void waitinglist(Queue<Customer> swait, Queue<Customer> dwait) {
		// TODO Auto-generated method stub
		for (Customer c : swait) {
			System.out.println("Name: " + c.name + " RoomNo: " + c.room + " (S/D): " + c.sd);
		}
		System.out.println();
		for (Customer c : dwait) {
			System.out.println("Name: " + c.name + " RoomNo: " + c.room + " (S/D): " + c.sd);
		}
		System.out.println();

	}

	private static void cancelroom(List<Customer> customers, List<Integer> m1, List<Integer> m2, Queue<Customer> swait,
			Queue<Customer> dwait) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("CANCELATION OF ROOM");
		System.out.println("Enter name to cancel room:");
		String name1 = sc.next();
		boolean has = false;// FLAG
		for (Customer c : customers) {
			if (name1.equals(c.name)) {
				has = true;
				switch (c.sd.toLowerCase()) {
				case "s":
					m1.add(c.room);
					Collections.sort(m1);
					// already waiting ppl
					if (!swait.isEmpty()) {
						Customer wait = swait.poll();
						wait.room = m1.remove(0);
						System.out.println("Waiting customer :" + wait.name + " moved on to room : " + wait.room
								+ " (S/D): " + wait.sd);
					}
					break;
				case "d":
					m2.add(c.room);
					Collections.sort(m2);
					// already waiting people
					if (!dwait.isEmpty()) {
						Customer wait = dwait.poll();
						wait.room = m2.remove(0);
						System.out.println("Waiting customer :" + wait.name + " moved on to room : " + wait.room
								+ " (S/D): " + wait.sd);
					}
					break;

				}
				customers.remove(c);
				System.out.println("ROOM CANCELLED");
				break;
			}
		}
		if (!has) {
			System.out.println("ROOM UNABLE TO CANCEL");
			System.out.println("ENTER VALID NAME");
		}

	}

	private static void history(List<Customer> customers) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("BOOKED ROOMS: ");
		System.out.println("enter name:");
		String name1 = sc.next();
		for (Customer c : customers) {
			if (name1.equals(c.name)) {
				System.out.println("Name: " + c.name + " RoomNo: " + c.room + " (S/D): " + c.sd);
				System.out.println();
			}
		}
		for (Customer c : customers) {
			System.out.println("name: " + c.name + " ||Room no: " + c.room + " ||(S/D): " + c.sd);
		}
		System.out.println();

	}

	private static void updatecustomer(List<Customer> customers) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Update customer details:");
		System.out.println("Enter name:");
		String name1 = sc.next();
		System.out.println("data to change: \n1.age?\n2.room?\n3.single/double?\n4.payment method?\n5.none?");
		int coo = sc.nextInt();
		for (Customer cus : customers) {
			if (name1.equals(cus.name)) {
				switch (coo) {
				case 1:
					System.out.println("Enter age to update:");
					int age1 = sc.nextInt();
					cus.age = age1;
					break;
				case 2:
					System.out.println("Enter room no to change:");
					int room1 = sc.nextInt();
					sc.nextLine();
					cus.room = room1;
					break;
				case 3:
					System.out.println("Change to single or double: ");
					String sd1 = sc.next();
					cus.sd = sd1;
					break;
				case 4:
					System.out.println("Enter payment method: cash or UPI: ");
					String pay1 = sc.next();
					cus.pay = pay1;
					break;
				case 5:
					System.out.println("No changes made");
					break;
				default:
					System.out.println("Enter valid choice");
					break;
				}
			}

		} // printing updated value
		for (Customer c : customers) {
			if (name1.equals(c.name)) {
				System.out.println("name: " + c.name);
				System.out.println("age: " + c.age);
				System.out.println("room no: " + c.room);
				System.out.println("single or double room(S/D): " + c.sd);
				System.out.println("Payment method(C/UPI): " + c.pay);
			}
		}

	}

	private static void customerdetails(List<Customer> customers) {
		// TODO Auto-generated method stub
		System.out.println("Customer details");
		for (Customer c : customers) {
			System.out.println("name: " + c.name);
			System.out.println("age: " + c.age);
			System.out.println("room no: " + c.room);
			System.out.println("single or double room(S/D): " + c.sd);
			System.out.println("Payment method(C/UPI): " + c.pay);
			System.out.println();
		}

	}

	private static void bookroom(List<Customer> customers, List<Integer> m1, List<Integer> m2, Queue<Customer> swait,
			Queue<Customer> dwait) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Booking Room\nEnter customer details");
		System.out.println("Enter name: ");
		String name = sc.next();
		System.out.println("Enter age: ");
		int age = sc.nextInt();
		System.out.println("Enter room no: ");
		int room = sc.nextInt();
		System.out.println("Enter single or double room(S/D): ");
		String sd = sc.next();
		System.out.println("Enter Payment method(C/UPI): ");
		String pay = sc.next();
		// check for availability
		Vacant vacant = new Vacant();
		if (vacant.vacant(sd, room, m1, m2)) {
			customers.add(new Customer(name, age, room, sd, pay));
			System.out.println("Room has been booked!!!");
		} else {
			// add if waiting
			System.out.println("BOOKING QUEUED");
			System.out.println("Adding onto waiting list..");
			Customer wait = new Customer(name, age, room, sd, pay);
			if (wait.sd.equalsIgnoreCase("s")) {
				swait.add(wait);
			} else if (wait.sd.equalsIgnoreCase("d")) {
				dwait.add(wait);
			}
		}

	}

	private static void availablerooms(List<Integer> m1, List<Integer> m2) {
		// TODO Auto-generated method stub
		System.out.println("Available Rooms");
		System.out.println("SINGLE-ONE MEMBER(m1): ");
		for (Integer i : m1) {
			System.out.print(i + " ");
			if (i % 4 == 0) {
				System.out.println();
			}
		}
		System.out.println();
		System.out.println("DOUBLE-TWO MEMBER(m2): ");
		for (Integer i : m2) {
			System.out.print(i + " ");
			if (i % 4 == 0) {
				System.out.println();
			}
		}
		System.out.println();

	}

	private static void displayMenu() {
		// TODO Auto-generated method stub
		System.out.println("============MENU============");
		System.out.println(
				"1.Available Rooms\n2.Book a Room\n3.Cancel Room\n4.History(search from name)\n5.Customer Details\n6.Update customer details\n7.Waiting List\n8.Exit");
		System.out.println("============================");
	}

}

//creating a customer class
class Customer {
	String name;
	int age;
	int room;
	String sd;
	String pay;

	// respective constructor
	Customer(String name, int age, int room, String sd, String pay) {
		this.name = name;
		this.age = age;
		this.room = room;
		this.sd = sd;
		this.pay = pay;

	}
}

//creating vacant class
class Vacant {
	public boolean vacant(String sd, int room, List<Integer> m1, List<Integer> m2) {
		switch (sd.toLowerCase()) {
		case "s":
			return m1.remove(Integer.valueOf(room));
		case "d":
			return m2.remove(Integer.valueOf(room));
		}
		return false;
	}
}