import java.util.*;

public class CareerFair {
	private Booth[] booths;
	private Attendee[] attendees;

	public CareerFair() {
		booths = new Booth[6];
		attendees = new Attendee[50];
	}

	public Booth[] getBooths() {
		return booths;
	}

	public Attendee[] getAttendees() {
		return attendees;
	}
}