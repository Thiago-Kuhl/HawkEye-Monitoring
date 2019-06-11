package monitoring;

import oshi.hardware.CentralProcessor;
import oshi.util.FormatUtil;

import javax.swing.*;
import java.text.DecimalFormat;


public class Cpu {

    //Retorna o modelo da CPU
    public String modeloCpu(CentralProcessor processor) {
        String modeloCpu = processor.getName();
        return modeloCpu;
    }

    //Retorna o uso da CPU
    public double usedCpuPercent(CentralProcessor processor){
        DecimalFormat df = new DecimalFormat("#.#");
        return Double.valueOf(df.format(processor.getSystemCpuLoadBetweenTicks() * 100).replaceAll(",",".").replaceAll("[a-zA-Z]", ""));
    }

    //Retorna a frequencia que a CPU trabalha
    public String cpuFrequency(CentralProcessor processor){


        String cpuFrequency = FormatUtil.formatHertz(processor.getVendorFreq());

        return cpuFrequency;
    }

}
