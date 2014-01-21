import javax.swing.*; import javax.swing.border.Border; import java.awt.*; import java.awt.event.*; import java.io.*;
public class detailedLog extends JFrame {
	private static final long serialVersionUID = 1L;
	public detailedLog() {
		super("AGS Software Log");
		JPanel controlPanel = new JPanel(new BorderLayout(2,1));
		Border gap = BorderFactory.createEmptyBorder(5,5,5,5);
		controlPanel.setBorder(gap);
		try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
		catch (Exception e) {e.printStackTrace(); }
		logbox = new JTextArea(5,20);
		scrollingPane = new JScrollPane(logbox);
		scrollingPane.setPreferredSize(new Dimension(700, 200));
		logbox.setEditable(false);
		controlPanel.add(scrollingPane, BorderLayout.PAGE_START);
		saveButton = new JButton("Save Log");
		saveButton.addActionListener(new ApplyListener());
		saveButton.setPreferredSize(new Dimension(100, 50));
		controlPanel.add(saveButton, BorderLayout.PAGE_END);
		setContentPane(controlPanel);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		logbox.append("AGS Software Log activated.");
		logbox.setFont(new Font("SansSerif", Font.PLAIN, 11));}
	private JButton saveButton;
	private JTextArea logbox;
	private JScrollPane scrollingPane;
	private class ApplyListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			PrintWriter out = null;
			try {out = new PrintWriter(new File(TimeSchedule.CurrentMilitaryTime() + "_" + TimeSchedule.CurrentDate() + "_Log.txt"));}
			catch (FileNotFoundException e) {e.printStackTrace(); }
			out.print(logbox.getText());
			out.close();
			
		} }
	public static void main(String args[]) {
		detailedLog x = new detailedLog();
		x.pack();
		x.setVisible(true);
		x.setResizable(false);
		int potato = 0;
		while (potato < 100) {
			potato++;
			x.writeToLog("Hello World!");
		}
		}
	public void writeToLog(String text) {
		logbox.append("\n" + TimeSchedule.TimeStamp() + " " + text);
		logbox.setCaretPosition(logbox.getDocument().getLength()); } }
