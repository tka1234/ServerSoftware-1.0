import org.hyperic.sigar.SigarException; import org.hyperic.sigar.cmd.Shell; import org.hyperic.sigar.cmd.SigarCommandBase;
public class CpuInfo extends SigarCommandBase {
	public CpuInfo(Shell shell) {super(shell); }
	public CpuInfo() {super(); }
	public static double getCPU() {
		try {return new CpuInfo().sigar.getCpuPerc().getCombined(); }
		catch (SigarException e) {e.printStackTrace(); return -1; } }
	public static void main(String[] args) throws Exception {
		while (true) {System.out.println(getCPU() * 100); } }
	public void output(String[] arg0) throws SigarException { } }
