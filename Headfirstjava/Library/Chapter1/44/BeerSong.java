public class BeerSong {

	public static void main(String[] args) {
		int beerNum = 99;
		String word = "бутылок (бутылки)";
		
		while (beerNum > 0) {
			if(beerNum == 1) {
				word = "бутылка"; // В единственном числе - ОДНА бутылка
			}

			System.out.println(beerNum + " " + word + " сока на стене.");
			System.out.println(beerNum + " " + word + " сока.");
			System.out.println("Возьми одну");
			System.out.println("Пусти по кругу");
			beerNum = beerNum - 1;
			if (beerNum > 0) {
				System.out.println(beerNum + " " + word + " сока на стене.");
			}else {
				System.out.println("Нет бутылок на стене");
			} // Конец else
		} // Конец цикла
	} // Конец метода main
} // Конец класса