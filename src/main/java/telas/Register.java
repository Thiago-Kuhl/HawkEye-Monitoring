package telas;

import database.Database;

import java.util.Scanner;

public class Register {
    private int companyId = 0;

    public void registerMachine(){
        String machineName;
        String location;
        int alertCPU;
        int alertRAM;
        int alertDisk;
        boolean run = true;
        String answer;
        Database db = new Database();
        Scanner reader = new Scanner(System.in);
        System.out.println("================");
        System.out.println("CADASTRO MÁQUINA");
        System.out.println("================");

        while(run){
            System.out.print("NOME MÁQUINA: ");
            machineName = reader.next();
            System.out.print("LOCALIZAÇÃO: ");
            location = reader.next();
            System.out.print("ALERTA CPU: ");
            alertCPU = reader.nextInt();
            System.out.print("ALERTA RAM: ");
            alertRAM = reader.nextInt();
            System.out.print("ALERTA DISCO: ");
            alertDisk = reader.nextInt();

            System.out.println("=========");
            System.out.println("CONFIRMA?");
            System.out.println("=========");

            System.out.print("NOME MÁQUINA: ");
            System.out.print("LOCALIZAÇÃO: ");
            System.out.print("ALERTA CPU: ");
            System.out.print("ALERTA RAM: ");
            System.out.print("ALERTA DISCO: ");

            System.out.println("As informações estão corretas? [Y/N]");
            answer = reader.next();

            if(answer.equals("y")|| answer.equals("Y")) {
                db.insertMachine(getCompanyId(), machineName, location, alertCPU,
                alertRAM, alertDisk, System.getProperty("user.name"));
                Monitoring monitoring = new Monitoring();
                monitoring.update();
                run = false;
            }

        }


    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }
}
