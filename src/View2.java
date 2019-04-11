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
	public static boolean oddFlag = false; // ���ڼ� ���
	public static String zcheck = "";
	
	int blankcheckCount=0;
	public static String blankcheck="";
	
	static String keytext=""; //������ Ű
	static String entext=""; //��ȣȭ�� ����
	static String detext=""; //��ȣȭ�� ����

	static String encryption; //��ȣȭ�ȹ���
	static String decryption; //��ȣȭ�ȹ���

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
						setBoard(key_text.getText()); //������ Ű�� ��ȣ�� ����� �޼ҵ� ȣ��
						keytext = key_text.getText(); //������ Ű
						entext = en_text.getText(); //��
						
						
						for( int i = 0 ; i < entext.length() ; i++ ) 
						{
							if(entext.charAt(i)==' ') //��������
							{
								entext = entext.substring(0,i)+entext.substring(i+1,entext.length()); //
								blankcheck+=10;
							}
							else
							{
								blankcheck+=0;
							}
							if(entext.charAt(i)=='z') //z�� q�� �ٲ��༭ ó����.
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
							if(encryption.charAt(i)==' ') //��������
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

				frame.pack();// setVisible ���� �ѹ� ȣ�� ->����� �����
				frame.setVisible(true);// â�� ���̰� ��
			}
		});
	}
	private static String setDecryption(String key, String str, String zcheck) {
		ArrayList<char[]> playFair = new ArrayList<char[]>(); //�ٲٱ� �� ���ھ�ȣ�� ������ ��
		ArrayList<char[]> decPlayFair = new ArrayList<char[]>(); //�ٲ� ���� ���ھ�ȣ ������ ��
		int x1 = 0 , x2 = 0 , y1 = 0, y2 = 0; //���� ��ȣ �� ������ ������ ��,�� ��
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
			
			if(x1==x2) //���� ���� ��� ���� �ٷ� �Ʒ��� ����
			{
				tmpArr[0] = Board[x1][(y1+4)%5];
				tmpArr[1] = Board[x2][(y2+4)%5];
			}
			else if(y1==y2) //���� ���� ��� ���� �ٷ� �� �� ����
			{
				tmpArr[0] = Board[(x1+4)%5][y1];
				tmpArr[1] = Board[(x2+4)%5][y2];
			}
			else //��, �� �ٸ���� ���� �밢���� �ִ� ��.
			{
				tmpArr[0] = Board[x2][y1];
				tmpArr[1] = Board[x1][y2];
			}
			
			decPlayFair.add(tmpArr);
			
		}
		
		for(int i = 0 ; i < decPlayFair.size() ; i++) //�ߺ� ���ڿ� ��������
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
		
		
		
		for(int i = 0 ; i < zcheck.length() ; i++ )//z��ġ ã�Ƽ� q�� ��������
		{
			if( zcheck.charAt(i) == '1' ) 
				decStr = decStr.substring(0,i)+'z'+decStr.substring(i+1,decStr.length());
			
		}
		
		
		
		if(oddFlag) decStr = decStr.substring(0,decStr.length()-1);
		
		/*
		 //����
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
		
		
		
		for( int i = 0 ; i < str.length() ; i+=2 ) // arraylist ����
		{
			char[] tmpArr = new char[2];
			tmpArr[0] = str.charAt(i);
			try{
				if( str.charAt(i) == str.charAt(i+1)) //���� �ݺ��Ǹ� x�߰�
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
			for( int j = 0 ; j < Board.length ; j++ ) //���ھ�ȣ�� ���� ��ġüũ
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
			
			
			if(x1==x2) //���� �������
			{
				tmpArr[0] = Board[x1][(y1+1)%5];
				tmpArr[1] = Board[x2][(y2+1)%5];
			}
			else if(y1==y2) //���� ���� ���
			{
				tmpArr[0] = Board[(x1+1)%5][y1];
				tmpArr[1] = Board[(x2+1)%5][y2];
			} 
			else //��, �� ��� �ٸ����
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
		String keyForSet = ""; // �ߺ��� ���ڰ� ���ŵ� ���ڿ��� ������ ���ڿ�.
		boolean duplicationFlag = false; // ���� �ߺ��� üũ�ϱ� ���� flag ����.
		int keyLengthCount = 0; // Board�� keyForSet�� �ֱ� ���� count����.

		key += "abcdefghijklmnopqrstuvwxyz"; // Ű�� ��� ���ĺ��� �߰�.

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
		// �迭�� ����
		for (int i = 0; i < Board.length; i++) {
			for (int j = 0; j < Board[i].length; j++) {
				Board[i][j] = keyForSet.charAt(keyLengthCount++);
			}
		}
		// �迭�� ����
		for (int i = 0; i < Board.length; i++) {
			for (int j = 0; j < Board[i].length; j++) {
				System.out.print(Board[i][j] + "-");
			}
			System.out.println();
		}

	}
}
