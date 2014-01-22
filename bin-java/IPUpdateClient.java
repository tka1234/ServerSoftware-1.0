import java.net.*; import java.io.*;
public class IPUpdateClient {
	public static String getIp() {
		try {
			URL AmazonIPCheck = new URL("http://checkip.amazonaws.com");
			BufferedReader in = new BufferedReader(new InputStreamReader(AmazonIPCheck.openStream()));
			return in.readLine(); }
		catch (Exception E) {
			E.printStackTrace();
			return "0"; } } }
