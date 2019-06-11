package monitoring;

import oshi.hardware.CentralProcessor;
import oshi.util.FormatUtil;

import javax.swing.*;
import java.text.DecimalFormat;


public class Cpu {

    public double maxCPU;
    public double alertLevel;
    public double usingCPU;
    public double restCPU;
    public String modeloCPU;
    //Logs scriptLog = new Logs();

    public static void print(CentralProcessor processor) {

        JOptionPane.showMessageDialog(
                null,
                processor +
                        "" + processor.getPhysicalPackageCount() + " physical CPU package(s) \n" +
                        processor.getPhysicalProcessorCount() + " physical CPU core(s) \n" +
                        processor.getLogicalProcessorCount() + " logical CPU(s) \n" +
                        "Identifier: " + processor.getIdentifier() + "\n" +
                        "ProcessorID: " + processor.getProcessorID(),
                "Processador",
                JOptionPane.INFORMATION_MESSAGE
        );
        //System.out.println(100L * processor.getSystemCpuLoadTicks());
    }

    //Retorna o modelo da CPU
    public String modeloCpu(CentralProcessor processor) {
        String modeloCpu = processor.getName();
        System.out.println(modeloCpu);
        return modeloCpu;
    }

    //Retorna o uso da CPU
    public double usedCpuPercent(CentralProcessor processor){
        DecimalFormat df = new DecimalFormat("#.#");
        return Double.valueOf(df.format(processor.getSystemCpuLoadBetweenTicks() * 100).replaceAll(",",".").replaceAll("[a-zA-Z]", ""));
    }

    //Retorna a frequencia que a CPU trabalha
    public String cpuFrequency(CentralProcessor processor){


        System.out.println(FormatUtil.formatHertz(processor.getVendorFreq()));
        String cpuFrequency = FormatUtil.formatHertz(processor.getVendorFreq());

        return cpuFrequency;
    }

//    //Alertas
//    public void alertCPUPassed() {
//        try {
//            //scriptLog.escreverLog("ALERTA! VOCE ESTA UTILIZANDO " + restCPU +"% E ULTRAPASSOU O LIMITE DE USO DA CPU!");
//        } catch (IOException ex) {
//
//        }
//    }
//
//    public void alertCPUOK() {
//        try {
//            //scriptLog.escreverLog ("SEU USO DE CPU ESTAO OK!");
//        } catch (IOException ex) {
//
//        }
//    }

}
