import javax.swing.*; import javax.swing.border.Border; import java.awt.event.*; import java.awt.*; import java.io.*; import java.util.Scanner;
public class ServerLoad extends JFrame {
	private static final long serialVersionUID = 1L;
	public ServerLoad() {
		super("AGS - Choose Server");
		JPanel controlPanel = new JPanel( new GridLayout(6,1) );
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
		JLabel Footer = new JLabel("AGS Client 1.0A for Java 1.6");
		controlPanel.add(Footer);
		setContentPane(controlPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); }
	
	private JButton buttonA, buttonB, buttonC, buttonD, buttonE;
	public static String RunningConfiguration = "", ServerLaunchPath = "", ServerName = "";
	public static boolean CommandsEnabled = false, ChoiceMade = false;
	
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
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
			CommandsEnabled = in.nextBoolean();
			in.close();
			
			ConfigCreator x = new ConfigCreator();
			x.pack();
			x.setVisible(true);
			x.setResizable(false);
			x.setSize(400, 150);
			while (!x.TriggeredDisposal) {
				try {Thread.sleep(500); }
				catch (InterruptedException e) {e.printStackTrace(); } }
			x.dispose();
			
			ChoiceMade = true; } } }
