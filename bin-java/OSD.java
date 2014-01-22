import javax.swing.*; import javax.swing.border.Border; import java.awt.event.*; import java.awt.*;
public class OSD extends JFrame{
	private static final long serialVersionUID = 1L;
	public OSD() {
		super("AGS Controller 1.0A");
		JPanel controlPanel = new JPanel(new GridLayout(4, 1));
		Border gap = BorderFactory.createEmptyBorder(5,5,5,5);
		controlPanel.setBorder(gap);
		try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
		catch (ClassNotFoundException e) {e.printStackTrace(); }
		catch (InstantiationException e) {e.printStackTrace(); }
		catch (IllegalAccessException e) {e.printStackTrace(); }
		catch (UnsupportedLookAndFeelException e) {e.printStackTrace(); }
		
		TimeProgress = new JProgressBar();
		TimeProgress.setMaximum(100);
		TimeProgress.setStringPainted(true);
		TimeProgress.setString("Time to Action:");
		controlPanel.add(TimeProgress);
		
		currentAction = new JProgressBar();
		currentAction.setMaximum(100);
		currentAction.setStringPainted(true);
		currentAction.setString("Checking status in 45 seconds.");
		controlPanel.add(currentAction);
		
		JPanel ButtonsPanel = new JPanel(new GridLayout(1, 2));
		
		Shutdown = new JButton("Shut Down...");
		Shutdown.setIcon(new ImageIcon("PWR.png"));
		Shutdown.addActionListener(new OSDListener());
		ButtonsPanel.add(Shutdown);
		
		Switch = new JButton("Change Profile...");
		Switch.addActionListener(new OSDListener());
		ButtonsPanel.add(Switch);
		
		EcoButton = new JButton("Eco-Mode Off");
		EcoButton.setIcon(new ImageIcon("ECO.png"));
		EcoButton.addActionListener(new OSDListener());
		ButtonsPanel.add(EcoButton);
		
		controlPanel.add(ButtonsPanel);
		ConsoleOutput = new JTextField(32);
		ConsoleOutput.setEditable(false);
		controlPanel.add(ConsoleOutput);
		setContentPane(controlPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ConsoleOutput.setText("Test of Server Software"); }
	
	private class OSDListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (Shutdown.isFocusOwner()) ActionButtonPressed = "A";
			if (Switch.isFocusOwner()) ActionButtonPressed = "B";
			if (EcoButton.isFocusOwner()) ActionButtonPressed = "C"; } }
	
	public static String ActionButtonPressed = "";
	private JProgressBar currentAction, TimeProgress;
	private JTextField ConsoleOutput;
	private JButton Shutdown, Switch, EcoButton;
	
	public void SetTimeProgress(int value, String text) {
		TimeProgress.setValue(value);
		TimeProgress.setString(text); }
	public void CurrentActivityIndef(boolean choice) {currentAction.setIndeterminate(choice); }
	public void SetCAProgress(int value, String text) {
		currentAction.setValue(value);
		currentAction.setString(text); }
	public void EcoIndicator() {
		if (TimeSchedule.EcoMode) {
			EcoButton.setText("Eco-Mode ON");
			EcoButton.setIcon(new ImageIcon("ECO.png")); }
		else {
			EcoButton.setText("Eco-Mode Off");
			EcoButton.setIcon(new ImageIcon("ECOOFF.png")); } }
	public void Output(String text) {ConsoleOutput.setText(text); } }
