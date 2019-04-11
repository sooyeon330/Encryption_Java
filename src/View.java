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
	
	static String keytext=""; //������ Ű
	static String entext=""; //��ȣȭ�� ����
	static String detext=""; //��ȣȭ�� ����

	static String encryption=""; //��ȣȭ�ȹ���
	static String decryption=""; //��ȣȭ�ȹ���

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
								
				ArrayList<Integer> tmparr = new ArrayList<>();
				
				JFrame frame = new JFrame();
				frame.setTitle("���α׷� �̸�"); // Ÿ��Ʋ�� ���ڿ�
				frame.setLocation(500, 400);// ->�»�� ���� ��ġ(x,y)
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// �����ư ������ �� �ൿ

				JPanel panel = new JPanel();
				panel.setPreferredSize(new Dimension(600, 500));
				panel.setLayout(new GridLayout(10, 3, 0, 10));

				JLabel key_lable = new JLabel("������ Ű�� �Է��ϼ���");
				key_lable.setSize(100, 20);
				panel.add(key_lable);

				JTextField key_text = new JTextField();
				panel.add(key_text);

				JLabel en_label = new JLabel("��ȣȭ �� ������ �Է��ϼ���");
				en_label.setSize(100, 20);
				panel.add(en_label);

				JTextField en_text = new JTextField();
				panel.add(en_text);

				JButton enbtn = new JButton("��ȣȭ");
				panel.add(enbtn);

				JLabel ened_label = new JLabel("��ȣȭ�� ����");
				ened_label.setSize(100, 20);
				panel.add(ened_label);

				JTextField ened_text = new JTextField();
				panel.add(ened_text);

				JButton denbtn = new JButton("��ȣȭ");
				panel.add(denbtn);

				JLabel den_label = new JLabel("��ȣȭ�� ����");
				den_label.setSize(100, 20);
				panel.add(den_label);

				JTextField den_text = new JTextField();
				panel.add(den_text);
				
				enbtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						//��ȣȭ
						encryption=""; //��ȣȭ�ȹ���
						decryption="";
						ened_text.setText(" ");
						den_text.setText(" ");
						tmparr.clear();
						String blankCheck = "";
						int blankCheckCount = 0;
						keytext = key_text.getText(); //������ Ű
						entext = en_text.getText(); //��
						
						for (int i = 0; i < entext.length(); i++) {
							if (entext.charAt(i) == ' ') // ��������
							{
								entext = entext.substring(0, i) + entext.substring(i + 1, entext.length());
								blankCheck += 10;
							} else {
								blankCheck += 0;
							}
						}
						
						keytext += "abcdefghijklmnopqrstuvwxyz"; // Ű�� ��� ���ĺ��� �߰�.

						String keyForSet = ""; // �ߺ��� ���ڰ� ���ŵ� ���ڿ��� ������ ���ڿ�.
						boolean duplicationFlag = false; // ���� �ߺ��� üũ�ϱ� ���� flag ����.
						int keyLengthCount = 0;
						// �ߺ�ó��
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
						for (int i = 0; i < Board[0].length; i++) { // �迭 ����
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

				frame.pack();// setVisible ���� �ѹ� ȣ�� ->����� �����
				frame.setVisible(true);// â�� ���̰� ��
			}
		});
	}
	
}
