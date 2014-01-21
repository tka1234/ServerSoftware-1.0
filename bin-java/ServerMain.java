public class ServerMain {
	public static int EcoModeSegment = 0;
	public static boolean TextsEnabled = false;
	public static void main(String args[]) {
		detailedLog Log = new detailedLog();
		Log.pack();
		Log.setVisible(true);
		Log.setResizable(false);
		Log.writeToLog("Log called from ServerMain.");
		
		ServerLoad x = new ServerLoad();
		x.pack();
		x.setVisible(true);
		x.setResizable(false);
		x.setSize(300, 300);
		Log.writeToLog("Profiles panel created.");
		Log.writeToLog("Waiting for a choice.");
		
		while (!ServerLoad.ChoiceMade) {
			try {Thread.sleep(200);}
			catch (InterruptedException e) {e.printStackTrace();} }
		x.dispose();
		Log.writeToLog("Choice made: Profile " + ServerLoad.RunningConfiguration + " (" + ServerLoad.ServerName + ").");
		
		OSD y = new OSD();
		y.pack();
		y.setVisible(true);
		y.setResizable(false);
		y.setSize(400, 200);
		Log.writeToLog("OSD initialized.");
		
		y.CurrentActivityIndef(true);
		y.SetCAProgress(0, "Launching Server...");
		try {
			@SuppressWarnings("unused") Process proc = Runtime.getRuntime().exec("java -jar " + ServerLoad.ServerLaunchPath);
			Log.writeToLog("Server Jar launched at path " + ServerLoad.ServerLaunchPath + "."); }
		catch (Exception e1) {Log.writeToLog("Server jar failed to launch."); }
		
	//READ ECO MODE TIMES
		y.SetCAProgress(0, "Reading Default Eco-Mode Schedule...");
		TimeSchedule.ReadEcoTimes();
	//SEND STARTUP MESSAGE
		y.SetCAProgress(0, "Sending Startup Message...");
		if (TextsEnabled) SendMail.Email("2405666903@vtext.com", TimeSchedule.TimeStamp() + " Server has started under the *" + ServerLoad.ServerName + "* profile.");
		y.CurrentActivityIndef(false);
	//SET VARIABLES FOR RUNNING
		int UpdateCountdown = 600;
		double TimeUntilMaint = 0, TimeUntilOn = 0, TimeUntilOff = 0;
	//END OF STARTUP PROCESS
		while (true) {
			if (OSD.ActionButtonPressed == "A") System.exit(0); //TODO make it close the server window, too
			if (OSD.ActionButtonPressed == "B") {
				//TODO account for restart and changing running profile.
				OSD.ActionButtonPressed = "";
				System.out.println("LElelelelellle"); }
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
				TimeUntilOn = TimeSchedule.DeltaTimes(TimeSchedule.CurrentMilitaryTime(), TimeSchedule.ServerOn);
				TimeUntilOff = TimeSchedule.DeltaTimes(TimeSchedule.CurrentMilitaryTime(), TimeSchedule.ServerOff);
				TimeUntilMaint = TimeSchedule.DeltaTimes(TimeSchedule.CurrentMilitaryTime(), TimeSchedule.MaintTime);
				if (TimeUntilOn < TimeUntilOff && TimeUntilOn < TimeUntilMaint && TimeUntilOn > 0) {
					y.SetTimeProgress(100, TimeUntilOn + " Until On");
					EcoModeSegment = 1; }
				if (TimeUntilOff < TimeUntilOn && TimeUntilOff < TimeUntilMaint && TimeUntilOff > 0) {
					y.SetTimeProgress(100, TimeUntilOff + " Until Off");
					EcoModeSegment = 2; }
				if (TimeUntilMaint < TimeUntilOn && TimeUntilMaint < TimeUntilOff && TimeUntilMaint > 0) {
					y.SetTimeProgress(100, TimeUntilMaint + " Until Maint");
					EcoModeSegment = 3; } }
			else y.SetTimeProgress(0, "∞ until Power Off");
			if (EcoModeSegment == 1) {
				int SecondsToHibernate = (int) ((Math.floor(TimeUntilOn / 100) * 3600) + ((TimeUntilOn - Math.floor(TimeUntilOn / 100) * 100)) * 60) - 30;
				System.out.println(SecondsToHibernate + " HibernationSecs");
				if (SecondsToHibernate > 60) {
					//try {@SuppressWarnings("unused") Process hiber = Runtime.getRuntime().exec("sudo rtcwake -u -s " + SecondsToHibernate + " -m disk"); }
					//catch (Exception e1) {y.SetCAProgress(0, "Failed to sudo"); }
					System.out.println("we would be hibernating now");
				}
			}
			if (EcoModeSegment == 2) {
				//server is running until server off.
				//if server not running initialize everything.
			}
			if (EcoModeSegment == 3) {
				int SecondsToHibernate = (int) ((Math.floor(TimeUntilMaint / 100) * 3600) + ((TimeUntilMaint - Math.floor(TimeUntilMaint / 100) * 100)) * 60) - 30;
				System.out.println(SecondsToHibernate + " HibernationSecs");
				if (SecondsToHibernate > 60) {
					//try {@SuppressWarnings("unused") Process hiber = Runtime.getRuntime().exec("sudo rtcwake -u -s " + SecondsToHibernate + " -m disk"); }
					//catch (Exception e1) {y.SetCAProgress(0, "Failed to sudo"); }
					System.out.println("we would be hibernating now");
				}
				else {
					//here goes the maintenance stuff... backup the world... send a log
				}
			}
			try {y.Output("Current Segment for Eco: " + EcoModeSegment); }
			catch (Exception e1) {e1.printStackTrace(); }
			if (UpdateCountdown == 0) {
				//TODO trigger update: check server status, cpu load/heat, Players? Network?
				UpdateCountdown = 600; }
			try {Thread.sleep(500); }
			catch (InterruptedException e) {e.printStackTrace(); }
			UpdateCountdown--; } } }
