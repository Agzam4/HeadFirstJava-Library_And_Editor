class TapeDeck {

	boolean canRecord = false;

	void playTape() {
		System.out.println("������ �������������");
	}

	void recordTape() {
		System.out.println("���� ������ �� ������");
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
