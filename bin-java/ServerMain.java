public class ServerMain {
	public static void main(String args[]) {
		boolean TextsEnabled = false; //TODO enable text message notifications.
		double TimeUntilMaint, TimeUntilOn, TimeUntilOff, MinutesSection, RealTimeAway = 0;
		String ActionSentence = "";
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
		if (TextsEnabled) SendMail.Email("2405666903@vtext.com", "Server has started under the " + ServerLoad.ServerName + " profile. " + TimeSchedule.TimeStamp());
		y.CurrentActivityIndef(false);
		y.SetCAProgress(0, "...");
		y.SetTimeProgress(100, "∞ until Power Off");
		int UpdateCountdown = 600;
		while (true) {
			if (OSD.ActionButtonPressed == "A") System.exit(0); //TODO make it close all open windows. Autohotkey Scripts.
			if (OSD.ActionButtonPressed == "C" && TimeSchedule.EcoMode) {
				TimeSchedule.EcoMode = false;
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
				TimeUntilMaint = (TimeSchedule.MaintTime / 100) - TimeSchedule.CurrentHour();
				TimeUntilOn = (TimeSchedule.ServerOn / 100) - TimeSchedule.CurrentHour();
				TimeUntilOff = (TimeSchedule.ServerOff / 100) - TimeSchedule.CurrentHour();
				MinutesSection = TimeSchedule.CurrentMinute(); MinutesSection = MinutesSection / 60;
				if (TimeUntilMaint < 0) TimeUntilMaint = 24 - TimeSchedule.CurrentHour() + (TimeSchedule.MaintTime / 100);
				if (TimeUntilOn < 0) TimeUntilOn = 24 - TimeSchedule.CurrentHour() + (TimeSchedule.ServerOn / 100);
				if (TimeUntilOff < 0) TimeUntilOff = 24 - TimeSchedule.CurrentHour() + (TimeSchedule.ServerOff / 100);
				if (TimeUntilMaint < TimeUntilOn && TimeUntilMaint < TimeUntilOff) {
					RealTimeAway = TimeUntilMaint - 1 + (1 - MinutesSection);
					ActionSentence = "Maintenance"; }
				else if (TimeUntilOn < TimeUntilMaint && TimeUntilOn < TimeUntilOff) {
					RealTimeAway = TimeUntilOn - 1 + (1 - MinutesSection);
					ActionSentence = "Server On"; }
				else if (TimeUntilOff < TimeUntilMaint && TimeUntilOff < TimeUntilOn) {
					RealTimeAway = TimeUntilOff - 1 + (1 - MinutesSection);
					ActionSentence = "Server Off"; }
				y.SetTimeProgress(10, ((int) Math.floor(RealTimeAway)) + " hrs " + (int) Math.round(((RealTimeAway - Math.floor(RealTimeAway)) * 60)) + " min until " + ActionSentence); }
			else y.SetTimeProgress(100, "∞ until Power Off");
			y.Output("Current Time: " + TimeSchedule.CurrentMilitaryTime());
			if (UpdateCountdown == 0) {
				//TODO trigger update: check server status, cpu load/heat, players, network... AutoHotKey scripts...
				UpdateCountdown = 600; }
			try {Thread.sleep(500); }
			catch (InterruptedException e) {e.printStackTrace(); }
			UpdateCountdown--;
		}
		
		
	}

}
