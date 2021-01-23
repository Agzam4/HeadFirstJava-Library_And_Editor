
public class GoodDogTestDrive {

	public static void main(String[] args) {
		GoodDog one = new GoodDog();
		one.setSize(70);
		GoodDog two = new GoodDog();
		two.setSize(8);
		System.out.println("ѕерва€ собака: " + one.getSise());
		System.out.println("¬тора€ собака: " + two.getSise());
	}

}

class GoodDog {
	
	private int size;
	
	public int getSise() {
		return size;
	}
	public void setSize(int s) {
		size = s;
	}
	
	void bark() {
		if(size > 60) {
			System.out.println("√ав √ав!");
		} else if (size > 14) {
			System.out.println("¬уф ¬уф!");
		} else {
			System.out.println("“€ф “€ф");
		}
	}
}
