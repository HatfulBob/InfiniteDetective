
public class Victim extends Person {
	// Victims are always assumed to be dead sometime before 9, they're screwed

	public Victim(String name) {
		super(name);
	}

	@Override
	boolean isVictim() {
		return true;
	}

}
