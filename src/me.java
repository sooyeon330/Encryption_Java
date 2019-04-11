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

public class me {
	public static char alphabetBoard[][] = new char[5][5];
	public static boolean oddFlag = false; // 글자수 출력
	public static String zCheck = "";
	
	int blankCheckCount=0;
	public static String blankCheck="";
	
	static String keytext="";
	static String entext="";
	static String detext="";

	static String encryption;
	static String decryption;

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
						
					}
				});
				denbtn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {

						
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
