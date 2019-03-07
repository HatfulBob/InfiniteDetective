
public class Person {
	String name;
	byte room;// Which room they are in
	boolean justArrived = true;// People must stay in a room for at least one
								// hour before even thinking of leaving it.
	boolean deceased = false;
	Weapon weapon = null;// What weapon they have (don't necessarily need to
	// have one)
	int[] rooms = new int[Game.numOfRooms];// keep track of where they were.

	byte deathTime;// the hour which they died.

	public Person(String name) {
		this.name = name;
	}

	String getName() {
		return name;
	}

	byte getRoom() {
		return room;
	}

	void setRoom(byte r) {
		room = r;
	}

	void considerMoving(int time, int currentRoom) {
		if (justArrived || deceased) {
			justArrived = false;
			return;
		} else if (Game.random.nextInt(100) <= 40) {
			rooms[time] = currentRoom;
			changeRoom();
		}
	}

	void changeRoom() {
		// moving clockwise/right
		setRoom((byte) Game.random.nextInt(Game.rooms.length));
		justArrived = true;
	}

	public String toString() {
		return getName();
	}

	boolean getDeceased() {
		return deceased;
	}

	boolean isVictim() {
		return false;
	}

	boolean isMurderer() {
		return false;
	}

	void setWeapon(Weapon w) {
		weapon = w;
	}

	Weapon getWeapon() {
		return weapon;
	}

	public void takeWeapon(byte room) {
		weapon = Game.rooms[room].takeWeapon();

	}

	void die(int i) {
		deceased = true;
		this.deathTime = (byte) i;
	}

	public byte getDeathTime() {
		return deathTime;
	}
}
