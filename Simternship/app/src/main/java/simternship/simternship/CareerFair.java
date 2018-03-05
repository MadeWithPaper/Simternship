package simternship.simternship;

import java.util.List;

public class CareerFair {
	private List<CareerFairBooth> booths;

	public CareerFair(List<CareerFairBooth> booths) {
		this.booths = booths;
	}

	public List<CareerFairBooth> getBooths() {
		return booths;
	}

}