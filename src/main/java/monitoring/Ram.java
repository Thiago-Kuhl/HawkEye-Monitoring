package monitoring;

import oshi.hardware.GlobalMemory;
import oshi.util.FormatUtil;
import java.text.DecimalFormat;


public class Ram {

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
        return  Double.valueOf(df.format(freeRamPercent).replaceAll(",",".").replaceAll("[a-zA-Z]", ""));
    }

}
