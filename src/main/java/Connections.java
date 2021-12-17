import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.Connection;
import org.firebirdsql.management.FBManager;
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
        //
        //Работа с Firebird
        if (db == 1){
            System.out.println("Вы выбрали Firebird.");
            DB_Driver = "org.firebirdsql.jdbc.FBDriver";
            System.out.println("Создать новую базу данных? Y(Да)/N(Нет)");
            r = in.nextLine();
            switch (r){
                case "Y":
                    System.out.println("Полный путь с именем создаваемой базы данных:");
                    DB_Path = in.nextLine();
                    DB_Path = DB_Path.replace("\\","/");
                    System.out.println(DB_Path);
                    System.out.println("Сервер:");
                    DB_Host = in.nextLine();
                    System.out.println("Порт:");
                    DB_Port = in.next();
                    System.out.println("Пользователь:");
                    DB_User = in.next();
                    System.out.println("Пароль:");
                    DB_Pass = in.next();
                    FBManager man = new FBManager();
                    try{
                        man.start();
                        man.createDatabase(DB_Path,DB_User,DB_Pass);
                        man.stop();
                        System.out.println("База данных успешно создана.");
                    }
                    catch (Exception e){
                        System.out.println(e);
                    }
                    break;
                case "N":
                    System.out.println("Полный путь с именем ранее созданной базы данных:");
                    DB_Path = in.nextLine();
                    DB_Path = DB_Path.replace("\\","/");
                    System.out.println(DB_Path);
                    System.out.println("Сервер:");
                    DB_Host = in.nextLine();
                    System.out.println("Порт:");
                    DB_Port = in.next();
                    System.out.println("Пользователь:");
                    DB_User = in.next();
                    System.out.println("Пароль:");
                    DB_Pass = in.next();
                    break;
            }
            DB_URL = "jdbc:firebirdsql://"+DB_Host+":"+DB_Port+"/"+DB_Path;
            System.out.println(DB_URL);//Для отладки
            try{
                Class.forName(DB_Driver);
                Connection con = DriverManager.getConnection(DB_URL,DB_User,DB_Pass);
                System.out.println("Соединение с СУБД выполнено.");
                tester.testcheck("FireBird",con);
                con.close();
                System.out.println("Отключение от СУБД выполнено.");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                System.out.println("Ошибка SQL!");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("JDBC драйвер для субд не найден!");
            }
        }
        //Работа с Red DataBase
        if (db == 2){
            System.out.println("Вы выбрали Red DataBase.");
            DB_Driver = "org.firebirdsql.jdbc.FBDriver";
            System.out.println("Создать новую базу данных? Y(Да)/N(Нет)");
            r = in.nextLine();
            switch (r){
                case "Y":
                    System.out.println("Полный путь с именем создаваемой базы данных:");
                    DB_Path = in.nextLine();
                    DB_Path = DB_Path.replace("\\","/");
                    System.out.println(DB_Path);
                    System.out.println("Сервер:");
                    DB_Host = in.nextLine();
                    System.out.println("Порт:");
                    DB_Port = in.next();
                    System.out.println("Пользователь:");
                    DB_User = in.next();
                    System.out.println("Пароль:");
                    DB_Pass = in.next();
                    FBManager man = new FBManager();
                    try{
                        man.start();
                        man.createDatabase(DB_Path,DB_User,DB_Pass);
                        man.stop();
                        System.out.println("База данных успешно создана.");
                    }
                    catch (Exception e){
                        System.out.println(e);
                    }
                    break;
                case "N":
                    System.out.println("Полный путь с именем ранее созданной базы данных:");
                    DB_Path = in.nextLine();
                    DB_Path = DB_Path.replace("\\","/");
                    System.out.println(DB_Path);
                    System.out.println("Сервер:");
                    DB_Host = in.nextLine();
                    System.out.println("Порт:");
                    DB_Port = in.next();
                    System.out.println("Пользователь:");
                    DB_User = in.next();
                    System.out.println("Пароль:");
                    DB_Pass = in.next();
                    break;
            }
            DB_URL = "jdbc:firebirdsql://"+DB_Host+":"+DB_Port+"/"+DB_Path;
            System.out.println(DB_URL);//Для отладки
            try{
                Class.forName(DB_Driver);
                Connection con = DriverManager.getConnection(DB_URL,DB_User,DB_Pass);
                System.out.println("Соединение с СУБД выполнено.");
                tester.testcheck("Ред База Данных",con);
                con.close();
                System.out.println("Отключение от СУБД выполнено.");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                System.out.println("Ошибка SQL!");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("JDBC драйвер для субд не найден!");
            }
        }
        //Работа с oracle
        if (db == 3){
            System.out.println("Вы выбрали Oracle.");
            System.out.println("Внимание! Перед выполнение дальнейших действий убедитесь что" +
                    "база данных создана стандартными средствами Oracle.");
            DB_Driver = "oracle.jdbc.driver.OracleDriver";
            System.out.println("Имя базы данных:");
            DB_Name = in.next();
            System.out.println("Сервер:");
            DB_Host = in.next();
            System.out.println("Порт:");
            DB_Port = in.next();
            System.out.println("Пользователь:");
            DB_User = in.next();
            System.out.println("Пароль:");
            DB_Pass = in.next();
            DB_URL = "jdbc:oracle:thin:@"+DB_Host+":"+DB_Port+":"+DB_Name;
            System.out.println(DB_URL);
            try{
                Class.forName(DB_Driver);
                Connection con = DriverManager.getConnection(DB_URL, DB_User, DB_Pass);
                System.out.println("Соединение с СУБД выполнено.");
                tester.testcheck("Oracle",con);
                con.close();
                System.out.println("Отключение от СУБД выполнено");
            }catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        //
        //Работа с H2
        if (db == 4){
            System.out.println("Вы выбрали H2.");
            DB_Driver = "org.h2.Driver";
            System.out.println("Полный путь создания базы данных:");
            DB_Path = in.nextLine();
            DB_Path = DB_Path.replace("\\","/");
            System.out.println("Имя базы данных:");
            DB_Name = in.next();
            DB_URL = "jdbc:h2:"+DB_Path+"/"+DB_Name;
            System.out.println(DB_URL);
            try {
                Class.forName(DB_Driver);
                Connection con = DriverManager.getConnection(DB_URL);
                System.out.println("Соединение с СУБД выполнено.");
                tester.testcheck("H2",con);
                con.close();
                System.out.println("Отключение от СУБД выполнено");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        //
        //Работа с Postgre
        if (db == 5){
            System.out.println("Вы выбрали PostgreSQL.");
            System.out.println("Внимание! Перед выполнение дальнейших действий убедитесь что" +
                    "база данных создана стандартными средствами PostgreSQL.");
            DB_Driver = "org.postgresql.Driver";
            System.out.println("Имя базы данных:");
            DB_Name = in.next();
            System.out.println("Сервер:");
            DB_Host = in.next();
            System.out.println("Порт:");
            DB_Port = in.next();
            System.out.println("Пользователь:");
            DB_User = in.next();
            System.out.println("Пароль:");
            DB_Pass = in.next();
            DB_URL = "jdbc:postgresql://"+DB_Host+":"+DB_Port+"/"+DB_Name;
            System.out.println(DB_URL);
            try {
                Class.forName(DB_Driver);
                Connection con = DriverManager.getConnection(DB_URL, DB_User, DB_Pass);
                System.out.println("Соединение с СУБД выполнено.");
                tester.testcheck("PostgreSQL",con);
                con.close();
                System.out.println("Отключение от СУБД выполнено");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        //
        //Работа с MySQL
        if (db == 6){
            System.out.println("Вы выбрали MySQL.");
            DB_Driver = "com.mysql.cj.jdbc.Driver";
            System.out.println("Создать новую базу данных? Y(Да)/N(Нет)");
            r = in.nextLine();
            switch (r) {
                case "Y":
                    System.out.println("Сервер:");
                    DB_Host = in.nextLine();
                    System.out.println("Порт:");
                    DB_Port = in.next();
                    System.out.println("Имя базы данных:");
                    DB_Name = in.next();
                    System.out.println("Пользователь:");
                    DB_User = in.next();
                    System.out.println("Пароль:");
                    DB_Pass = in.next();
                    DB_URL = "jdbc:mysql://"+DB_Host+"/";
                    System.out.println(DB_URL);
                    try {
                        Class.forName(DB_Driver);
                        Connection con = DriverManager.getConnection(DB_URL, DB_User, DB_Pass);
                        Statement stmt = con.createStatement();
                        String sql = "CREATE DATABASE "+DB_Name;
                        System.out.println(sql);
                        stmt.executeUpdate(sql);
                        System.out.println("База данных успешно создана.");
                        con.close();
                        DB_URL = "jdbc:mysql://"+DB_Host+":"+DB_Port+"/"+DB_Name;
                        System.out.println(DB_URL);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    break;
                case "N":
                    System.out.println("Сервер:");
                    DB_Host = in.nextLine();
                    System.out.println("Порт:");
                    DB_Port = in.next();
                    System.out.println("Имя базы данных:");
                    DB_Name = in.next();
                    System.out.println("Пользователь:");
                    DB_User = in.next();
                    System.out.println("Пароль:");
                    DB_Pass = in.next();
                    DB_URL = "jdbc:mysql://"+DB_Host+":"+DB_Port+"/"+DB_Name;
                    System.out.println(DB_URL);
                    break;
            }
            try {
                Class.forName(DB_Driver);
                Connection con = DriverManager.getConnection(DB_URL, DB_User, DB_Pass);
                System.out.println("Соединение с СУБД выполнено.");
                tester.testcheck("MySQL",con);
                con.close();
                System.out.println("Отключение от СУБД выполнено");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        //
        //
        return db;
    }
}
