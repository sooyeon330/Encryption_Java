import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class View {
	public static char Board[][] = new char[5][5];
	public static boolean oddFlag = false; // 글자수 출력
	public static String zcheck = "";
	
	int blankcheckCount=0;
	public static String blankcheck="";
	
	static String keytext=""; //가상의 키
	static String entext=""; //암호화할 문장
	static String detext=""; //복호화할 문장

	static String encryption; //암호화된문장
	static String decryption; //복호화된문장

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				
				JFrame frame = new JFrame();
				frame.setTitle("프로그램 이름"); // 타이틀바 문자열
				frame.setLocation(500, 400);// ->좌상단 시작 위치(x,y)
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 종료버튼 눌렀을 때 행동

				JPanel panel = new JPanel();
				panel.setPreferredSize(new Dimension(600, 500));
				panel.setLayout(new GridLayout(10, 3, 0, 10));

				JLabel key_lable = new JLabel("가상의 키를 입력하세요");
				key_lable.setSize(100, 20);
				panel.add(key_lable);

				JTextField key_text = new JTextField();
				panel.add(key_text);

				JLabel en_label = new JLabel("암호화 할 문장을 입력하세요");
				en_label.setSize(100, 20);
				panel.add(en_label);

				JTextField en_text = new JTextField();
				panel.add(en_text);

				JButton enbtn = new JButton("암호화");
				panel.add(enbtn);

				JLabel ened_label = new JLabel("암호화된 문장");
				ened_label.setSize(100, 20);
				panel.add(ened_label);

				JTextField ened_text = new JTextField();
				panel.add(ened_text);

				JButton denbtn = new JButton("복호화");
				panel.add(denbtn);

				JLabel den_label = new JLabel("복호화된 문장");
				den_label.setSize(100, 20);
				panel.add(den_label);

				JTextField den_text = new JTextField();
				panel.add(den_text);

				enbtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						setBoard(key_text.getText()); //가상의 키로 암호판 만드는 메소드 호출
						keytext = key_text.getText(); //가상의 키
						entext = en_text.getText(); //평문
						
						
						for( int i = 0 ; i < entext.length() ; i++ ) 
						{
							if(entext.charAt(i)==' ') //공백제거
							{
								entext = entext.substring(0,i)+entext.substring(i+1,entext.length()); //
								blankcheck+=10;
							}
							else
							{
								blankcheck+=0;
							}
							if(entext.charAt(i)=='z') //z를 q로 바꿔줘서 처리함.
							{
								entext = entext.substring(0,i)+'q'+entext.substring(i+1,entext.length());
								zcheck+=1;
							}
							else 
							{
								zcheck+=0;
							}
						}
						
						System.out.println(keytext+" , "+entext);
						System.out.println(setEncryption(keytext,entext));
						encryption=setEncryption(keytext,entext);
						
						for( int i = 0 ; i < encryption.length() ; i++ ) 
						{
							if(encryption.charAt(i)==' ') //공백제거
								encryption = encryption.substring(0,i)+encryption.substring(i+1,encryption.length());
						}
						
						decryption = setDecryption(keytext, encryption, zcheck);
						
						for( int i = 0 ; i < decryption.length() ; i++)
						{
							if(blankcheck.charAt(i)=='1')
							{
								decryption = decryption.substring(0,i)+" "+decryption.substring(i,decryption.length());
							}
						}
						
						ened_text.setText(encryption);
					}
				});
				denbtn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {

						den_text.setText(decryption);
					}
				});

				panel.setBackground(Color.white);
				frame.add(panel);

				frame.pack();// setVisible 전에 한번 호출 ->사이즈를 잡아줌
				frame.setVisible(true);// 창을 보이게 함
			}
		});
	}
	private static String setDecryption(String key, String str, String zcheck) {
		ArrayList<char[]> playFair = new ArrayList<char[]>(); //바꾸기 전 쌍자암호를 저장할 곳
		ArrayList<char[]> decPlayFair = new ArrayList<char[]>(); //바꾼 후의 쌍자암호 저장할 곳
		int x1 = 0 , x2 = 0 , y1 = 0, y2 = 0; //쌍자 암호 두 글자의 각각의 행,열 값
		String decStr ="";

		int lengthOddFlag = 1;
		
		
		for( int i = 0 ; i < str.length() ; i+=2 )
		{
			char[] tmpArr = new char[2];
			tmpArr[0] = str.charAt(i);
			tmpArr[1] = str.charAt(i+1);
			playFair.add(tmpArr);
		}
		
		
		for(int i = 0 ; i < playFair.size() ; i++ )
		{

			char[] tmpArr = new char[2];
			for( int j = 0 ; j < Board.length ; j++ )
			{
				for( int k = 0 ; k < Board[j].length ; k++ )
				{
					if(Board[j][k] == playFair.get(i)[0])
					{
						x1 = j;
						y1 = k;
					}
					if(Board[j][k] == playFair.get(i)[1])
					{
						x2 = j;
						y2 = k;
					}
				}
			}
			
			if(x1==x2) //행이 같은 경우 각각 바로 아래열 대입
			{
				tmpArr[0] = Board[x1][(y1+4)%5];
				tmpArr[1] = Board[x2][(y2+4)%5];
			}
			else if(y1==y2) //열이 같은 경우 각각 바로 옆 열 대입
			{
				tmpArr[0] = Board[(x1+4)%5][y1];
				tmpArr[1] = Board[(x2+4)%5][y2];
			}
			else //행, 열 다른경우 각자 대각선에 있는 곳.
			{
				tmpArr[0] = Board[x2][y1];
				tmpArr[1] = Board[x1][y2];
			}
			
			decPlayFair.add(tmpArr);
			
		}
		
		for(int i = 0 ; i < decPlayFair.size() ; i++) //중복 문자열 돌려놓음
		{
			if(i!=decPlayFair.size()-1 && decPlayFair.get(i)[1]=='x' 
					&& decPlayFair.get(i)[0]==decPlayFair.get(i+1)[0])
			{	
				decStr += decPlayFair.get(i)[0];
			}
			else
			{
				decStr += decPlayFair.get(i)[0]+""+decPlayFair.get(i)[1];
			}
		}
		
		
		
		for(int i = 0 ; i < zcheck.length() ; i++ )//z위치 찾아서 q로 돌려놓음
		{
			if( zcheck.charAt(i) == '1' ) 
				decStr = decStr.substring(0,i)+'z'+decStr.substring(i+1,decStr.length());
			
		}
		
		
		
		if(oddFlag) decStr = decStr.substring(0,decStr.length()-1);
		
		/*
		 //띄어쓰기
		for(int i = 0 ; i < decStr.length(); i++)
		{
			if(i%2==lengthOddFlag){
				decStr = decStr.substring(0, i+1)+" "+decStr.substring(i+1, decStr.length());
				i++;
				lengthOddFlag = ++lengthOddFlag %2;
			}
		}
		*/
		return decStr;
	}
	private static String setEncryption(String key, String str){
		ArrayList<char[]> playFair = new ArrayList<char[]>();
		ArrayList<char[]> encPlayFair = new ArrayList<char[]>();
		int x1 = 0 , x2 = 0 , y1 = 0, y2 = 0;
		String encStr ="";
		
		
		
		for( int i = 0 ; i < str.length() ; i+=2 ) // arraylist 세팅
		{
			char[] tmpArr = new char[2];
			tmpArr[0] = str.charAt(i);
			try{
				if( str.charAt(i) == str.charAt(i+1)) //글이 반복되면 x추가
				{
					tmpArr[1] = 'x';
					i--;
				}else{
					tmpArr[1] = str.charAt(i+1);
				}
			}catch(StringIndexOutOfBoundsException e)
			{
				tmpArr[1] = 'x'; 
				oddFlag = true;
			}
			playFair.add(tmpArr);
		}
		
		for(int i = 0 ; i < playFair.size() ; i++ )
		{
			System.out.print(playFair.get(i)[0]+""+playFair.get(i)[1]+" ");
		}
		System.out.println();
		
		for(int i = 0 ; i < playFair.size() ; i++ )
		{

			char[] tmpArr = new char[2];
			for( int j = 0 ; j < Board.length ; j++ ) //쌍자암호의 각각 위치체크
			{
				for( int k = 0 ; k < Board[j].length ; k++ )
				{
					if(Board[j][k] == playFair.get(i)[0])
					{
						x1 = j;
						y1 = k;
					}
					if(Board[j][k] == playFair.get(i)[1])
					{
						x2 = j;
						y2 = k;
					}
				}
			}
			
			
			if(x1==x2) //행이 같은경우
			{
				tmpArr[0] = Board[x1][(y1+1)%5];
				tmpArr[1] = Board[x2][(y2+1)%5];
			}
			else if(y1==y2) //열이 같은 경우
			{
				tmpArr[0] = Board[(x1+1)%5][y1];
				tmpArr[1] = Board[(x2+1)%5][y2];
			} 
			else //행, 열 모두 다른경우
			{
				tmpArr[0] = Board[x2][y1];
				tmpArr[1] = Board[x1][y2];
			}
			
			encPlayFair.add(tmpArr);
			
		}
		
		for(int i = 0 ; i < encPlayFair.size() ; i++)
		{
			encStr += encPlayFair.get(i)[0]+""+encPlayFair.get(i)[1]+" ";
		}
		
		return encStr;
	}
	private static void setBoard(String key) {
		String keyForSet = ""; // 중복된 문자가 제거된 문자열을 저장할 문자열.
		boolean duplicationFlag = false; // 문자 중복을 체크하기 위한 flag 변수.
		int keyLengthCount = 0; // Board에 keyForSet을 넣기 위한 count변수.

		key += "abcdefghijklmnopqrstuvwxyz"; // 키에 모든 알파벳을 추가.

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
		// 배열에 대입
		for (int i = 0; i < Board.length; i++) {
			for (int j = 0; j < Board[i].length; j++) {
				Board[i][j] = keyForSet.charAt(keyLengthCount++);
			}
		}
		// 배열에 대입
		for (int i = 0; i < Board.length; i++) {
			for (int j = 0; j < Board[i].length; j++) {
				System.out.print(Board[i][j] + "-");
			}
			System.out.println();
		}

	}
}
