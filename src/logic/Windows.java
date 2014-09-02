package logic;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.text.DefaultCaret;

import logic.Simulation;
import javax.swing.JCheckBox;

public class Windows {

	private JFrame frame;
	private JTextField textField;
	private JButton btntart;
	private JTextArea textArea;
	private JLabel lblPoetud;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JCheckBox chckbxSpoznaniePomocouPriateov;
	private JCheckBox chckbxSpoznaniePomocouNajobbenejch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Windows window = new Windows();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Windows() {
		initialize();
		
		textField_1.setText("3000");
		textField_2.setText("500");
		textField_3.setText("10000");
		textField_4.setText("1000");
		textField.setText("1000");

		chckbxSpoznaniePomocouPriateov.setSelected(true);
		chckbxSpoznaniePomocouNajobbenejch.setSelected(false);
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		frame = new JFrame(
				"SimulaËn˝ model siete vzùahov - Modelovanie a Simul·cia: SlavomÌr ä·rik");
		frame.setBounds(100, 100, 1700, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 1664, 739);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		textField = new JTextField();
		textField.setBounds(1418, 8, 86, 20);
		panel.add(textField);
		textField.setColumns(10);

		btntart = new JButton("\u0160tart ");
		btntart.setBounds(1565, 7, 89, 23);
		panel.add(btntart);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 73, 1644, 655);
		panel.add(scrollPane);

		textArea = new JTextArea();
		textArea.setFont(new Font("Courier New", Font.PLAIN, 16));
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		DefaultCaret caret = (DefaultCaret) textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		scrollPane.setViewportView(textArea);

		lblPoetud = new JLabel("Po\u010Det \u013Eud\u00ED:");
		lblPoetud.setBounds(10, 11, 66, 14);
		panel.add(lblPoetud);

		textField_1 = new JTextField();
		textField_1.setBounds(94, 8, 86, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblMaxPoetPriateov = new JLabel(
				"Max. po\u010Det priate\u013Eov:");
		lblMaxPoetPriateov.setBounds(250, 11, 114, 14);
		panel.add(lblMaxPoetPriateov);

		textField_2 = new JTextField();
		textField_2.setBounds(374, 8, 86, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);

		JLabel lblPoetNhodnchSpoznan = new JLabel(
				"Po\u010Det n\u00E1hodn\u00FDch spoznan\u00ED: ");
		lblPoetNhodnchSpoznan.setBounds(556, 11, 142, 14);
		panel.add(lblPoetNhodnchSpoznan);

		textField_3 = new JTextField();
		textField_3.setBounds(708, 8, 86, 20);
		panel.add(textField_3);
		textField_3.setColumns(10);

		JLabel lblPoetSpoznanCez = new JLabel(
				"Po\u010Det spoznan\u00ED cez priate\u013Eov:");
		lblPoetSpoznanCez.setBounds(847, 11, 164, 14);
		panel.add(lblPoetSpoznanCez);

		textField_4 = new JTextField();
		textField_4.setBounds(1006, 8, 86, 20);
		panel.add(textField_4);
		textField_4.setColumns(10);

		JLabel lblPoetSpoznanCez_1 = new JLabel(
				"Po\u010Det spoznan\u00ED  cez najob\u013E\u00FAbenej\u0161\u00EDch priate\u013Eov:");
		lblPoetSpoznanCez_1.setBounds(1170, 11, 238, 14);
		panel.add(lblPoetSpoznanCez_1);

		chckbxSpoznaniePomocouPriateov = new JCheckBox(
				"Spoznanie pomocou priate\u013Eov");
		chckbxSpoznaniePomocouPriateov.setBounds(1170, 43, 173, 23);
		panel.add(chckbxSpoznaniePomocouPriateov);

		chckbxSpoznaniePomocouNajobbenejch = new JCheckBox(
				"Spoznanie pomocou najob\u013E\u00FAbenej\u0161\u00EDch priate\u013Eov");
		chckbxSpoznaniePomocouNajobbenejch.setBounds(1393, 43, 261, 23);
		panel.add(chckbxSpoznaniePomocouNajobbenejch);

		btntart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				textArea.setText("");
				Simulation s = new Simulation(textArea);


				
				s.simulate(Integer.parseInt(textField_1.getText()),
						Integer.parseInt(textField_2.getText()),
						Integer.parseInt(textField_3.getText()),
						Integer.parseInt(textField_4.getText()),
						Integer.parseInt(textField.getText()),
						chckbxSpoznaniePomocouPriateov.isSelected(),
						chckbxSpoznaniePomocouNajobbenejch.isSelected());

			}
		});

	}

	public JButton getBtnstart() {
		return btntart;
	}

	public JTextField getTextField() {
		return textField;
	}

	public JTextArea getTextArea() {
		return textArea;
	}
}
