
public class Unnamed {

	public static void main(String[] args) {
		// 1) ������� ���-��
		int x = 3;
		String name = "������";
		x = x * 17;
		System.out.print("x ����� " + x);
		double d = Math.random();
		// ��� �����������
		
		// 2 ������ ���-�� ����� � �����
		
		while (x > 12) {
			x = x -1;
		}
		for (int x2 = 0; x2 < 10; x2 = x2 + 1) { // � ������� x �� x2, ��� �� ��� ������� (�� ������ ��������� ���������� � ����������� �������)
			System.out.print("������ x ����� " + x2);
		}
		
		// 3) ������� ���-�� ��� ������
		
		if(x == 10) {
			System.out.print("x ����� 10"); // � ����� ��� ������� ������ �������, �� � �������� (�������� "x ������ ���� ����� 10")
		}else {
			System.out.print("x �� ����� 10");
		}
		
		if((x < 3) & (name.equals("������"))) {
			System.out.print("���������");
		}

		System.out.print("��� ������ ����������� � ����� ������");
	}

}