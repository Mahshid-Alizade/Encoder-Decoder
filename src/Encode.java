import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Base64.Decoder;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;

public class Encode extends JFrame {

	private JPanel contentPane;
	private Random r = new Random();
	private MaxHeap maxHeap = new MaxHeap(28);
	private MaxHeap decodeMaxHeap = new MaxHeap(26);
	private HashMap map = new HashMap();
	private Queue queue = new Queue();
	private HashMap decodeMap;
	private static Encode frame;
	private Queue decodeQueue = new Queue();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Encode();
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
	public Encode() {
		setType(Type.UTILITY);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 312, 461);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 39, 276, 44);
		contentPane.add(scrollPane);

		JTextArea textArea = new JTextArea();
		textArea.setBackground(new Color(255, 102, 255));
		textArea.setFont(new Font("Agency FB", Font.BOLD, 18));
		scrollPane.setViewportView(textArea);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 124, 276, 166);
		contentPane.add(scrollPane_1);

		JTextArea answer = new JTextArea();
		answer.setBackground(Color.CYAN);
		answer.setFont(new Font("Agency FB", Font.PLAIN, 25));
		scrollPane_1.setViewportView(answer);


		//action listener for ENCODE
		JButton button = new JButton("EnCode");
		button.setBackground(Color.ORANGE);
		button.setFocusable(false);
		button.setFont(new Font("Agency FB", Font.BOLD, 22));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				HashMap map = fillMap();//make a ma with letters and digits
				System.out.println(map.goodForDecode());
				createHeap(map, maxHeap);
				String text = textArea.getText();//get text from textArea
				String code = codeText(text);
				System.out.println(code);
				answer.setText(code);
			}

		});
		button.setBounds(10, 301, 133, 44);
		contentPane.add(button);
		

		//action listener for DECODE
		JButton button_1 = new JButton("DeCode");
		button_1.setBackground(Color.YELLOW);
		button_1.setFocusable(false);
		button_1.setFont(new Font("Agency FB", Font.BOLD, 21));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String code = textArea.getText();
				decodeMap = DeCode.getMap();//get map from DECODE class
				decodeMaxHeap = createHeap(decodeMap, decodeMaxHeap);
				decode(code, "" , decodeMaxHeap, decodeQueue, decodeMap);
				answer.setText(result);

			}
		});
		button_1.setBounds(153, 302, 133, 44);
		contentPane.add(button_1);

		JLabel lblEnter = new JLabel("E N T E R Y :");
		lblEnter.setFont(new Font("Agency FB", Font.BOLD, 18));
		lblEnter.setBounds(12, 3, 126, 32);
		contentPane.add(lblEnter);

		
		//get letters and digits to fill the map
		JButton btnNewButton = new JButton("Enter Reference");
		btnNewButton.setBackground(Color.GREEN);
		btnNewButton.setFont(new Font("Agency FB", Font.BOLD, 23));
		btnNewButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {

				frame.hide();
				DeCode.main(null);
				try {
				decodeMaxHeap.clear();
				decodeMap.clear();
				decodeQueue.clear();
				}catch(NullPointerException e) {
					System.out.println(156);
				}
			}
		});
		btnNewButton.setBounds(10, 356, 276, 55);
		contentPane.add(btnNewButton);
		
		JLabel lblANS = new JLabel("A N S W E R :");
		lblANS.setBackground(new Color(221, 160, 221));
		lblANS.setFont(new Font("Agency FB", Font.BOLD, 18));
		lblANS.setBounds(12, 90, 126, 32);
		contentPane.add(lblANS);
	}

	// create map with letters and random digits
	private HashMap fillMap() {
		String letters[] = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
				"s", "t", "u", "v", "w", "x", "y", "z" };
		int randomNumber = 0;
		for (int i = 0; i < letters.length; i++) {
			randomNumber = r.nextInt(26) + 1;// (max - min +1) + min
			while (map.containsKey(randomNumber))// tekrari nabashad
				randomNumber = r.nextInt(26) + 1;
			map.put(randomNumber, letters[i]);
		}

		return map;
	}

	// create heap with map
	private MaxHeap createHeap(HashMap map, MaxHeap maxHeap) {
		for (int i = 0; i < map.size; i++) {
			maxHeap.insert(map.getkeyWithIndex(i));
		}
		return maxHeap;
	}

	//CODER
	private String codeText(String text) {

		String code = "";
		for (int i = 0; i < text.length(); i++) {
			int deleteCount = (int) map.getKey(text.charAt(i) + "");
			int lastDeletedRoot = 0;

			//gar tedade hazf ha bishtar az ozv haye heap bashad
			if (deleteCount > maxHeap.getSize()) {
				while (!queue.isEmpty())
					maxHeap.insert((int) queue.deQueue().data);
			}
			
			//be tedade deleteCount hazf mikonim
			for (int j = 0; j < deleteCount; j++) {
				lastDeletedRoot = maxHeap.deleteRoot();
				queue.enQueue(lastDeletedRoot);
			}

			code += map.get(lastDeletedRoot);

			map.swapKey(map.get(lastDeletedRoot), lastDeletedRoot, map.get(deleteCount), deleteCount);
		}

		return code;
	}
	
	
	//DECODER
	String result = "";
	void decode(String code, String answer, MaxHeap heap, Queue q, HashMap map) {
		
		//take a copy of map
		HashMap mapcopy_1 = new HashMap();
		for(int i = 0 ; i < map.size ; i++) {
			mapcopy_1.put(map.map[i][0], map.map[i][1]);
		}

		//take a copy of heap
		MaxHeap heapCopy =new MaxHeap(28);
		for(int i = 1 ; i < heap.getSize() + 1 ; i++) {
			heapCopy.insert(heap.heap[i]);
		}
		
		
		//take a copy of map
		Queue queueCopy = new Queue();
		Node t1 = q.getFirst();
		for(int i = 0 ; i < q.getSize() ; i++) {
			queueCopy.enQueue(t1.data);
			t1 = t1.next;
		}
		
		
		if (code.length() == 0) {
			result +=">> " + answer + "\n";
			System.out.println("answer : " + answer);
			return;
		}

		//part 1
		String tmp = code.charAt(0) + "";
		if (heapCopy.contains((int) map.getKey(tmp))) {
			int deleteCount = 0;
			int heapSize1 = heapCopy.getSize();

			// part 1
			// az heap hazf mikonim ta be Harfe morede nazar beresim
			for (int i = 0; i < heapSize1; i++) {
				int x = heapCopy.deleteRoot();
				queueCopy.enQueue(x);// harfe hazf shode ra be saf ezafe mikonim
				deleteCount++;// tedade hazf ha
				if (x == (int) map.getKey(tmp))// agar harf peyda shod
					break;
			}

			System.out.println("delet num : " + deleteCount +  "  " + tmp);
			String decodedLetter = (String) map.get(deleteCount);// decoded letter
			// prepare v1 , v2 , k1 , k2 to swap
			String v1 = map.get(deleteCount) + "";
			int k1 = deleteCount;
			String v2 = tmp;
			int k2 = (int) map.getKey(tmp);
			map.swapKey(v1, k1, v2, k2);
			// it will be pass for next level
			String nextlevelAnswer = answer + decodedLetter;
			// call function for the next level
			decode(code.substring(1), nextlevelAnswer, heapCopy, queueCopy, map);

			
			// part 2
			if (deleteCount != queueCopy.getSize()) {
				int queueSize = (int) queueCopy.getSize();
				for (int i = 0; i < queueSize; i++)// dequeue and insert to heap
					heapCopy.insert((int) queueCopy.deQueue().data);
			
				
				decode(code, answer, heapCopy, queueCopy, mapcopy_1);
			}

		} else {//if heap dosn't contain the letter
			int queueSize = (int) queueCopy.getSize();
			
			for (int i = 0; i < queueSize; i++)
				heapCopy.insert((int) queueCopy.deQueue().data);
			
			decode(code, answer, heapCopy, queueCopy, mapcopy_1);
		}
	}
}
