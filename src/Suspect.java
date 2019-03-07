
public class Suspect extends Person {
	boolean didIt;// did the murder

	public Suspect(String name) {
		super(name);
	}

	void iDidIt() {
		didIt = true;
	}

	boolean isMurderer() {
		return didIt;
	}
}
