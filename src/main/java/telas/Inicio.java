package telas;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

public class Inicio {

    public static void main(String[] args) throws URISyntaxException, IOException {
        Scanner reader = new Scanner(System.in);
        boolean run = true;

        Login login = new Login();

        System.out.println("================================");
        System.out.println("       HAWKEYE MONITORING       ");
        System.out.println("================================");

        while(run) {
            System.out.println("Digite a opção desejada:\n" +
                    "[1]Já possuo cadastro [2]Não possuo cadastro");
            int answer = reader.nextInt();

            switch (answer) {
                case 1:
                    login.validateLogin();
                    run = false;
                    break;
                case 2:
                    java.awt.Desktop.getDesktop().browse(new java.net.URI("http://hawkeyetechnology.azurewebsites.net/signup"));
                    login.validateLogin();
                    run = false;
                    break;
                default:
                    break;
            }
        }




    }

}
