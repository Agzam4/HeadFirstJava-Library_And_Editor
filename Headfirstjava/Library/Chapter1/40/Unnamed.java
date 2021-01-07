
public class Unnamed {

	public static void main(String[] args) {
		// 1) Сделать что-то
		int x = 3;
		String name = "Кинжал";
		x = x * 17;
		System.out.print("x равен " + x);
		double d = Math.random();
		// Это комментарий
		
		// 2 Делать что-то снова и снова
		
		while (x > 12) {
			x = x -1;
		}
		for (int x2 = 0; x2 < 10; x2 = x2 + 1) { // Я заменил x на x2, что бы код работал (тк нельзя объявлять переменные с одинаковыми именами)
			System.out.print("Теперь x равен " + x2);
		}
		
		// 3) Сделать что-то при уловии
		
		if(x == 10) {
			System.out.print("x равен 10"); // В книге был немного кривой перевод, но я исправил (Оригинал "x должен быть равен 10")
		}else {
			System.out.print("x не равен 10");
		}
		
		if((x < 3) & (name.equals("Кинжал"))) {
			System.out.print("Осторожно");
		}

		System.out.print("Эта строка выполняется в любом случаи");
	}

}