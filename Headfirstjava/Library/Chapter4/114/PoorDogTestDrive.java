
public class PoorDogTestDrive {

	public static void main(String[] args) {
		PoorDog one = new PoorDog();
		System.out.println("Размер собаки - "  + one.getSize());
		System.out.println("Имя собаки - "  + one.getName());
	}
}

class PoorDog {
	private int size;
	private String name;
	
	public int getSize() {
		return size;
	}
	public String getName() {
		return name;
	}
}