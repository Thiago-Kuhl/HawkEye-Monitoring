package crypto;

import java.util.Base64;

public class Encrypt{
/*
Essa classe realiza o Encrypt do usuário logado na maquina e com isso gera o 'SingleID' que possibilita que uma empresa
tenha vários 'servidores' diferentes
*/

    public String singleID(String user) {
        System.out.println("Texto original: " + user);

        //Recebe o user.name do usuário e encrypta ele
        user = Base64.getEncoder().encodeToString(user.getBytes());

        System.out.println("Texto em Base64: " + user);
        return user;
    }
}
