
public class Room {
	String name;
	Weapon weapon;// What weapon is found here
	String weaponName;// What is usually found here(to know where it would be
						// when its taken)

	public Room(String name, Weapon weapon) {
		this.name = name;
		this.weapon = weapon;
		weaponName = weapon.getName();
	}

	public Room(String name) {
		this.name = name;
		this.weapon = null;
	}

	Weapon takeWeapon() {
		if (weapon != null) {
			Weapon output = weapon;
			weapon = null;
			return output;
		} else
			return null;
	}

	boolean checkWeapon() {
		// Check if this room's weapon is still here
		if (weapon == null)
			return false;
		else
			return true;

	}

	String getWeaponName() {
		return weaponName;
	}
}
