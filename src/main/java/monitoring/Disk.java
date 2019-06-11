package monitoring;

import oshi.util.FormatUtil;
import java.io.File;
import java.text.DecimalFormat;

public class Disk {

    //Retorna o tamanho total do Disk
    public double totalDisco(double totalDisco) {
        DecimalFormat df = new DecimalFormat("#.#");
        File[] roots = File.listRoots();
        for (File root : roots) {

            totalDisco = Double.valueOf(FormatUtil.formatBytes(root.getTotalSpace()).replaceAll("[a-zA-Z]", "").replaceAll(",","."));

        }

        return Double.valueOf(df.format(totalDisco).replaceAll(",",".").replaceAll("[a-zA-Z]", ""));
    }

    //Retorna o uso atual do Disk
    public double usoDisco(double usoDisco) {
        DecimalFormat df = new DecimalFormat("#.#");
        File[] roots = File.listRoots();
        for (File root : roots) {

            double totalDisco = Double.valueOf(FormatUtil.formatBytes(root.getUsableSpace()).replaceAll("[a-zA-Z]", "").replaceAll(",","."));
            double disponivelDisco = Double.valueOf(FormatUtil.formatBytes(root.getUsableSpace()).replaceAll("[a-zA-Z]", "").replaceAll(",","."));

            usoDisco = totalDisco(totalDisco) - disponivelDisco(disponivelDisco);

        }

        return Double.valueOf(df.format(usoDisco).replaceAll(",",".").replaceAll("[a-zA-Z]", ""));
    }

    //Retorna o valor disponive do Disk
    public double disponivelDisco(double disponivelDisco) {
        DecimalFormat df = new DecimalFormat("#.#");
        File[] roots = File.listRoots();
        for (File root : roots) {

            disponivelDisco = Double.valueOf(FormatUtil.formatBytes(root.getFreeSpace()).replaceAll("[a-zA-Z]", "").replaceAll(",","."));

        }

        return Double.valueOf(df.format(disponivelDisco).replaceAll(",",".").replaceAll("[a-zA-Z]", ""));
    }

    //Retorna a porcentagem livre do Disk
    public double freeDiskPercent(double freeDiskPercent){
        DecimalFormat df = new DecimalFormat("#.#");
        File[] roots = File.listRoots();
        for (File root : roots){
            double totalDisk = Double.parseDouble(FormatUtil.formatBytes(root.getTotalSpace()).replaceAll("[a-zA-Z]", "").replaceAll(",","."));
            double freeDisk = Double.parseDouble(FormatUtil.formatBytes(root.getFreeSpace()).replaceAll("[a-zA-Z]", "").replaceAll(",","."));
            double usedDisk = totalDisk - freeDisk;
            freeDiskPercent = Double.valueOf(df.format((usedDisk * 100) / totalDisk).replaceAll(",",".").replaceAll("[a-zA-Z]", ""));
        }
        return  freeDiskPercent;
    }

}