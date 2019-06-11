/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slack;


/**
 *
 * @author Gil
 */
public class Slack {

    public String user = System.getProperty("user.name");

    public void sendMessage(String componente, String os, String path) {


        if (os.substring(0, 9).equals("Microsoft"))
        {
            path = "C:\\Users\\"+user+"\\HawkEye-Monitoring\\"+componente.toLowerCase() +"\\";
        }
        else if(os.substring(0 , 3).equals("GNU")){
            path = "/home/" + user + "/HawkEye-Monitoring/" + componente.toLowerCase() + "/";
        }
        else if(os.substring(0 , 5).equals("Apple")){
            path = "/Users/" + user + "/HawkEye-Monitoring/" + componente.toLowerCase() + "/";
        }
        SlackMessage slackMessage = SlackMessage.builder()
        .channel("outros-assuntos")
        .username("")
        .text("AVISO NIVEL DE USO DE  "  + componente + " ACIMA DO CONFIGURADO. VERIFIQUE O LOG EM: " + path)
        .icon_emoji(":twice:")
        .build();
      SlackUtils.sendMessage(slackMessage);
    }
}
