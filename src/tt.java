import java.util.ArrayList;
import java.util.Scanner;

public class tt {
	public static boolean oddFlag = false;

	public static void main(String[] args) {
		// ����ġȯ

		char[][] Board = {
				{ 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
						'u', 'v', 'w', 'x', 'y', 'z' },
				{ 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
						'u', 'v', 'w', 'x', 'y', 'z' } };

		String encryption;
		String decryption;

		Scanner sc = new Scanner(System.in); // �Է��� ���� Scanner ����
		System.out.print("��ȣȭ�� ���� Ű�� �Է��ϼ��� : ");
		String key = sc.nextLine(); // key �Է�
		System.out.print("��ȣȭ�� ���ڿ��� �Է��ϼ��� : ");
		String enstr = sc.nextLine();
		String blankCheck = "";
		int blankCheckCount = 0;

		for (int i = 0; i < enstr.length(); i++) {
			if (enstr.charAt(i) == ' ') // ��������
			{
				enstr = enstr.substring(0, i) + enstr.substring(i + 1, enstr.length());
				blankCheck += 10;
			} else {
				blankCheck += 0;
			}
		}

		// for( int j = 0 ; j < key.length() ; j++ ) //�ߺ� ����
		// {
		// if(key.charAt(j) == key.charAt(j+1)) {
		// tmp.add(key.charAt(j));
		// j+=2;
		// if(j==key.length()-1) tmp.add(key.charAt(j));
		// }
		// else {
		// tmp.add(key.charAt(j));
		// }
		// }
		key += "abcdefghijklmnopqrstuvwxyz"; // Ű�� ��� ���ĺ��� �߰�.

		String keyForSet = ""; // �ߺ��� ���ڰ� ���ŵ� ���ڿ��� ������ ���ڿ�.
		boolean duplicationFlag = false; // ���� �ߺ��� üũ�ϱ� ���� flag ����.
		int keyLengthCount = 0;
		// �ߺ�ó��
		for (int i = 0; i < key.length(); i++) {
			for (int j = 0; j < keyForSet.length(); j++) {
				if (key.charAt(i) == keyForSet.charAt(j)) {
					duplicationFlag = true;
					break;
				}
			}
			if (!(duplicationFlag))
				keyForSet += key.charAt(i);
			duplicationFlag = false;
		}
		// Board[1] = keyForSet.charAt(i);
		for (int i = 0; i < Board[0].length; i++) { // �迭 ����
			// System.out.println(keyForSet.charAt(i));
			Board[1][i] = keyForSet.charAt(i);
		}
		// System.out.println(keyForSet);

		System.out.println("�� : " + enstr);
		System.out.println("��ȣ�� : ");
		System.out.println(Board[0]);
		System.out.println(Board[1]);

		ArrayList<Integer> tmparr = new ArrayList<>();
		ArrayList<Integer> tmparr2 = new ArrayList<>();
		
		for (int j = 0; j < enstr.length(); j++) {
			for (int i = 0; i < Board[0].length; i++) {
				if (Board[0][i] == enstr.charAt(j)) 
					tmparr.add(i);
			}
		}
//		System.out.println(tmparr);
		System.out.print("��ȣ�� : ");
		for(int i=0; i<tmparr.size(); i++){
			System.out.print(Board[1][tmparr.get(i)]);
		}
		System.out.println();
		
		System.out.print("��ȣ�� : ");
		for(int i=0; i<tmparr.size(); i++){
			System.out.print(Board[0][tmparr.get(i)]);
		}
	}

}
