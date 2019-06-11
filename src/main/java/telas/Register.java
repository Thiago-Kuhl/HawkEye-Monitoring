package telas;

import java.util.Scanner;

public class Register {

    public void registerMachine(){
        String machineName;
        String localizacao;
        double alertCPU;
        double alertRAM;
        double alertDisk;
        boolean run = true;
        String answer = "";

        Scanner reader = new Scanner(System.in);
        System.out.println("================");
        System.out.println("CADASTRO MÁQUINA");
        System.out.println("================");

        do{
            System.out.print("NOME MÁQUINA: ");
            machineName = reader.next();
            System.out.print("LOCALIZAÇÃO: ");
            localizacao = reader.next();
            System.out.print("ALERTA CPU: ");
            alertCPU = reader.nextDouble();
            System.out.print("ALERTA RAM: ");
            alertRAM = reader.nextDouble();
            System.out.print("ALERTA DISCO: ");
            alertDisk = reader.nextDouble();

            System.out.println("=========");
            System.out.println("CONFIRMA?");
            System.out.println("=========");
            System.out.print("NOME MÁQUINA: ");
            System.out.print("LOCALIZAÇÃO: ");
            System.out.print("ALERTA CPU: ");
            System.out.print("ALERTA RAM: ");
            System.out.print("ALERTA DISCO: ");

        }while(!answer.equals("y")||!answer.equals("Y"));


    }
}
