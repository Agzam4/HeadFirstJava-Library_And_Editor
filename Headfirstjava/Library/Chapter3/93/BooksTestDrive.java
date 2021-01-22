class Books {
	String title;
	String author;
}

public class BooksTestDrive {

	public static void main(String[] args) {
		
		Books[] myBooks = new Books[3];
		int x = 0;
		myBooks[0].title = "ѕлоды Java";
		myBooks[1].title = "Java √этсби";
		myBooks[2].title = "—борник рецептов по Java";

		myBooks[0].author = "Ѕоб";
		myBooks[1].author = "—ью";
		myBooks[2].author = "ян";
		
		while (x < 3) {
			System.out.print(myBooks[x].title);
			System.out.print(", автор");
			System.out.println(myBooks[x].author);
			x = x+1;
		}
	}
}
