package telas;

import database.Database;
import menu.Init;
import monitoring.*;
import slack.Slack;

public class Monitoring {

    private int machineId;
    private double alertCPU;
    private double alertRAM;
    private double alertDisk;
    Slack slack = new Slack();
    Database db = new Database();
    Cpu cpu = new Cpu();
    Ram ram = new Ram();
    Disk disk = new Disk();

    public void update() {
        System.out.println("==============================================================================================================================");
        System.out.println("A aplicação foi iniciada! Para visualizar os dados, acesse: https://hawkeye-technology.outsystemscloud.com/HAWKEYETECHNOLOGY");
        System.out.println("==============================================================================================================================");
        while (true) {

            db.insertComponent(disk.totalDisco(0), disk.freeDiskPercent(0), disk.disponivelDisco(0), cpu.modeloCpu(Init.hal.getProcessor()) ,
                    cpu.usedCpuPercent(Init.hal.getProcessor()), Init.os.getProcessCount(), ram.totalRam(Init.hal.getMemory()), ram.freeRamPercent(Init.hal.getMemory()),
                    ram.avaibleRam(Init.hal.getMemory()), getMachineId());

            try {Thread.sleep(5000);
            }
            catch (Exception e) {}
        }
    }


    public int getMachineId() {
        return machineId;
    }

    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    public double getAlertCPU() {
        return alertCPU;
    }

    public void setAlertCPU(double alertCPU) {
        this.alertCPU = alertCPU;
    }

    public double getAlertRAM() {
        return alertRAM;
    }

    public void setAlertRAM(double alertRAM) {
        this.alertRAM = alertRAM;
    }

    public double getAlertDisk() {
        return alertDisk;
    }

    public void setAlertDisk(double alertDisk) {
        this.alertDisk = alertDisk;
    }
}
