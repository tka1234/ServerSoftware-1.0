public class ServerMain {
	public static int EcoModeSegment = 0;
	public static void main(String args[]) {
		boolean TextsEnabled = false; //TODO enable text message notifications.
		double TimeUntilMaint = 0, TimeUntilOn = 0, TimeUntilOff = 0;
		ServerLoad x = new ServerLoad();
		x.pack();
		x.setVisible(true);
		x.setResizable(false);
		x.setSize(300, 300);
		while (!ServerLoad.ChoiceMade) {
			try {Thread.sleep(200);}
			catch (InterruptedException e) {e.printStackTrace();} }
		x.dispose();
		OSD y = new OSD();
		y.pack();
		y.setVisible(true);
		y.setResizable(false);
		y.setSize(400, 200);
		y.CurrentActivityIndef(true);
		//y.SetCAProgress(0, "Launching Server..."); TODO FIX THIS PART making things runnable.
		//try {@SuppressWarnings("unused")
		//Process proc = Runtime.getRuntime().exec("java -jar " + ServerLoad.ServerLaunchPath); }
		//catch (IOException e1) {
		//	y.SetCAProgress(0, "Server launch failed. Check path.");
		//	try {Thread.sleep(3000); } catch (InterruptedException e) {e.printStackTrace(); } }
		y.SetCAProgress(0, "Reading Default Eco-Mode Schedule...");
		TimeSchedule.ReadEcoTimes();
		y.SetCAProgress(0, "Sending Startup Message...");
		if (TextsEnabled) SendMail.Email("LeLeLePhone@vtext.com", "Server has started under the " + ServerLoad.ServerName + " profile. " + TimeSchedule.TimeStamp());
		y.CurrentActivityIndef(false);
		y.SetCAProgress(0, "...");
		y.SetTimeProgress(100, "∞ until Power Off");
		int UpdateCountdown = 600;
		while (true) {
			if (OSD.ActionButtonPressed == "A") System.exit(0); //TODO make it close all open windows. Autohotkey Scripts.
			if (OSD.ActionButtonPressed == "C" && TimeSchedule.EcoMode) {
				TimeSchedule.EcoMode = false;
				EcoModeSegment = 0;
				OSD.ActionButtonPressed = ""; }
			else if (OSD.ActionButtonPressed == "C") {
				OSD.ActionButtonPressed = "";
				TimeSchedule sliders = new TimeSchedule();
				sliders.pack();
				sliders.setVisible(true);
				sliders.setResizable(false);
				sliders.setSize(400, 200); }
			y.EcoIndicator();
			y.SetCAProgress((int) Math.round(100 - (UpdateCountdown * 100) / 600), (UpdateCountdown / 2) + " seconds until update.");
	//Countdown to Next Major Action
			if (TimeSchedule.EcoMode) {
				y.SetTimeProgress(100, "Congratulations, You Found A Bug");
				TimeUntilOn = TimeSchedule.TimeUntil(TimeSchedule.ServerOn);
				TimeUntilOff = TimeSchedule.TimeUntil(TimeSchedule.ServerOff);
				TimeUntilMaint = TimeSchedule.TimeUntil(TimeSchedule.MaintTime);
				if (TimeUntilOn < TimeUntilOff && TimeUntilOn < TimeUntilMaint && TimeUntilOn > 0) {
					y.SetTimeProgress(100 - ((int) Math.round(100 * (TimeUntilOn / TimeSchedule.TimeBetween(TimeSchedule.MaintTime, TimeSchedule.ServerOn)))), (int) Math.floor(TimeUntilOn) + " hrs " + (int) Math.round((TimeUntilOn - Math.floor(TimeUntilOn)) * 60) + " min until Server On.");
					EcoModeSegment = 1; }
				if (TimeUntilOff < TimeUntilOn && TimeUntilOff < TimeUntilMaint && TimeUntilOff > 0) {
					y.SetTimeProgress(100 - ((int) Math.round(100 * (TimeUntilOff / TimeSchedule.TimeBetween(TimeSchedule.ServerOn, TimeSchedule.ServerOff)))), (int) Math.floor(TimeUntilOff) + " hrs " + (int) Math.round((TimeUntilOff - Math.floor(TimeUntilOff)) * 60) + " min until Server Off.");
					EcoModeSegment = 2; }
				if (TimeUntilMaint < TimeUntilOn && TimeUntilMaint < TimeUntilOff && TimeUntilMaint > 0) {
					y.SetTimeProgress(100 - ((int) Math.round(100 * (TimeUntilMaint / TimeSchedule.TimeBetween(TimeSchedule.ServerOff, TimeSchedule.MaintTime)))), (int) Math.floor(TimeUntilMaint) + " hrs " + (int) Math.round((TimeUntilMaint - Math.floor(TimeUntilMaint)) * 60) + " min until Maintenance.");
					EcoModeSegment = 3; } }
			else y.SetTimeProgress(100, "∞ until Power Off");
			if (EcoModeSegment == 1) {
				//do hibernation 1 min before server on.
				//make sure maintenance stops before this code triggers.
			}
			if (EcoModeSegment == 2) {
				//server is running until server off.
				//if server not running initialize everything.
			}
			if (EcoModeSegment == 3) {
				//hibernation until 1 min before maint.
				//a long sleep.
				//do the maintenance here?
			}
			y.Output("Current Segment for Eco: " + EcoModeSegment);
			if (UpdateCountdown == 0) {
				//TODO trigger update: check server status, cpu load/heat, players, network... AutoHotKey scripts...
				UpdateCountdown = 600; }
			try {Thread.sleep(500); }
			catch (InterruptedException e) {e.printStackTrace(); }
			UpdateCountdown--; } } }
