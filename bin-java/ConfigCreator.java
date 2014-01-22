import javax.swing.*; import javax.swing.border.Border; import java.awt.event.*; import java.awt.*; import java.io.*;
public class ConfigCreator extends JFrame {
	private static final long serialVersionUID = 1L;
	public ConfigCreator() {
		super("Edit Profile " + ServerLoad.RunningConfiguration + " ...");
		JPanel controlPanel = new JPanel(new GridLayout(4, 1));
		Border gap = BorderFactory.createEmptyBorder(5,5,5,5);
		controlPanel.setBorder(gap);
		try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
		catch (Exception e) {e.printStackTrace(); }
		JPanel panel1 = new JPanel(new GridLayout(1, 2));
		JLabel title1 = new JLabel("Profile Name:");
		panel1.add(title1);
		nameBox = new JTextField(32);
		nameBox.setText(ServerLoad.ServerName);
		panel1.add(nameBox);
		JPanel panel2 = new JPanel(new GridLayout(1, 2));
		JLabel title2 = new JLabel("JAR/EXE Location:");
		panel2.add(title2);
		pathBox = new JTextField(32);
		pathBox.setText(ServerLoad.ServerLaunchPath);
		panel2.add(pathBox);
		JPanel panel3 = new JPanel(new GridLayout(1, 2));
		JLabel title3 = new JLabel("Advanced Commands:");
		panel3.add(title3);
		commandsSwitch = new JButton("Commands On");
		commandsSwitch.addActionListener(new ConfigListener());
		panel3.add(commandsSwitch);
		JPanel panel4 = new JPanel(new GridLayout(1, 2));
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ConfigListener());
		saveButton = new JButton("Save");
		saveButton.setEnabled(false);
		saveButton.addActionListener(new ConfigListener());
		panel4.add(cancelButton);
		panel4.add(saveButton);
		controlPanel.add(panel1);
		controlPanel.add(panel2);
		controlPanel.add(panel3);
		controlPanel.add(panel4);
		setContentPane(controlPanel);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		TriggeredDisposal = false; }
	
	public boolean TriggeredDisposal;
	private JTextField nameBox, pathBox;
	private JButton commandsSwitch, cancelButton, saveButton;
	
	private class ConfigListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (commandsSwitch.isFocusOwner()) {
				if (commandsSwitch.getText() == "Commands On") commandsSwitch.setText("Commands Off");
				else commandsSwitch.setText("Commands On"); }
			else if (cancelButton.isFocusOwner()) {TriggeredDisposal = true;}
			else if (saveButton.isFocusOwner()) {
				nameBox.setEditable(false);
				pathBox.setEditable(false);
				commandsSwitch.setEnabled(false);
				PrintWriter out = null;
				try {out = new PrintWriter(new File("ServerConfig" + ServerLoad.RunningConfiguration + ".txt")); }
				catch (FileNotFoundException e) {e.printStackTrace(); }
				out.println(nameBox.getText());
				out.println(pathBox.getText());
				if (commandsSwitch.getText() == "Commands On") out.println("true");
				else out.println("false");
				out.close();
				TriggeredDisposal = true; } } }
	public static void main(String args[]) {
		ConfigCreator x = new ConfigCreator();
		x.pack();
		x.setVisible(true);
		x.setResizable(false);
		x.setSize(400, 150); } }
