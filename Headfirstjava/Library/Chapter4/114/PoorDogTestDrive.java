
public class PoorDogTestDrive {

	public static void main(String[] args) {
		PoorDog one = new PoorDog();
		System.out.println("������ ������ - "  + one.getSize());
		System.out.println("��� ������ - "  + one.getName());
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