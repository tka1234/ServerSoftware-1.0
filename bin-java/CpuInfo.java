import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.cmd.Shell;
import org.hyperic.sigar.cmd.SigarCommandBase;
import org.hyperic.sigar.shell.ShellCommandExecException;
import org.hyperic.sigar.shell.ShellCommandUsageException;
public class CpuInfo extends SigarCommandBase {
	public CpuInfo(Shell shell) {super(shell); }
	public CpuInfo() {super(); }
	private static double output(CpuPerc cpu) {
		//println("User Time....." + CpuPerc.format(cpu.getUser()));
		//println("Sys Time......" + CpuPerc.format(cpu.getSys()));
		//println("Idle Time....." + CpuPerc.format(cpu.getIdle()));
		//println("Wait Time....." + CpuPerc.format(cpu.getWait()));
		//println("Nice Time....." + CpuPerc.format(cpu.getNice()));
		//println("Combined......" + CpuPerc.format(cpu.getCombined()));
		return cpu.getUser();
		//println("Irq Time......" + CpuPerc.format(cpu.getIrq())); 
		}
	public void output(String[] args) throws SigarException {output(this.sigar.getCpuPerc()); }
	
	public static double getCPU() throws ShellCommandUsageException, ShellCommandExecException {
		try {return output(new CpuInfo().sigar.getCpuPerc()); }
		catch (SigarException e) {e.printStackTrace(); return 0; } }
	
	public static void main(String[] args) throws Exception {new CpuInfo().processCommand(args); } }
