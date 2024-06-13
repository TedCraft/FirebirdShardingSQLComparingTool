import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Scanner;
import java.sql.Connection;
import org.firebirdsql.management.FBManager;
import org.apache.commons.io.FileUtils;
import java.io.File;

public class Connections {
    public static String DB_URL;
    public static String DB_Driver;
    public static String DB_User;
    public static String DB_Pass;
    public static String DB_Path;
    public static String DB_Host;
    public static String DB_Port;
    public static String DB_Name;
    public static int dbconnection(int db){
        //
        Tester tester = new Tester();
        Scanner in = new Scanner(System.in);
        String r;
//        //
//        //Работа с Firebird
//        if (db == 1){
//            System.out.println("Вы выбрали Firebird.");
//            DB_Driver = "org.firebirdsql.jdbc.FBDriver";
//            System.out.println("Создать новую базу данных? Y(Да)/N(Нет)");
//            r = in.nextLine();
//            switch (r){
//                case "Y":
//                    System.out.println("Полный путь с именем создаваемой базы данных:");
//                    DB_Path = in.nextLine();
//                    DB_Path = DB_Path.replace("\\","/");
//                    System.out.println(DB_Path);
//                    System.out.println("Сервер:");
//                    DB_Host = in.nextLine();
//                    System.out.println("Порт:");
//                    DB_Port = in.next();
//                    System.out.println("Пользователь:");
//                    DB_User = in.next();
//                    System.out.println("Пароль:");
//                    DB_Pass = in.next();
//                    FBManager man = new FBManager();
//                    try{
//                        man.start();
//                        man.createDatabase(DB_Path,DB_User,DB_Pass);
//                        man.stop();
//                        System.out.println("База данных успешно создана.");
//                    }
//                    catch (Exception e){
//                        System.out.println(e);
//                    }
//                    break;
//                case "N":
//                    System.out.println("Полный путь с именем ранее созданной базы данных:");
//                    DB_Path = in.nextLine();
//                    DB_Path = DB_Path.replace("\\","/");
//                    System.out.println(DB_Path);
//                    System.out.println("Сервер:");
//                    DB_Host = in.nextLine();
//                    System.out.println("Порт:");
//                    DB_Port = in.next();
//                    System.out.println("Пользователь:");
//                    DB_User = in.next();
//                    System.out.println("Пароль:");
//                    DB_Pass = in.next();
//                    break;
//            }
//            DB_URL = "jdbc:firebirdsql://"+DB_Host+":"+DB_Port+"/"+DB_Path+"?encoding=utf8;useUnicode=true&amp;characterEncoding=utf8";
//            System.out.println(DB_URL);//Для отладки
//            try{
//                Class.forName(DB_Driver);
//                Connection con = DriverManager.getConnection(DB_URL,DB_User,DB_Pass);
//                con.setAutoCommit(false);
//                System.out.println("Соединение с СУБД выполнено.");
//                tester.testcheck("FireBird",con);
//                con.close();
//                System.out.println("Отключение от СУБД выполнено.");
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//                System.out.println("Ошибка SQL!");
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//                System.out.println("JDBC драйвер для субд не найден!");
//            }
//        }
//        //Работа с Red DataBase
//        if (db == 2){
//            System.out.println("Вы выбрали Red DataBase.");
//            DB_Driver = "org.firebirdsql.jdbc.FBDriver";
//            System.out.println("Создать новую базу данных? Y(Да)/N(Нет)");
//            r = in.nextLine();
//            switch (r){
//                case "Y":
//                    System.out.println("Полный путь с именем создаваемой базы данных:");
//                    DB_Path = in.nextLine();
//                    DB_Path = DB_Path.replace("\\","/");
//                    System.out.println(DB_Path);
//                    System.out.println("Сервер:");
//                    DB_Host = in.nextLine();
//                    System.out.println("Порт:");
//                    DB_Port = in.next();
//                    System.out.println("Пользователь:");
//                    DB_User = in.next();
//                    System.out.println("Пароль:");
//                    DB_Pass = in.next();
//                    FBManager man = new FBManager();
//                    try{
//                        man.start();
//                        man.createDatabase(DB_Path,DB_User,DB_Pass);
//                        man.stop();
//                        System.out.println("База данных успешно создана.");
//                    }
//                    catch (Exception e){
//                        System.out.println(e);
//                    }
//                    break;
//                case "N":
//                    System.out.println("Полный путь с именем ранее созданной базы данных:");
//                    DB_Path = in.nextLine();
//                    DB_Path = DB_Path.replace("\\","/");
//                    System.out.println(DB_Path);
//                    System.out.println("Сервер:");
//                    DB_Host = in.nextLine();
//                    System.out.println("Порт:");
//                    DB_Port = in.next();
//                    System.out.println("Пользователь:");
//                    DB_User = in.next();
//                    System.out.println("Пароль:");
//                    DB_Pass = in.next();
//                    break;
//            }
//            DB_URL = "jdbc:firebirdsql://"+DB_Host+":"+DB_Port+"/"+DB_Path+"?encoding=utf8;useUnicode=true&amp;characterEncoding=utf8";
//            System.out.println(DB_URL);//Для отладки
//            try{
//                Class.forName(DB_Driver);
//                Connection con = DriverManager.getConnection(DB_URL,DB_User,DB_Pass);
//                con.setAutoCommit(false);
//                System.out.println("Соединение с СУБД выполнено.");
//                tester.testcheck("Ред База Данных",con);
//                con.close();
//                System.out.println("Отключение от СУБД выполнено.");
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//                System.out.println("Ошибка SQL!");
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//                System.out.println("JDBC драйвер для субд не найден!");
//            }
//        }
//        //Работа с Oracle
//        if (db == 3){
//            System.out.println("Вы выбрали Oracle.");
//            System.out.println("Внимание! Перед выполнение дальнейших действий убедитесь что" +
//                    "база данных создана стандартными средствами Oracle.");
//            DB_Driver = "oracle.jdbc.driver.OracleDriver";
//            System.out.println("Имя базы данных:");
//            DB_Name = in.next();
//            System.out.println("Сервер:");
//            DB_Host = in.next();
//            System.out.println("Порт:");
//            DB_Port = in.next();
//            DB_User = "SYSTEM";
//            System.out.println("Пароль от схемы SYSTEM:");
//            DB_Pass = in.next();
//            DB_URL = "jdbc:oracle:thin:@"+DB_Host+":"+DB_Port+"/"+DB_Name;
//            System.out.println(DB_URL);
//            try{
//                Class.forName(DB_Driver);
//                Connection con = DriverManager.getConnection(DB_URL, DB_User, DB_Pass);
//                System.out.println("Соединение с СУБД выполнено.");
//                System.out.println("Создание схемы");
//                System.out.println("Имя создаваемой схемы: SQLTESTS");
//                DB_User = "SQLTESTS";
//                System.out.println("Введите пароль схемы:");
//                DB_Pass = in.next();
//                Statement stmt = con.createStatement();
//                //Создание схемы в базе данных
//                String sql = "CREATE USER \""+DB_User+"\" IDENTIFIED BY \""+DB_Pass+"\"";
//                stmt.executeUpdate(sql);
//                sql = "GRANT \"RESOURCE\" TO \""+DB_User+"\"";
//                stmt.executeUpdate(sql);
//                sql = "GRANT \"DBA\" TO \""+DB_User+"\"";
//                stmt.executeUpdate(sql);
//                sql = "GRANT \"CONNECT\" TO \""+DB_User+"\"";
//                stmt.executeUpdate(sql);
//                sql = "GRANT \"EXP_FULL_DATABASE\" TO \""+DB_User+"\"";
//                stmt.executeUpdate(sql);
//                sql = "GRANT \"IMP_FULL_DATABASE\" TO \""+DB_User+"\"";
//                stmt.executeUpdate(sql);
//                System.out.println("Создание схемы выполнено!");
//                con.close();
//                //
//                // Подключение к созданной схеме
//                DB_URL = "jdbc:oracle:thin:@"+DB_Host+":"+DB_Port+"/"+DB_Name;
//                con = DriverManager.getConnection(DB_URL, DB_User, DB_Pass);
//                con.setAutoCommit(false);
//                System.out.println("Соединение с СУБД выполнено.");
//                tester.testcheck("Oracle",con);
//                con.close();
//                System.out.println("Отключение от СУБД выполнено");
//                //
//            }catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//        }
//        //
//        //Работа с H2
//        if (db == 4){
//            System.out.println("Вы выбрали H2.");
//            DB_Driver = "org.h2.Driver";
//            System.out.println("Полный путь создания базы данных:");
//            DB_Path = in.nextLine();
//            DB_Path = DB_Path.replace("\\","/");
//            System.out.println("Имя базы данных:");
//            DB_Name = in.next();
//            DB_URL = "jdbc:h2:"+DB_Path+"/"+DB_Name;
//            System.out.println(DB_URL);
//            try {
//                Class.forName(DB_Driver);
//                Connection con = DriverManager.getConnection(DB_URL);
//                con.setAutoCommit(false);
//                System.out.println("Соединение с СУБД выполнено.");
//                tester.testcheck("H2",con);
//                con.close();
//                System.out.println("Отключение от СУБД выполнено");
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//        }
//        //
//        //Работа с Postgre
//        if (db == 5){
//            System.out.println("Вы выбрали PostgreSQL.");
//            DB_Driver = "org.postgresql.Driver";
//            System.out.println("Создать новую базу данных? Y(Да)/N(Нет)");
//            r = in.nextLine();
//            switch (r){
//                case "Y":
//                    System.out.println("Сервер:");
//                    DB_Host = in.next();
//                    System.out.println("Порт (по умолчанию: 5432):");
//                    DB_Port = in.next();
//                    System.out.println("Имя базы данных:");
//                    DB_Name = in.next().toLowerCase();
//                    System.out.println("Пользователь:");
//                    DB_User = in.next();
//                    System.out.println("Пароль:");
//                    DB_Pass = in.next();
//                    DB_URL = "jdbc:postgresql://"+DB_Host+"/";
//                    System.out.println(DB_URL);
//                    try{
//                        Class.forName(DB_Driver);
//                        Connection con = DriverManager.getConnection(DB_URL, DB_User, DB_Pass);
//                        Statement stmt = con.createStatement();
//                        String sql = "CREATE DATABASE "+DB_Name;
//                        System.out.println(sql);
//                        stmt.executeUpdate(sql);
//                        System.out.println("База данных успешно создана.");
//                        con.close();
//
//                    } catch (SQLException e) {
//                        throw new RuntimeException(e);
//                    } catch (ClassNotFoundException e) {
//                        throw new RuntimeException(e);
//                    }
//                    break;
//                case "N":
//                    System.out.println("Имя базы данных:");
//                    DB_Name = in.next();
//                    System.out.println("Сервер:");
//                    DB_Host = in.next();
//                    System.out.println("Порт (по умолчанию:5432):");
//                    DB_Port = in.next();
//                    System.out.println("Пользователь:");
//                    DB_User = in.next();
//                    System.out.println("Пароль:");
//                    DB_Pass = in.next();
//                    break;
//            }
//            DB_URL = "jdbc:postgresql://"+DB_Host+":"+DB_Port+"/"+DB_Name+"?encoding=utf8;useUnicode=true&amp;characterEncoding=utf8";
//            System.out.println(DB_URL);
//            try {
//                Class.forName(DB_Driver);
//                Connection con = DriverManager.getConnection(DB_URL, DB_User, DB_Pass);
//                con.setAutoCommit(false);
//                System.out.println("Соединение с СУБД выполнено.");
//                tester.testcheck("PostgreSQL",con);
//                con.close();
//                System.out.println("Отключение от СУБД выполнено");
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//        }
//        //
//        //Работа с MySQL
//        if (db == 6){
//            System.out.println("Вы выбрали MySQL.");
//            DB_Driver = "com.mysql.cj.jdbc.Driver";
//            System.out.println("Создать новую базу данных? Y(Да)/N(Нет)");
//            r = in.nextLine();
//            switch (r) {
//                case "Y":
//                    System.out.println("Сервер:");
//                    DB_Host = in.nextLine();
//                    System.out.println("Порт:");
//                    DB_Port = in.next();
//                    System.out.println("Имя базы данных:");
//                    DB_Name = in.next();
//                    System.out.println("Пользователь:");
//                    DB_User = in.next();
//                    System.out.println("Пароль:");
//                    DB_Pass = in.next();
//                    DB_URL = "jdbc:mysql://"+DB_Host+"/";
//                    System.out.println(DB_URL);
//                    try {
//                        Class.forName(DB_Driver);
//                        Connection con = DriverManager.getConnection(DB_URL, DB_User, DB_Pass);
//                        Statement stmt = con.createStatement();
//                        String sql = "CREATE DATABASE "+DB_Name;
//                        System.out.println(sql);
//                        stmt.executeUpdate(sql);
//                        System.out.println("База данных успешно создана.");
//                        con.close();
//                        DB_URL = "jdbc:mysql://"+DB_Host+":"+DB_Port+"/"+DB_Name;
//                        System.out.println(DB_URL);
//                    } catch (Exception e) {
//                        System.out.println(e);
//                    }
//                    break;
//                case "N":
//                    System.out.println("Сервер:");
//                    DB_Host = in.nextLine();
//                    System.out.println("Порт:");
//                    DB_Port = in.next();
//                    System.out.println("Имя базы данных:");
//                    DB_Name = in.next();
//                    System.out.println("Пользователь:");
//                    DB_User = in.next();
//                    System.out.println("Пароль:");
//                    DB_Pass = in.next();
//                    DB_URL = "jdbc:mysql://"+DB_Host+":"+DB_Port+"/"+DB_Name;
//                    System.out.println(DB_URL);
//                    break;
//            }
//            try {
//                Class.forName(DB_Driver);
//                Connection con = DriverManager.getConnection(DB_URL, DB_User, DB_Pass);
//                System.out.println("Соединение с СУБД выполнено.");
//                tester.testcheck("MySQL",con);
//                con.close();
//                System.out.println("Отключение от СУБД выполнено");
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//        }
//        //Работа с ShardingSphere
//        if (db == 7){
//            System.out.println("Вы выбрали Red DataBase.");
//            DB_Driver = "org.apache.shardingsphere.driver.ShardingSphereDriver";
//            DB_URL = "jdbc:shardingsphere:classpath:config.yaml";
//            System.out.println(DB_URL);//Для отладки
//            try{
//                Class.forName(DB_Driver);
//                Connection con = DriverManager.getConnection(DB_URL);
//                con.setAutoCommit(false);
//                System.out.println("Соединение с СУБД выполнено.");
//                tester.testcheck("ShardingSphere",con);
//                con.close();
//                System.out.println("Отключение от СУБД выполнено.");
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//                System.out.println("Ошибка SQL!");
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//                System.out.println("JDBC драйвер для субд не найден!");
//            }
//        }
//        //
//        //

        try {
            //Sharding
            DB_Driver = "org.apache.shardingsphere.driver.ShardingSphereDriver";
            DB_URL = "jdbc:shardingsphere:classpath:config.yaml";

            File emptyDB = new File("src/main/resources/database/empty.FDB");
            File shardDB = new File("src/main/resources/database/shard.FDB");
            FileUtils.copyFile(emptyDB, shardDB);

            Class.forName(DB_Driver);
            Configuration config = new Configuration();
            Connection con = config.createDataSource().getConnection();
            con.setAutoCommit(false);
            System.out.println("Соединение с СУБД выполнено.");
            LinkedHashMap<String, ArrayList<String>> resultsSharding = tester.testcheck("ShardingSphere", con);
            con.close();
            System.out.println("Отключение от СУБД выполнено.");

            //JDBC
            DB_Driver = "org.firebirdsql.jdbc.FBDriver";
            DB_URL = String.format("jdbc:firebirdsql://localhost:3050/%s/src/main/resources/database/jdbc.FDB?characterEncoding=utf-8", System.getProperty("user.dir"));

            File jdbcDB = new File("src/main/resources/database/jdbc.FDB");
            FileUtils.copyFile(emptyDB, jdbcDB);

            Class.forName(DB_Driver);
            con = DriverManager.getConnection(DB_URL, config.USER_NAME, config.PASSWORD);
            con.setAutoCommit(false);
            System.out.println("Соединение с СУБД выполнено.");
            LinkedHashMap<String, ArrayList<String>> resultsJDBC = tester.testcheck("Red Database", con);
            con.close();
            System.out.println("Отключение от СУБД выполнено.");

            ArrayList<String> results = new ArrayList<>();
            for (String key : resultsJDBC.keySet()) {
                ArrayList<String> jdbcRes = resultsJDBC.get(key);
                ArrayList<String> shardRes = resultsSharding.get(key);

                boolean keyInserted = false;
                for (int i = 0; i < jdbcRes.size(); i++) {
                    if (jdbcRes.get(i) != shardRes.get(i) &&
                            jdbcRes.get(i) == "<td colspan = 3 align = right><b>OK</td><td width = 50%> no error</td></tr>\n") {
                        if (!keyInserted) {
                            results.add(key);
                            keyInserted = true;
                        }
                        results.add(shardRes.get(i-1));
                        results.add(shardRes.get(i));
                    }
                }
            }

            Html htmlgen = new Html();
            htmlgen.Genhtml(results, "of comparing");

            //Comparing
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Ошибка SQL!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("JDBC драйвер для субд не найден!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //
        //
        return db;
    }
}
