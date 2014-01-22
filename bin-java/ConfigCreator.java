import javax.swing.*; import javax.swing.border.Border; import java.awt.event.*; import java.awt.*; import java.io.*;
public class ConfigCreator extends JFrame {
	private static final long serialVersionUID = 1L;
	public ConfigCreator() {
		super("AGS - Profile " + ServerLoad.RunningConfiguration);
		ServerMain.Log.writeToLog("Now editing Config " + ServerLoad.RunningConfiguration + ".");
		JPanel controlPanel = new JPanel(new GridLayout(3, 1));
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
		controlPanel.add(panel1);
		
		JPanel panel2 = new JPanel(new GridLayout(1, 2));
		JLabel title2 = new JLabel("JAR/EXE Location:");
		panel2.add(title2);
		pathBox = new JTextField(32);
		pathBox.setText(ServerLoad.ServerLaunchPath);
		panel2.add(pathBox);
		controlPanel.add(panel2);
		
		JPanel panel4 = new JPanel(new GridLayout(1, 2));
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ConfigListener());
		saveButton = new JButton("Save");
		saveButton.addActionListener(new ConfigListener());
		panel4.add(cancelButton);
		panel4.add(saveButton);
		controlPanel.add(panel4);
		
		setContentPane(controlPanel);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); }
	
	private JTextField nameBox, pathBox;
	private JButton cancelButton, saveButton;
	
	private class ConfigListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (cancelButton.isFocusOwner()) {
				ServerMain.Log.writeToLog("No changes made to Config " + ServerLoad.RunningConfiguration + ".");
				ServerLoad.CC.dispose();
				ServerMain.x = new ServerLoad();
				ServerMain.x.pack();
				ServerMain.x.setVisible(true);
				ServerMain.x.setResizable(false);
				ServerMain.x.setSize(300, 300);
				ServerMain.x.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width / 2) - 150, (Toolkit.getDefaultToolkit().getScreenSize().height / 2) - 150);
				ServerMain.Log.writeToLog(" ---Restarting GUI---"); }
			else if (saveButton.isFocusOwner()) {
				nameBox.setEditable(false);
				pathBox.setEditable(false);
				PrintWriter out = null;
				try {out = new PrintWriter(new File("ServerConfig" + ServerLoad.RunningConfiguration + ".txt")); }
				catch (FileNotFoundException e) {e.printStackTrace(); }
				out.println(nameBox.getText());
				out.println(pathBox.getText());
				out.close();
				ServerMain.Log.writeToLog("Changes were made to Config " + ServerLoad.RunningConfiguration + ".");
				ServerMain.Log.writeToLog("New Name = " + nameBox.getText() + " .");
				ServerMain.Log.writeToLog("New Path = " + pathBox.getText() + " .");
				ServerLoad.CC.dispose();
				ServerMain.x = new ServerLoad();
				ServerMain.x.pack();
				ServerMain.x.setVisible(true);
				ServerMain.x.setResizable(false);
				ServerMain.x.setSize(300, 300);
				ServerMain.x.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width / 2) - 150, (Toolkit.getDefaultToolkit().getScreenSize().height / 2) - 150);
				ServerMain.Log.writeToLog(" ---Restarting GUI---"); } } } }
