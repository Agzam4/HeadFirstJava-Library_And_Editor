public class DogTestDrive {

	public static void main(String[] args) {
		Dog d = new Dog(); // ������� ����� ������ Dog
		d.size = 40; // ������������� �������� ���� size
		d.bark(); // ��������� ����� bark()
	}
	
}

class Dog {
	
	// ���������� ����������
	int size;
	String breed;
	String name;
	
	void bark() { // ����� bark()
		System.out.println("���! ���!");
	}
}