class TapeDeck {

	boolean canRecord = false;

	void playTape() {
		System.out.println("пленка проигрывается");
	}

	void recordTape() {
		System.out.println("идет запись на пленку");
	}
}

public class TapeDeckTestDrive {

	
	public static void main(String[] args) {
		
		t.canRecord = true;
		t.playTape();
		
		if(t.canRecord == true) {
			t.recordTape();
		}
	}

}
