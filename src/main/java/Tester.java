import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;

public class Tester {
    Html htmlgen = new Html();
    int exctres;
    public static ArrayList<String> results = new ArrayList<>();
    public void testcheck(String sydb, Connection con){
        for (Object obj : Parser.testcase){
            String str = (String) obj;
            System.out.println(str);
            if(str.contains("Name")){
                str = str.replace("Name:","");
                results.add("<tr><th colspan = 5>" + str + "</th></tr>");
            }
            if(str.contains("Case:")){
                exctres = 0;
                str = str.replace("Case:","");
                results.add("<tr><td>"+str+"</td>");
            }
            if (str.contains("Query:")){
                if (exctres == 0)
                    results.add("none");
                str = str.replace("Query:","");
                Statement statement = null;
                try {
                    statement = con.createStatement();
                    statement.executeUpdate(str);
                    exctres = 1;
                    results.remove(results.size()-1);
                    results.add("<td colspan = 3 align = right><b>" + "OK" + "</td><td width = 50%> no error</td></tr>");
                    System.out.println("Запрос выполнен!");
                } catch (SQLException throwables) {
                    exctres = -1;
                    System.out.println("Запрос не выполнен!");
                    results.remove(results.size()-1);
                    results.add("<td colspan = 3 align = right>" + "Failed" + "</td><td width = 50%>"+ throwables.getMessage() +"</td></tr>");
                    //throwables.printStackTrace();//for debug
                    System.out.println(throwables.getMessage());
                }
            }
        }
        htmlgen.Genhtml(results,sydb);
    }
}
