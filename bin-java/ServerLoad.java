import javax.swing.*; import javax.swing.border.Border; 

import java.awt.event.*; import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class ServerLoad extends JFrame{
	private static final long serialVersionUID = 1L;
	public ServerLoad() {
		super("Choose Server");
		JPanel controlPanel = new JPanel( new GridLayout(6,1) );
		controlPanel.setSize(900, 900);
		Border gap = BorderFactory.createEmptyBorder(5,5,5,5);
		controlPanel.setBorder(gap);
		try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
		catch (ClassNotFoundException e) {e.printStackTrace(); }
		catch (InstantiationException e) {e.printStackTrace(); }
		catch (IllegalAccessException e) {e.printStackTrace(); }
		catch (UnsupportedLookAndFeelException e) {e.printStackTrace(); }
		File inFile; Scanner in = null;
		inFile = new File("ServerConfigA.txt");
		try {in = new Scanner(inFile); }
		catch (FileNotFoundException e) {e.printStackTrace(); }
		buttonA = new JButton(in.nextLine());
		buttonA.addActionListener(new ButtonListener());
		controlPanel.add(buttonA);
		in.close();
		inFile = new File("ServerConfigB.txt");
		try {in = new Scanner(inFile); }
		catch (FileNotFoundException e) {e.printStackTrace(); }
		buttonB = new JButton(in.nextLine());
		buttonB.addActionListener(new ButtonListener());
		controlPanel.add(buttonB);
		in.close();
		inFile = new File("ServerConfigC.txt");
		try {in = new Scanner(inFile); }
		catch (FileNotFoundException e) {e.printStackTrace(); }
		buttonC = new JButton(in.nextLine());
		buttonC.addActionListener(new ButtonListener());
		controlPanel.add(buttonC);
		in.close();
		inFile = new File("ServerConfigD.txt");
		try {in = new Scanner(inFile); }
		catch (FileNotFoundException e) {e.printStackTrace(); }
		buttonD = new JButton(in.nextLine());
		buttonD.addActionListener(new ButtonListener());
		controlPanel.add(buttonD);
		in.close();
		inFile = new File("ServerConfigE.txt");
		try {in = new Scanner(inFile); }
		catch (FileNotFoundException e) {e.printStackTrace(); }
		buttonE = new JButton(in.nextLine());
		buttonE.addActionListener(new ButtonListener());
		controlPanel.add(buttonE);
		in.close();
		JLabel Footer = new JLabel("AMD Gaming Server Client 1.0A");
		controlPanel.add(Footer);
		setContentPane(controlPanel);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private JButton buttonA, buttonB, buttonC, buttonD, buttonE;
	
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			String configChoice = "";
			if (buttonA.isFocusOwner()) configChoice = "A";
			if (buttonB.isFocusOwner()) configChoice = "B";
			if (buttonC.isFocusOwner()) configChoice = "C";
			if (buttonD.isFocusOwner()) configChoice = "D";
			if (buttonE.isFocusOwner()) configChoice = "E";
			File inFile = new File("ServerConfig" + configChoice + ".txt");
			Scanner in = null;
			try {in = new Scanner(inFile); }
			catch (FileNotFoundException e) {e.printStackTrace(); }
			System.out.print("--New Server--\n" + in.nextLine() + "\nLocation: " + in.nextLine() + "\nCommands Enabled: " + in.nextBoolean() + "\n");
			in.close();
			
			} }

	public static void main(String args[]) {
		ServerLoad x = new ServerLoad();
		x.pack();
		x.setVisible(true);
		x.setResizable(false);
		x.setSize(300, 300);

} }
