import javax.swing.*; import javax.swing.border.Border; 
import java.awt.event.*; import java.awt.*;
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
		controlPanel.add(TimeProgress);
		currentAction = new JProgressBar();
		currentAction.setMaximum(100);
		controlPanel.add(currentAction);
		JPanel ButtonsPanel = new JPanel(new GridLayout(1, 2));
		Shutdown = new JButton("Shut Down...");
		Shutdown.setIcon(new ImageIcon("PWR.png"));
		ButtonsPanel.add(Shutdown);
		Switch = new JButton("Change Profile...");
		ButtonsPanel.add(Switch);
		EcoButton = new JButton("Eco-Mode");
		EcoButton.setIcon(new ImageIcon("ECO - Copy.png"));
		ButtonsPanel.add(EcoButton);
		controlPanel.add(ButtonsPanel);
		ConsoleOutput = new JTextField(32);
		ConsoleOutput.setEditable(false);
		controlPanel.add(ConsoleOutput);
		setContentPane(controlPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ConsoleOutput.setText("Test of Server Software");
	}
	
	private JProgressBar currentAction, TimeProgress;
	private JTextField ConsoleOutput;
	private JButton Shutdown, Switch, EcoButton;
	
	public static void ServerIdle(OSD display) {
		while (true) System.out.println("server code");
	}
	public static void main(String args[]) {
		OSD x = new OSD();
		x.pack();
		x.setVisible(true);
		x.setResizable(false);
		x.setSize(400, 200);

} }
