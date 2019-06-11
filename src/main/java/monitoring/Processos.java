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

import javax.swing.*;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

public class Processos {

    public static void exibir() {
        SystemInfo si = new SystemInfo();
        OperatingSystem os = si.getOperatingSystem(); 
        GlobalMemory memory = si.getHardware().getMemory();
        
        System.out.println("Processos: " + os.getProcessCount() + ", Threads: " + os.getThreadCount());

        List<OSProcess> procs = Arrays.asList(os.getProcesses(5, OperatingSystem.ProcessSort.CPU));

        System.out.println(" PID     CPU(%)     Memória(%)     VSZ      RSS     Name");
        for (int cont = 0; cont < procs.size() && cont < 5; cont++) {
                OSProcess p = procs.get(cont);


                JOptionPane.showMessageDialog(null, "  PID     CPU(%)     Memória(%)        VSZ         " +
                        "       RSS          Name\n" +
                        String.format("%5d   %5.1f            %4.1f            %9s        %9s      %s     %n", p.getProcessID(),
                                100d * (p.getKernelTime() + p.getUserTime()) / p.getUpTime(),
                                100d * p.getResidentSetSize() / memory.getTotal(), FormatUtil.formatBytes(p.getVirtualSize()),
                                FormatUtil.formatBytes(p.getResidentSetSize()), p.getName()));

        }

    }

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
