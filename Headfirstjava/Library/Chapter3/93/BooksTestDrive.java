class Books {
	String title;
	String author;
}

public class BooksTestDrive {

	public static void main(String[] args) {
		
		Books[] myBooks = new Books[3];
		int x = 0;
		myBooks[0].title = "����� Java";
		myBooks[1].title = "Java ������";
		myBooks[2].title = "������� �������� �� Java";

		myBooks[0].author = "���";
		myBooks[1].author = "���";
		myBooks[2].author = "��";
		
		while (x < 3) {
			System.out.print(myBooks[x].title);
			System.out.print(", �����");
			System.out.println(myBooks[x].author);
			x = x+1;
		}
	}
}
