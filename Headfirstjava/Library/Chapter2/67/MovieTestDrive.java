public class MovieTestDrive {

	public static void main(String[] args) {
		Movie one = new Movie();
		one.title = "��� ��������� � ������";
		one.genre = "��������";
		one.rating = -2;
		
		Movie two = new Movie();
		two.title = "���������� � �����";
		two.genre = "�������";
		two.rating = 5;
		two.playIt();

		Movie three = new Movie();
		three.title = "����-���";
		three.genre = "��������, �� � ����� �������";
		three.rating = 127;
	}
	
}

class Movie {
	String title;
	String genre;
	int rating;
	void playIt(){
		System.out.println("������������ ������");
	}
}