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
	public static boolean oddFlag = false; // ���ڼ� ���
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
						
					}
				});
				denbtn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {

						
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
