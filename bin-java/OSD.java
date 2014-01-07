import javax.swing.*; import javax.swing.border.Border; 
import java.awt.event.*; import java.awt.*;
public class OSD extends JFrame{
	private static final long serialVersionUID = 1L;
	public OSD() {
		super("AGS Controller 1.0A");
		JPanel controlPanel = new JPanel( new GridLayout(2,1) );
		Border gap = BorderFactory.createEmptyBorder(5,5,5,5);
		controlPanel.setBorder(gap);
		try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
		catch (ClassNotFoundException e) {e.printStackTrace(); }
		catch (InstantiationException e) {e.printStackTrace(); }
		catch (IllegalAccessException e) {e.printStackTrace(); }
		catch (UnsupportedLookAndFeelException e) {e.printStackTrace(); }
		currentAction = new JProgressBar();
		currentAction.setMaximum(100);
		currentAction.setValue(50);
		controlPanel.add(currentAction);
		setContentPane(controlPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private JProgressBar currentAction;
	public static void main(String args[]) {
		OSD x = new OSD();
		x.pack();
		x.setVisible(true);
		
		x.currentAction.setIndeterminate(true);
		//x.currentAction.setValue(50);}

} }
