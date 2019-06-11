/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monitoring;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;
import oshi.util.FormatUtil;

import java.text.DecimalFormat;

public class Processes {

    //Retorna os processos que estão sendo executados
    public static String[][] getProcessesAsStringTable(int numberOfProcesses) {
        DecimalFormat df = new DecimalFormat("#.##");

        //order: [0] Name    [1] PID    [2] Using %CPU     [3] Using RAM    [4] Using %RAM
        String[][] arrayStringTable = new String[numberOfProcesses][5];
        String[] arrayString = new String[numberOfProcesses];

        OSProcess[] procs = getProcesses(numberOfProcesses);

        for (int count = 0; count < arrayString.length; count++) {
            arrayStringTable[count][0] = procs[count].getName();
            arrayStringTable[count][1] = String.valueOf(procs[count].getProcessID());
            arrayStringTable[count][2] = df.format(100d * (procs[count].getKernelTime() + procs[count].getUserTime()) / procs[count].getUpTime());
            arrayStringTable[count][3] = FormatUtil.formatBytes(procs[count].getResidentSetSize());
            arrayStringTable[count][4] = df.format(100d * procs[count].getResidentSetSize() / getMemory().getTotal());
        }

        return arrayStringTable;
    }

    //Imports necessários para o funcionamento do metodo 'getProcessesAsStringTable'
    private static SystemInfo systemInfo = new SystemInfo();

    public static OperatingSystem getOs() {
        return systemInfo.getOperatingSystem();
    }

    public static HardwareAbstractionLayer getHardware() {
        return systemInfo.getHardware();
    }

    public static CentralProcessor getCpu() {
        return getHardware().getProcessor();
    }

    public static OSProcess[] getProcesses() {
        return getOs().getProcesses(0, OperatingSystem.ProcessSort.MEMORY);
    }

    public static OSProcess[] getProcesses(int numberOfProcesses) {
        return getOs().getProcesses(numberOfProcesses, OperatingSystem.ProcessSort.MEMORY);
    }

    public static GlobalMemory getMemory() {
        return getHardware().getMemory();
    }
}
