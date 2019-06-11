package monitoring;

import appLogs.Logger;
import oshi.hardware.GlobalMemory;
import oshi.util.FormatUtil;

import javax.swing.*;
import java.io.IOException;
import java.text.DecimalFormat;

//import appLogs.Logger;

public class Ram {
    public String maxRAM;
    public double alertLevel;
    public String usingRAM;
    public double restRAM;
    public double totalRam;
    public double usoRam;
   // Logs scriptLog = new Logs();
    public static void print(GlobalMemory memory) throws IOException {
        JOptionPane.showMessageDialog(
                null,
                "Memory: " +
                        FormatUtil.formatBytes(memory.getAvailable()) +
                        "/" +
                        FormatUtil.formatBytes(memory.getTotal()),
                "Memória RAM" ,
                JOptionPane.INFORMATION_MESSAGE
        );

        Logger log = new Logger();

        log.tipoLog = "ram";

       // log.usageAlertLog();
    }

    //Retorna o tamanho total da RAM
    public Double totalRam(GlobalMemory memory){
        DecimalFormat df = new DecimalFormat("#.#");
        double totalRam = Double.parseDouble(FormatUtil.formatBytes(memory.getTotal()).replaceAll(",",".").replaceAll("[a-zA-Z]", ""));
        return Double.valueOf(df.format(totalRam).replaceAll(",",".").replaceAll("[a-zA-Z]", ""));
    }

    //Retorna o uso atual da RAM
    public double usoRam(GlobalMemory memory){
        DecimalFormat df = new DecimalFormat("#.#");
        double freeRam = Double.valueOf(FormatUtil.formatBytes(memory.getAvailable()).replaceAll(",",".").replaceAll("[a-zA-Z]", ""));
        double totalRam = Double.parseDouble(FormatUtil.formatBytes(memory.getTotal()).replaceAll(",",".").replaceAll("[a-zA-Z]", ""));
        double usingRam = totalRam - freeRam;

        return Double.valueOf(df.format(usingRam).replaceAll(",",".").replaceAll("[a-zA-Z]", ""));
    }

    //Retorna o tamanho restante de RAM
    public double avaibleRam(GlobalMemory memory){

        double avaibleRam = Double.valueOf(FormatUtil.formatBytes(memory.getAvailable()).replaceAll(",",".").replaceAll("[a-zA-Z]", ""));

        return avaibleRam;
    }

    //Retorna a porcentagem de RAM disponivel
    public double freeRamPercent(GlobalMemory memory){
        DecimalFormat df = new DecimalFormat("#.#");
        double freeRam = Double.valueOf(FormatUtil.formatBytes(memory.getAvailable()).replaceAll(",",".").replaceAll("[a-zA-Z]", ""));
        double totalRam = Double.parseDouble(FormatUtil.formatBytes(memory.getTotal()).replaceAll(",",".").replaceAll("[a-zA-Z]", ""));
        double usingRam = totalRam - freeRam;
        double freeRamPercent = (usingRam * 100) / totalRam;
        System.out.println(freeRamPercent);
        return  Double.valueOf(df.format(freeRamPercent).replaceAll(",",".").replaceAll("[a-zA-Z]", ""));
    }

//
//
//    public void alertRAMPassed() {
//        try {
//            scriptLog.escreverLog("ALERTA! VOCÊ  ESTA UTILIZANDO " + restRAM +
//                    "% E ULTRAPASSOU O LIMITE DE USO DE MEMÓRIA!");
//        } catch (IOException ex) {
//            java.util.logging.Logger.getLogger(Ram.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    public void alertRAMOK() {
        JOptionPane.showMessageDialog(null, "SEU USO DE MEMORIA ESTA OK!");
    }
}
