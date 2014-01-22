import javax.swing.*; import javax.swing.border.Border; import java.awt.event.*; import java.awt.*; import java.io.*; import java.util.Scanner;
public class ServerLoad extends JFrame {
	private static final long serialVersionUID = 1L;
	public ServerLoad() {
		super("AGS - Choose Server");
		JPanel controlPanel = new JPanel( new GridLayout(6,2) );
		Border gap = BorderFactory.createEmptyBorder(5,5,5,5);
		controlPanel.setBorder(gap);
		try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
		catch (Exception e) {e.printStackTrace(); }
		File inFile; Scanner in = null;
		
		inFile = new File("ServerConfigA.txt");
		try {in = new Scanner(inFile); }
		catch (FileNotFoundException e) {e.printStackTrace(); }
		buttonA = new JButton(in.nextLine());
		buttonA.addActionListener(new ButtonListener());
		buttonA.setToolTipText(in.nextLine());
		controlPanel.add(buttonA);
		in.close();
		editA = new JButton("Edit...");
		editA.addActionListener(new ButtonListener());
		editA.setToolTipText("Edit or delete this configuration.");
		if (buttonA.getText().equals("Empty")) {
			buttonA.setEnabled(false);
			buttonA.setToolTipText(null);
			editA.setText("Create...");
			editA.setToolTipText(null); }
		controlPanel.add(editA);
		
		inFile = new File("ServerConfigB.txt");
		try {in = new Scanner(inFile); }
		catch (FileNotFoundException e) {e.printStackTrace(); }
		buttonB = new JButton(in.nextLine());
		buttonB.addActionListener(new ButtonListener());
		buttonB.setToolTipText(in.nextLine());
		controlPanel.add(buttonB);
		in.close();
		editB = new JButton("Edit...");
		editB.addActionListener(new ButtonListener());
		editB.setToolTipText("Edit or delete this configuration.");
		if (buttonB.getText().equals("Empty")) {
			buttonB.setEnabled(false);
			buttonB.setToolTipText(null);
			editB.setText("Create...");
			editB.setToolTipText(null); }
		controlPanel.add(editB);
		
		inFile = new File("ServerConfigC.txt");
		try {in = new Scanner(inFile); }
		catch (FileNotFoundException e) {e.printStackTrace(); }
		buttonC = new JButton(in.nextLine());
		buttonC.addActionListener(new ButtonListener());
		buttonC.setToolTipText(in.nextLine());
		controlPanel.add(buttonC);
		in.close();
		editC = new JButton("Edit...");
		editC.addActionListener(new ButtonListener());
		editC.setToolTipText("Edit or delete this configuration.");
		if (buttonC.getText().equals("Empty")) {
			buttonC.setEnabled(false);
			buttonC.setToolTipText(null);
			editC.setText("Create...");
			editC.setToolTipText(null); }
		controlPanel.add(editC);
		
		inFile = new File("ServerConfigD.txt");
		try {in = new Scanner(inFile); }
		catch (FileNotFoundException e) {e.printStackTrace(); }
		buttonD = new JButton(in.nextLine());
		buttonD.addActionListener(new ButtonListener());
		buttonD.setToolTipText(in.nextLine());
		controlPanel.add(buttonD);
		in.close();
		editD = new JButton("Edit...");
		editD.addActionListener(new ButtonListener());
		editD.setToolTipText("Edit or delete this configuration.");
		if (buttonD.getText().equals("Empty")) {
			buttonD.setEnabled(false);
			buttonD.setToolTipText(null);
			editD.setText("Create...");
			editD.setToolTipText(null); }
		controlPanel.add(editD);
		
		inFile = new File("ServerConfigE.txt");
		try {in = new Scanner(inFile); }
		catch (FileNotFoundException e) {e.printStackTrace(); }
		buttonE = new JButton(in.nextLine());
		buttonE.addActionListener(new ButtonListener());
		buttonE.setToolTipText(in.nextLine());
		controlPanel.add(buttonE);
		in.close();
		editE = new JButton("Edit...");
		editE.addActionListener(new ButtonListener());
		editE.setToolTipText("Edit or delete this configuration.");
		if (buttonE.getText().equals("Empty")) {
			buttonE.setEnabled(false);
			buttonE.setToolTipText(null);
			editE.setText("Create...");
			editE.setToolTipText(null); }
		controlPanel.add(editE);
		
		JLabel Footer = new JLabel("AGS Client 1.1A for Java 1.6");
		controlPanel.add(Footer);
		setContentPane(controlPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); }
	
	private JButton buttonA, buttonB, buttonC, buttonD, buttonE;
	private JButton editA, editB, editC, editD, editE;
	public static String RunningConfiguration = "", ServerLaunchPath = "", ServerName = "";
	public static boolean ChoiceMade = false;
	public static ConfigCreator CC;
	
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (buttonA.isFocusOwner() || buttonB.isFocusOwner() || buttonC.isFocusOwner() || buttonD.isFocusOwner() || buttonE.isFocusOwner()) {
				if (buttonA.isFocusOwner()) RunningConfiguration = "A";
				if (buttonB.isFocusOwner()) RunningConfiguration = "B";
				if (buttonC.isFocusOwner()) RunningConfiguration = "C";
				if (buttonD.isFocusOwner()) RunningConfiguration = "D";
				if (buttonE.isFocusOwner()) RunningConfiguration = "E";
				File inFile = new File("ServerConfig" + RunningConfiguration + ".txt");
				Scanner in = null;
				try {in = new Scanner(inFile); }
				catch (FileNotFoundException e) {e.printStackTrace(); }
				ServerName = in.nextLine();
				ServerLaunchPath = in.nextLine();
				in.close();
				ChoiceMade = true; }
			else {
				if (editA.isFocusOwner()) RunningConfiguration = "A";
				if (editB.isFocusOwner()) RunningConfiguration = "B";
				if (editC.isFocusOwner()) RunningConfiguration = "C";
				if (editD.isFocusOwner()) RunningConfiguration = "D";
				if (editE.isFocusOwner()) RunningConfiguration = "E";
				File inFile = new File("ServerConfig" + RunningConfiguration + ".txt");
				Scanner in = null;
				try {in = new Scanner(inFile); }
				catch (FileNotFoundException e) {e.printStackTrace(); }
				ServerName = in.nextLine();
				ServerLaunchPath = in.nextLine();
				in.close();
				CC = new ConfigCreator();
				CC.pack();
				CC.setVisible(true);
				CC.setSize(400, 150);
				CC.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width / 2) - 200, (Toolkit.getDefaultToolkit().getScreenSize().height / 2) - 75);
				ChoiceMade = false;
				ServerMain.x.dispose(); } } } }
