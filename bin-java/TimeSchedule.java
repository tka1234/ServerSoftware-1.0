import javax.swing.*; import javax.swing.border.Border; 
import java.awt.*; import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.*; 
import java.io.*; import java.util.*;
public class TimeSchedule extends JFrame {
	private static final long serialVersionUID = 1L;
	public TimeSchedule() {
		super("Eco-Mode Scheduler");
		JPanel controlPanel = new JPanel(new GridLayout(5, 1));
		Border gap = BorderFactory.createEmptyBorder(5,5,5,5);
		controlPanel.setBorder(gap);
		try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
		catch (ClassNotFoundException e) {e.printStackTrace(); }
		catch (InstantiationException e) {e.printStackTrace(); }
		catch (IllegalAccessException e) {e.printStackTrace(); }
		catch (UnsupportedLookAndFeelException e) {e.printStackTrace(); }
		
		JPanel Panel1 = new JPanel(new GridLayout(1, 2));
		onTime = new JSlider(0, 2359, ServerOn);
		onTime.setMajorTickSpacing(50);
		onTime.addChangeListener(new OnSliderListener());
		onTime.setPaintTicks(true);
		onTime.setSnapToTicks(true);
		onTime.setPreferredSize(new Dimension(200, 100));
		Panel1.add(onTime);
		onTimeLabel = new JLabel("Turn On @: " + ServerOn);
		onTimeLabel.setIcon(new ImageIcon("PWR.png"));
		Panel1.add(onTimeLabel);
		controlPanel.add(Panel1);
		
		JPanel Panel2 = new JPanel(new GridLayout(1, 2));
		offTime = new JSlider(0, 2359, ServerOff);
		offTime.setMajorTickSpacing(50);
		offTime.addChangeListener(new OffSliderListener());
		offTime.setPaintTicks(true);
		offTime.setSnapToTicks(true);
		Panel2.add(offTime);
		offTimeLabel = new JLabel("Turn Off @: " + ServerOff);
		offTimeLabel.setIcon(new ImageIcon("HIBER.png"));
		Panel2.add(offTimeLabel);
		controlPanel.add(Panel2);
		
		JPanel Panel3 = new JPanel(new GridLayout(1, 2));
		mTime = new JSlider(0, 2359, MaintTime);
		mTime.setMajorTickSpacing(50);
		mTime.addChangeListener(new mSliderListener());
		mTime.setPaintTicks(true);
		mTime.setSnapToTicks(true);
		mTime.setSize(200, 64);
		Panel3.add(mTime);
		mTimeLabel = new JLabel("Maintenance @: " + MaintTime);
		mTimeLabel.setIcon(new ImageIcon("MAINT.png"));
		Panel3.add(mTimeLabel);
		controlPanel.add(Panel3);
		
		ErrorPane = new JLabel("Configuration valid.");
		ErrorPane.setIcon(new ImageIcon("CHECK.png"));
		controlPanel.add(ErrorPane);
		
		JPanel ButtonsPane = new JPanel(new GridLayout(1, 3));
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ApplyListener());
		ButtonsPane.add(cancelButton);
		saveButton = new JButton("Save");
		saveButton.setEnabled(false);
		saveButton.addActionListener(new ApplyListener());
		ButtonsPane.add(saveButton);
		enableButton = new JButton("Apply");
		enableButton.addActionListener(new ApplyListener());
		ButtonsPane.add(enableButton);
		controlPanel.add(ButtonsPane);
		
		setContentPane(controlPanel);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}
	
	private JSlider onTime, offTime, mTime;
	private JLabel onTimeLabel, offTimeLabel, mTimeLabel, ErrorPane;
	private JButton cancelButton, saveButton, enableButton;
	
	private class ApplyListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (cancelButton.isFocusOwner()) dispose();
			if (saveButton.isFocusOwner()) {
				ServerOn = onTime.getValue();
				ServerOff = offTime.getValue();
				MaintTime = mTime.getValue();
				File inFile = new File("EcoSched.txt");
				PrintWriter out = null;
				try {out = new PrintWriter(inFile);}
				catch (FileNotFoundException e) {e.printStackTrace(); }
				out.println(ServerOn);
				out.println(ServerOff);
				out.println(MaintTime);
				out.close();
				dispose(); }
			if (enableButton.isFocusOwner()) {
				ServerOn = onTime.getValue();
				ServerOff = offTime.getValue();
				MaintTime = mTime.getValue();
				File inFile = new File("EcoSched.txt");
				PrintWriter out = null;
				try {out = new PrintWriter(inFile);}
				catch (FileNotFoundException e) {e.printStackTrace(); }
				out.println(ServerOn);
				out.println(ServerOff);
				out.println(MaintTime);
				out.close();
				EcoMode = true;
				dispose(); } } }
	
	private class OnSliderListener implements ChangeListener {
		public void stateChanged(ChangeEvent event) {
			onTimeLabel.setText("Turn On @: " + onTime.getValue());
			ProcessChanges(); } }
	private class OffSliderListener implements ChangeListener {
		public void stateChanged(ChangeEvent event) {
			offTimeLabel.setText("Turn Off @: " + offTime.getValue());
			ProcessChanges(); } }
	private class mSliderListener implements ChangeListener {
		public void stateChanged(ChangeEvent event) {
			mTimeLabel.setText("Maintenance @: " + mTime.getValue());
			ProcessChanges(); } }
	
	public void ProcessChanges() {
		if (onTime.getValue() == offTime.getValue()) {
			ErrorPane.setIcon(new ImageIcon("ERROR.png"));
			ErrorPane.setText("Start Time cannot equal Shutdown Time.");
			saveButton.setEnabled(false);
			enableButton.setEnabled(false); }
		else if (onTime.getValue() == mTime.getValue() || offTime.getValue() == mTime.getValue()) {
			ErrorPane.setIcon(new ImageIcon("ERROR.png"));
			ErrorPane.setText("Maintenance cannot be performed while server is starting/closing.");
			saveButton.setEnabled(false);
			enableButton.setEnabled(false); }
		else if (mTime.getValue() < offTime.getValue() && onTime.getValue() > offTime.getValue()) {
			ErrorPane.setIcon(new ImageIcon("ERROR.png"));
			ErrorPane.setText("Maintenance cannot be performed while server is running.");
			saveButton.setEnabled(false);
			enableButton.setEnabled(false); }
		else if (onTime.getValue() > offTime.getValue() && mTime.getValue() > onTime.getValue()) {
			ErrorPane.setIcon(new ImageIcon("ERROR.png"));
			ErrorPane.setText("Maintenance cannot be performed while server is running.");
			saveButton.setEnabled(false);
			enableButton.setEnabled(false); }
		else if (offTime.getValue() > onTime.getValue() && mTime.getValue() > onTime.getValue() && mTime.getValue() < offTime.getValue()) {
			ErrorPane.setIcon(new ImageIcon("ERROR.png"));
			ErrorPane.setText("Maintenance cannot be performed while server is running.");
			saveButton.setEnabled(false);
			enableButton.setEnabled(false); }
		else {
			ErrorPane.setIcon(new ImageIcon("CHECK.png"));
			ErrorPane.setText("Configuration valid. Changes have been made.");
			saveButton.setEnabled(true);
			enableButton.setEnabled(true);
			enableButton.setText("Save & Apply");
		}
	}
	
	public static int ServerOn, ServerOff, MaintTime;
	public static boolean EcoMode = false;
	
	public static void ReadEcoTimes() {
		File inFile = new File("EcoSched.txt");
		Scanner in = null;
		try {in = new Scanner(inFile);}
		catch (FileNotFoundException e) {e.printStackTrace(); }
		ServerOn = in.nextInt();
		ServerOff = in.nextInt();
		MaintTime = in.nextInt();
		in.close(); }
	public static double TimeUntil(int time) {
		double tm = (time / 100) - CurrentHour();
		if (tm < 0) tm = 24 - CurrentHour() + (time/100);
		double fail = Calendar.getInstance().get(Calendar.MINUTE);
		fail = fail / 60;
		tm = tm - fail;
		return tm; }
	public static double TimeBetween(int time1, int time2) {
		double tm = (time2 / 100) - (time1 / 100);
		if (tm < 0) tm = 24 - (time1 / 100) + (time2 / 100);
		return tm; }
	
	public static String TimeStamp() {return "*" + Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":" + Calendar.getInstance().get(Calendar.MINUTE) + "*"; }
	public static int CurrentMilitaryTime() {return (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) * 100) + Calendar.getInstance().get(Calendar.MINUTE); }
	public static int CurrentHour() {return Calendar.getInstance().get(Calendar.HOUR_OF_DAY); }
	public static int CurrentMinute() {return Calendar.getInstance().get(Calendar.MINUTE); }
}
