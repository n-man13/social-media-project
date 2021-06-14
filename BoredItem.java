public class BoredItem {
	
	private String activity;
	private double price;
	private double accessibility;
	
	public BoredItem() {
		
	}
	public String getActivity() {
		return activity;
	}
	public double getPrice() {
		return price;
	}
	public double getAccessibility() {
		return accessibility;
	}
	public void setActivity(String newAct) {
		activity = newAct;
	}
	public void setPrice(double cost) {
		price = cost;
	}
	public void setAccessibility(double access) {
		accessibility = access;
	}
	public String toString() {
		return activity + " " + price + " " + accessibility;
	}
}