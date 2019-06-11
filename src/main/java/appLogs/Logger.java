package appLogs;

import menu.Init;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class Logger {
    
    Date date = new Date();
    public String tipoLog;
    public String user = System.getProperty("user.name");
    public String os = String.valueOf(Init.os);

    public void startLog() throws IOException {

        if (os.substring(0, 9).equals("Microsoft"))
        {
            tipoLog = "general";
            FileWriter arq = new FileWriter("C:\\Users\\"+user+"\\HawkEye-\\"+tipoLog+"\\"+tipoLog+"--START--"+date.toString().replaceAll(" " , "-").replaceAll(":" , "-")+".txt");
            PrintWriter gravarArq = new PrintWriter(arq);

            gravarArq.printf(date + " -- A APLICAÇÃO HAWKEYE MONITORING FOI INICIADA PELO USUÁRIO: " + user + "\n") ;
            arq.close();
        }
         else if(os.substring(0 , 3).equals("GNU")){
            tipoLog = "general";
            FileWriter arq = new FileWriter("/home/" + user + "/HawkEye-Monitoring/" + tipoLog + "/START--"+date.toString().replaceAll(" " , "-").replaceAll(":" , "-")+".txt");;
            PrintWriter gravarArq = new PrintWriter(arq);

            gravarArq.printf(date + " -- A APLICAÇÃO HAWKEYE MONITORING FOI INICIADA PELO USUÁRIO: " + user + "\n") ;
            arq.close();
        }
        else if(os.substring(0 , 5).equals("Apple")){
            tipoLog = "general";
            FileWriter arq = new FileWriter("/Users/" + user + "/HawkEye-Monitoring/" + tipoLog + "/START--"+date.toString().replaceAll(" " , "-").replaceAll(":" , "-")+".txt");;
            PrintWriter gravarArq = new PrintWriter(arq);

            gravarArq.printf(date + " -- A APLICAÇÃO HAWKEYE MONITORING FOI INICIADA PELO USUÁRIO: " + user + "\n") ;
            arq.close();
        }
    }

    public void closeLog() throws IOException {

        if (os.substring(0, 9).equals("Microsoft"))
        {
            tipoLog = "general";
            FileWriter arq = new FileWriter("C:\\Users\\"+user+"\\HawkEye-Monitoring\\"+tipoLog+"\\"+tipoLog+"--CLOSE--"+date.toString().replaceAll(" " , "-").replaceAll(":" , "-")+".txt");
            PrintWriter gravarArq = new PrintWriter(arq);

            gravarArq.printf(date + " -- A APLICAÇÃO HAWKEYE MONITORING FOI FECHADA PELO USUÁRIO: " + user + "\n") ;
            arq.close();
        }
        else if(os.substring(0 , 3).equals("GNU")){
            tipoLog = "general";
            FileWriter arq = new FileWriter("/home/" + user + "/HawkEye-Monitoring/" + tipoLog + "/CLOSE--"+date.toString().replaceAll(" " , "-").replaceAll(":" , "-")+".txt");;
            PrintWriter gravarArq = new PrintWriter(arq);

            gravarArq.printf(date + " -- A APLICAÇÃO HAWKEYE MONITORING FOI FECHADA PELO USUÁRIO: " + user + "\n") ;

            arq.close();
        }
    }

    public void usageAlertLog(String tipoLog, String alertaDefinido, String usoAtual) throws IOException {
        if (os.substring(0, 9).equals("Microsoft"))
        {
            FileWriter arq = new FileWriter("C:\\Users\\"+user+"\\HawkEye-\\"+tipoLog+"\\" + "error-" + tipoLog + "-" +  date.toString().replaceAll(" " , "-").replaceAll(":" , "-")+".txt");
            PrintWriter gravarArq = new PrintWriter(arq);

            gravarArq.printf(String.valueOf(date) + " - ALERTA! VOCÊ ULTRAPASSOU O LIMITE DE USO DE " + tipoLog + ". ALERTA DEFINIDO: " + alertaDefinido +
                    " USO ATUAL: " + usoAtual);

            arq.close();

            System.out.printf("LOG GERADO COM SUCESSO!");
        }
        else if (os.substring(0 , 3).equals("GNU"))
        {
            FileWriter arq = new FileWriter("/home/" + user + "/HawkEye-Monitoring/" + tipoLog + "/error-" + tipoLog + "-" +  date.toString().replaceAll(" " , "-").replaceAll(":" , "-")+".txt");
            PrintWriter gravarArq = new PrintWriter(arq);

            gravarArq.printf(String.valueOf(date) + " - ALERTA! VOCÊ ULTRAPASSOU O LIMITE DE USO DE " + tipoLog + ". ALERTA DEFINIDO: " + alertaDefinido +
                    " USO ATUAL: " + usoAtual);

            arq.close();

            System.out.printf("LOG GERADO COM SUCESSO!");
        }
        else if (os.substring(0 , 5).equals("Apple"))
        {
            FileWriter arq = new FileWriter("/Users/" + user + "/HawkEye-Monitoring/" + tipoLog + "/error-" + tipoLog + "-" +  date.toString().replaceAll(" " , "-").replaceAll(":" , "-")+".txt");
            PrintWriter gravarArq = new PrintWriter(arq);

            gravarArq.printf(String.valueOf(date) + " - ALERTA! VOCÊ ULTRAPASSOU O LIMITE DE USO DE " + tipoLog + ". ALERTA DEFINIDO: " + alertaDefinido +
                    " USO ATUAL: " + usoAtual);

            arq.close();

            System.out.printf("LOG GERADO COM SUCESSO!");
        }
    }



}