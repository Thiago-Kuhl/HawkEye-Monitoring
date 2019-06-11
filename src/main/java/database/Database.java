package database;

import crypto.*;
import telas.*;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import telas.*;

public class Database {

    private String connectionUrl = "jdbc:sqlserver://hawkeyedb.database.windows.net:1433;" + "database=HawkEye;" + "user=HawkEye-Admin;" + "password=@hawk123;" + "encrypt=true;" + "trustServerCertificate=false;" + "LoginUsuarioTimeout=30;";
    Encrypt crypto = new Encrypt();
    private DateTimeFormatter dt = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    private DateTimeFormatter tm = DateTimeFormatter.ofPattern("HH:mm:ss");
    private LocalDateTime now = LocalDateTime.now();
    private String[] dataLeitura = getDt().format(getNow()).split(" ");
    private String SQL;


    public int selectCompanyId(String user){
        int companyId = 0;

        try (Connection con = DriverManager.getConnection(getConnectionUrl()); Statement stmt = con.createStatement();){
            SQL = "SELECT idEmpresa FROM empresa WHERE usuario = '"+user+"'";
            ResultSet rs = stmt.executeQuery(SQL);

            if(rs.next()){
                companyId = rs.getInt("idEmpresa");
                System.out.println(companyId);
                selectMachineExists(companyId, System.getProperty("user.name"));
                return companyId;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return companyId;
    }

    public boolean selectMachineExists(int companyId, String singleId){
        boolean exists = false;
        try (Connection con = DriverManager.getConnection(getConnectionUrl()); Statement stmt = con.createStatement()){
            SQL = "SELECT idMaquina FROM MAQUINA WHERE idEmpresa = '"+companyId+"' AND singleId = '"+crypto.singleID(singleId)+"'";
            ResultSet rs = stmt.executeQuery(SQL);

            if(rs.next()){
                System.out.println("Existe essa máquina");
                selectMachineId(companyId, System.getProperty("user.name"));
            }
            else{
                System.out.println("Não existe");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return exists;
    }

    public int selectMachineId(int companyId, String singleId){
        int machineId = 0;
        try (Connection con = DriverManager.getConnection(getConnectionUrl()); Statement stmt = con.createStatement()){
            SQL = "SELECT idMaquina, alertaCPU, alertaRAM, alertaDisco FROM maquina WHERE idEmpresa = '"+companyId+"'" +
                    "AND singleId = '"+crypto.singleID(singleId)+"'";

            ResultSet rs = stmt.executeQuery(SQL);
            while(rs.next()){
                Monitoring monitoring = new Monitoring();
                monitoring.setMachineId(rs.getInt("idMaquina"));
                monitoring.setAlertCPU(rs.getDouble("alertaCPU"));
                monitoring.setAlertRAM(rs.getDouble("alertaRAM"));
                monitoring.setAlertDisk(rs.getDouble("alertaDisco"));
                monitoring.update();
                //System.out.println(rs.getString("idMaquina"));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return machineId;
    }

    public void insertComponent(double totalDisk, double useDisk, double freeDisk, String cpuName, double useCpu, int cpuProcesses, double totalRam, double useRam, double freeRam, int machineId){
        String insertSql = "INSERT INTO componente(totalDisco, usoDisco, disponivelDisco, modeloCpu, " +
                "usoCpu, processosCpu, totalRam, usoRam, disponivelRam, idMaquina, dataLeitura, horaLeitura) VALUES "
                + "('" + totalDisk +"' , '" + useDisk +"', '" + freeDisk +
                "', '" + cpuName + "', '" + useCpu + "'" +
                ", '" + cpuProcesses + "', '" + totalRam + "'" +
                ", '" + useRam + "', '" + freeRam + "'" +
                ", '" + machineId +"', '" + dataLeitura[0].replaceAll("/", "-") +"', '" + dataLeitura[1] +"');";

        ResultSet resultSet = null;

        try (Connection connection = DriverManager.getConnection(getConnectionUrl());
             PreparedStatement prepsInsertProduct = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {

            prepsInsertProduct.execute();
            // Retrieve the generated key from the insert.
            resultSet = prepsInsertProduct.getGeneratedKeys();

            // Print the ID of the inserted row.
            while (resultSet.next()) {
                System.out.println("Generated: " + resultSet.getString(1));
            }
        }
        // Handle any errors that may have occurred.
        catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public boolean selectMaquina(String idEmpresa, String singleId){
//        boolean maquinaExiste = false;
//        try (Connection con = DriverManager.getConnection(getConnectionUrl()); Statement stmt = con.createStatement();) {
//            String SQL = "SELECT * FROM maquina WHERE idEmpresa = '"+ idEmpresa +"' AND singleId = '"+ crypto.singleID(singleId) +"';";
//            System.out.println(SQL);
//            ResultSet rs = stmt.executeQuery(SQL);
//
//            if(!rs.next()){
//                System.out.println("#####NÃO-EXISTE#####");
//                idEmpresa = rs.getString("idMaquina");
//                TelaCadastroMaquina cadastroMaquina = new TelaCadastroMaquina();
//                cadastroMaquina.setVisible(true);
//                cadastroMaquina.setIdEmpresa(idEmpresa);
//                return maquinaExiste;
//            }
//            else{
//                System.out.println("#####EXISTE#####");
//                System.out.println("IdEmpresa: " + rs.getString("idEmpresa") + "\n SingleId: " + rs.getString("singleId") + "\n IDMaquina: " +
//                        rs.getString("IdMaquina"));
//                maquinaExiste = true;
//                selectMaquina1(idEmpresa, System.getProperty("user.name"));
//                return maquinaExiste;
//            }
//
//        }
//        // Handle any errors that may have occurred.
//        catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return maquinaExiste;
//    }
//    public void insertMaquina(String idEmpresa, String singleId) {
//        String insertSql = "INSERT INTO maquina(sistemaOperacional, nomeMaquina, setor, idEmpresa, alertaCPU, alertaRAM, alertaDisco, singleId) VALUES "
//                + "('" + getSistemaOperacional() +"' , '" + getNomeMaquina() +"', '" + getSetor() + "', '" + idEmpresa +
//                "', '" + getAlertaCpu() + "', '" + getAlertaRam() + "', '" + getAlertaDisco() + "', '" + crypto.singleID(singleId) + "');";
//
//        ResultSet resultSet = null;
//
//        try (Connection connection = DriverManager.getConnection(getConnectionUrl());
//             PreparedStatement prepsInsertProduct = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);) {
//
//            prepsInsertProduct.execute();
//            // Retrieve the generated key from the insert.
//            resultSet = prepsInsertProduct.getGeneratedKeys();
//
//            // Print the ID of the inserted row.
//            while (resultSet.next()) {
//                System.out.println("Generated: " + resultSet.getString(1));
//            }
//            System.out.println("Insert Realizado com sucesso!");
//        }
//        // Handle any errors that may have occurred.
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//    }






    public DateTimeFormatter getDt() {
        return dt;
    }

    public void setDt(DateTimeFormatter dt) {
        this.dt = dt;
    }

    public DateTimeFormatter getTm() {
        return tm;
    }

    public void setTm(DateTimeFormatter tm) {
        this.tm = tm;
    }

    public LocalDateTime getNow() {
        return now;
    }

    public void setNow(LocalDateTime now) {
        this.now = now;
    }

    public String getConnectionUrl() {
        return connectionUrl;
    }

    public void setConnectionUrl(String connectionUrl) {
        this.connectionUrl = connectionUrl;
    }
}
