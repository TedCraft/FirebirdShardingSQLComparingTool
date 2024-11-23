import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Objects;
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
                    if (!Objects.equals(jdbcRes.get(i), shardRes.get(i)) &&
                            Objects.equals(jdbcRes.get(i), "<td colspan = 3 align = right><b>OK</td><td width = 50%> no error</td></tr>\n")) {
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
