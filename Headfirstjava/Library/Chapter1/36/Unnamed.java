import java.io.FileNotFoundException;

public class Unnamed {

	static int x; // � ������� ���, ��� �� ��� �������
	
	public static void main(String[] args) {
		int size = 27;
		String name = "����";
		Dog myDog = new Dog(name, size);
		
		x = size - 5;
		if(x < 15) myDog.bark(8);
		
		while (x > 3) {
			myDog.play();
		}
		
		int[] numList = {2,4,6,8};
		System.out.print("������");
		System.out.print("������: " + name);
		String num = "8";
		
		try {
			readTheFile("myFile.txt");
		} catch (FileNotFoundException ex) {
			System.out.print("���� �� ������.");
		}
	}
	
	private static void readTheFile(String string) throws FileNotFoundException { // � ������� ���� �����, ��� �� ��� �������
	}

	private static class Dog { // � ������� ���� �����, ��� �� ��� �������

		public Dog(String name, int size) {
		}

		public void play() {
		}

		public void bark(int i) {
		}
	}
}
