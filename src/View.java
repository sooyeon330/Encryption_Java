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
	public static boolean oddFlag = false;
	static char[][] Board = {
			{ 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
					'u', 'v', 'w', 'x', 'y', 'z' },
			{ 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
					'u', 'v', 'w', 'x', 'y', 'z' } };
	
	static String keytext=""; //가상의 키
	static String entext=""; //암호화할 문장
	static String detext=""; //복호화할 문장

	static String encryption=""; //암호화된문장
	static String decryption=""; //복호화된문장

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
								
				ArrayList<Integer> tmparr = new ArrayList<>();
				
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
						//암호화
						encryption=""; //암호화된문장
						decryption="";
						ened_text.setText(" ");
						den_text.setText(" ");
						tmparr.clear();
						String blankCheck = "";
						int blankCheckCount = 0;
						keytext = key_text.getText(); //가상의 키
						entext = en_text.getText(); //평문
						
						for (int i = 0; i < entext.length(); i++) {
							if (entext.charAt(i) == ' ') // 공백제거
							{
								entext = entext.substring(0, i) + entext.substring(i + 1, entext.length());
								blankCheck += 10;
							} else {
								blankCheck += 0;
							}
						}
						
						keytext += "abcdefghijklmnopqrstuvwxyz"; // 키에 모든 알파벳을 추가.

						String keyForSet = ""; // 중복된 문자가 제거된 문자열을 저장할 문자열.
						boolean duplicationFlag = false; // 문자 중복을 체크하기 위한 flag 변수.
						int keyLengthCount = 0;
						// 중복처리
						for (int i = 0; i < keytext.length(); i++) {
							for (int j = 0; j < keyForSet.length(); j++) {
								if (keytext.charAt(i) == keyForSet.charAt(j)) {
									duplicationFlag = true;
									break;
								}
							}
							if (!(duplicationFlag))
								keyForSet += keytext.charAt(i);
							duplicationFlag = false;
						}
						// Board[1] = keyForSet.charAt(i);
						for (int i = 0; i < Board[0].length; i++) { // 배열 대입
							// System.out.println(keyForSet.charAt(i));
							Board[1][i] = keyForSet.charAt(i);
						}
						
						
						
						for (int j = 0; j < entext.length(); j++) {
							for (int i = 0; i < Board[0].length; i++) {
								if (Board[0][i] == entext.charAt(j)) 
									tmparr.add(i);
							}
						}
						
						for(int i=0; i<tmparr.size(); i++){
							encryption += Board[1][tmparr.get(i)];
						}
						
						ened_text.setText(encryption);
						
					}
				});
				denbtn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						for(int i=0; i<tmparr.size(); i++){
							decryption += Board[0][tmparr.get(i)];
						}
						
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
	
}
