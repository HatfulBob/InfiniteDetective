import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import NameGenerator.NameGenerator;

public class Game {

	public static ObjectOutputStream outputStream = null;
	public static ObjectInputStream inputStream;
	public static int numOfRooms = 9;
	public static int numOfGuests = 4;// How many guests/suspects there are gonna be
	public static Room[] rooms = new Room[numOfRooms];
	ArrayList<Person> guests = new ArrayList<Person>();
	static Random random = new Random();
	ArrayList<String> names = new ArrayList<String>();

	public Game() {
		NameGenerator.generateNames(numOfGuests);
		fillRooms(true);
		createGuests();
		populateRooms();
		simulate();
	}

	void simulate() {
		int murderer = guests.size();
		int victim = guests.size();
		for (int j = 0; j < guests.size(); j++) {
			if (guests.get(j).isMurderer()) {
				murderer = j;
				break;
			}
		}
		for (int j = 0; j < guests.size(); j++) {
			if (guests.get(j).isVictim()) {
				victim = j;
				break;
			}
		}
		while (!(guests.get(victim).getDeceased())) {
			// Keep looping the day until he dies.
			for (int i = 0; i < 9; i++) {
				System.out.println("--" + (i + 1) + ":00PM--");
				for (int j = 0; j < guests.size(); j++) {
					guests.get(j).considerMoving(i, guests.get(j).getRoom());
				}
				if (guests.get(murderer).getWeapon() == null && random.nextBoolean() == true) {
					guests.get(murderer).takeWeapon(guests.get(murderer).getRoom());
					System.out.println("The " + rooms[guests.get(murderer).getRoom()].getWeaponName() + " is gone!");
				}
				if (!(guests.get(murderer).getWeapon() == null)
						&& (guests.get(murderer).getRoom() == guests.get(victim).getRoom())
						&& numOfPeopleInRoom(guests.get(murderer).getRoom()) <= 2
						&& !(guests.get(victim).getDeceased())) {
					guests.get(victim).die((byte) i + 1);
					System.out.println("A murder has occured!");
				}
			}
			if (!(guests.get(victim).getDeceased())) {
				flushInventory();
			}
		}
		// System.out.println("Hark! " + guests.get(victim).getName() + " was
		// found killed at "+guests.get(victim).getDeathTime()+"!");
		System.out.println("Hark! " + guests.get(victim).getName() + " was found dead!!");
		System.out.println("Foul play is suspected! Find the murderer and put them to justice!");
	}

	private void flushInventory() {
		for (int j = 0; j < guests.size(); j++) {
			guests.get(j).weapon = null;
		}
		fillRooms(false);
	}

	private int numOfPeopleInRoom(byte room) {
		int output = 0;
		for (int j = 0; j < guests.size(); j++) {
			if (guests.get(j).getRoom() == room) {
				output++;
			}
		}
		return output;
	}

	void createGuests() {
		System.out.print("Inviting guests");
		Random random = new Random();
		for (int i = 0; i < (numOfGuests); i++) {
			guests.add(new Suspect("Guy #" + i));
			System.out.print(".");
		}
		((Suspect) guests.get(random.nextInt(guests.size()))).iDidIt();
		guests.add(new Victim("Deady McDeadFace"));
		Collections.shuffle(guests);
		System.out.println(".done!");
	}

	void fillRooms(boolean firstTime) {
		if (firstTime)
			System.out.print("Creating rooms...");
		rooms[0] = new Room("Conservatory", new Weapon("Telescope"));
		rooms[1] = new Room("Billiards Room", new Weapon("Pool stick"));
		rooms[2] = new Room("Library", new Weapon("Heavy book"));
		rooms[3] = new Room("Study", new Weapon("Letteropener"));
		rooms[4] = new Room("Hall", new Weapon("Antique Firearm"));
		rooms[5] = new Room("Lounge", new Weapon("Bottle of Shandy"));
		rooms[6] = new Room("Dining Room", new Weapon("Dining knife"));
		rooms[7] = new Room("Kitchen", new Weapon("Chef's Knife"));
		rooms[8] = new Room("Ballroom", new Weapon("Ball"));
		if (firstTime)
			System.out.println(".done!");
	}

	void populateRooms() {
		System.out.print("Populating rooms...");
		for (int j = 0; j < guests.size(); j++) {
			guests.get(j).setRoom((byte) random.nextInt(rooms.length));
		}
		System.out.println(".done!");
	}

	
}
