public class MovieTestDrive {

	public static void main(String[] args) {
		Movie one = new Movie();
		one.title = "Как прогореть в Акциях";
		one.genre = "Трагедия";
		one.rating = -2;
		
		Movie two = new Movie();
		two.title = "Потерянные в Офисе";
		two.genre = "Комедия";
		two.rating = 5;
		two.playIt();

		Movie three = new Movie();
		three.title = "Байт-куб";
		three.genre = "Трагедия, но в целом веселая";
		three.rating = 127;
	}
	
}

class Movie {
	String title;
	String genre;
	int rating;
	void playIt(){
		System.out.println("Проигрывание фильма");
	}
}