import java.util.ArrayList;
import java.util.Scanner;

public class tt {
	public static boolean oddFlag = false;

	public static void main(String[] args) {
		// 단일치환

		char[][] Board = {
				{ 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
						'u', 'v', 'w', 'x', 'y', 'z' },
				{ 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
						'u', 'v', 'w', 'x', 'y', 'z' } };

		String encryption;
		String decryption;

		Scanner sc = new Scanner(System.in); // 입력을 위한 Scanner 정의
		System.out.print("암호화에 쓰일 키를 입력하세요 : ");
		String key = sc.nextLine(); // key 입력
		System.out.print("암호화할 문자열을 입력하세요 : ");
		String enstr = sc.nextLine();
		String blankCheck = "";
		int blankCheckCount = 0;

		for (int i = 0; i < enstr.length(); i++) {
			if (enstr.charAt(i) == ' ') // 공백제거
			{
				enstr = enstr.substring(0, i) + enstr.substring(i + 1, enstr.length());
				blankCheck += 10;
			} else {
				blankCheck += 0;
			}
		}

		// for( int j = 0 ; j < key.length() ; j++ ) //중복 제거
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
		key += "abcdefghijklmnopqrstuvwxyz"; // 키에 모든 알파벳을 추가.

		String keyForSet = ""; // 중복된 문자가 제거된 문자열을 저장할 문자열.
		boolean duplicationFlag = false; // 문자 중복을 체크하기 위한 flag 변수.
		int keyLengthCount = 0;
		// 중복처리
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
		for (int i = 0; i < Board[0].length; i++) { // 배열 대입
			// System.out.println(keyForSet.charAt(i));
			Board[1][i] = keyForSet.charAt(i);
		}
		// System.out.println(keyForSet);

		System.out.println("평문 : " + enstr);
		System.out.println("암호판 : ");
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
		System.out.print("암호문 : ");
		for(int i=0; i<tmparr.size(); i++){
			System.out.print(Board[1][tmparr.get(i)]);
		}
		System.out.println();
		
		System.out.print("복호문 : ");
		for(int i=0; i<tmparr.size(); i++){
			System.out.print(Board[0][tmparr.get(i)]);
		}
	}

}
