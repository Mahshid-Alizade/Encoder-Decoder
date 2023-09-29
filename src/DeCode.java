import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class DeCode extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private static HashMap map = new HashMap();
	private static DeCode frame;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new DeCode();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DeCode() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 232);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 31, 414, 39);
		contentPane.add(scrollPane);
		
		textField_1 = new JTextField();//letters
		textField_1.setBackground(Color.ORANGE);
		scrollPane.setViewportView(textField_1);
		textField_1.setColumns(10);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 102, 414, 39);
		contentPane.add(scrollPane_1);
		
		textField = new JTextField();
		textField.setBackground(Color.YELLOW);
		scrollPane_1.setViewportView(textField);
		textField.setColumns(10);
		
		
		//get letters and digits and make a map
		JButton btnOk = new JButton("O K");
		btnOk.setBackground(Color.GREEN);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				try {
				String letters[] = textField_1.getText().split("-");
				String numbers[] = textField.getText().split("-");
				
				for(int i = 0 ; i < letters.length ; i++) {
					map.put(Integer.parseInt(numbers[i]), letters[i]);
				}
				
				frame.hide();
				}catch (Exception e) {
					System.out.println("DECODE 88");
				}
				Encode.main(null);
				
			}
		});
		btnOk.setFont(new Font("Agency FB", Font.BOLD, 19));
		btnOk.setBounds(162, 152, 89, 32);
		contentPane.add(btnOk);
		
		JLabel lblLET = new JLabel("L e t t e r :");
		lblLET.setFont(new Font("Agency FB", Font.BOLD, 14));
		lblLET.setBounds(10, 6, 95, 24);
		contentPane.add(lblLET);
		
		JLabel lblNUM = new JLabel("N u m b e r :");
		lblNUM.setFont(new Font("Agency FB", Font.BOLD, 14));
		lblNUM.setBounds(10, 74, 68, 24);
		contentPane.add(lblNUM);
	}
	
	//return the map
	static HashMap getMap() {
		return map;
	}
}
