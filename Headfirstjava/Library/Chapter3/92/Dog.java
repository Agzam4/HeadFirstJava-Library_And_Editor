class Dog {
	
	String name;
	
	public static void main(String[] args) {
		// ������� ������ Dog � �������� � ���� ������
		Dog dog1 = new Dog();
		dog1.bark();
		dog1.name = "����";
		
		// ������ �������� ������ ���� Dog
		Dog[] myDogs = new Dog[3];
		// � �������� � ���� ��������� ���������
		myDogs[0] = new Dog();
		myDogs[1] = new Dog();
		myDogs[2] = dog1;
		
		// ������ �������� ������ � �������� Dog
		// � ������� ������ �� �������
		myDogs[0].name = "����";
		myDogs[1].name = "������";
		
		// �����... ����� ��� � myDogs[2]?
		System.out.print("��� ��������� ������ - ");
		System.out.println(myDogs[2].name);
		
		// ������ ��������� ��� �������� �������
		// � ������� ��� ������� ����� bark()
		int x = 0;
		while (x < myDogs.length) {
			myDogs[x].bark();
			x = x + 1;
		}
	}
	
	public void bark() {
		System.out.println(name + " ������ ���!");
	}

	public void eat() {  }
	public void chaseCat() {  }
}
