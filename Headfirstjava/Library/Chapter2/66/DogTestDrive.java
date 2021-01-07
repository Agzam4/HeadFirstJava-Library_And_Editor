public class DogTestDrive {

	public static void main(String[] args) {
		Dog d = new Dog(); // Создаем оъект класса Dog
		d.size = 40; // Устанавливаем значение поля size
		d.bark(); // Вызыываем метод bark()
	}
	
}

class Dog {
	
	// Переменные экземпляра
	int size;
	String breed;
	String name;
	
	void bark() { // Метод bark()
		System.out.println("Гав! Гав!");
	}
}