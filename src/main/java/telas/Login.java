package telas;

import database.Database;
import httpRequest.PostHttpRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.io.Console;

public class Login {

    public void validateLogin() {
        Scanner reader = new Scanner(System.in);
        String user;
        String password;
        String secretPassword;
        Database db = new Database();
        InputStreamReader streamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(streamReader);


        System.out.println("=================================");
        System.out.println("              Login              ");
        System.out.println("=================================");



        try {
            System.out.print("Usuário: ");
            user = reader.next();
            System.out.print("Senha: ");
            //password = reader.next();
            //SERÁ USADO NO FINAL PARA REALIZAR O MASCARA DE SENHA NO TERMINAL, ELE QUEBRA O CÓDIGO SE TENTAR UTILIZAR DIRETO NA IDE.
            secretPassword = reader.nextLine();
            password = String.valueOf(secretPassword);

            String url = "http://hawkeyetechnology.azurewebsites.net/loginjava";
            String jsonInputString = "{\"usuario\": \"" + user +"\", \"senha\": \"" +
                    password + "\"}";
            System.out.println(jsonInputString);

            int respondeCode = new PostHttpRequest().postHttpRequest(jsonInputString, url);

            if(respondeCode == 200){
                System.out.println("Loguei");
                db.selectCompanyId(user);
                System.out.println("dadasdadaaasd");

            }
        }catch (Exception e){
            System.out.println(e);
        }

    }



}

