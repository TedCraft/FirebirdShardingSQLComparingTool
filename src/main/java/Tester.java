import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;

public class Tester {
    Html htmlgen = new Html();
    int exctres;
    public static ArrayList<String> results = new ArrayList<>();
    public static ArrayList<String> errors = new ArrayList<>();
    public void testcheck(String sydb, Connection con){
        for (Object obj : Parser.testcase){
            String str = (String) obj;
            System.out.println(str);
            if(str.contains("Name:")){
                exctres = 0;
                str = str.replace("Name:","");
                results.add("<tr><th colspan = 5>" + str + "</th></tr>"+"\n");
            }
            if(str.contains("Case:")){
                exctres = 0;
                errors.clear();
                str = str.replace("Case:","");
                results.add("<tr><td>"+str+"</td>"+"\n");
            }
            if (str.contains("Query:")){
                if (exctres == 0)
                    results.add("none");
                str = str.replace("Query:","");
                Statement statement = null;
                try {
                    statement = con.createStatement();
                    statement.execute(str);
                    if (exctres == 1 || exctres == 0) {
                        results.remove(results.size() - 1);
                        results.add("<td colspan = 3 align = right><b>" + "OK" + "</td><td width = 50%> no error</td></tr>"+"\n");
                        exctres = 1;
                    }
                    System.out.println("Запрос выполнен!");
                } catch (SQLException throwables) {
                    errors.add("<table><tr><td>"+throwables.getMessage()+"</td></tr></table>"+"\n");
                    if (exctres == 1 || exctres == 0){
                        System.out.println("Запрос не выполнен!");
                        results.remove(results.size()-1);
                        results.add("<td colspan = 3 align = right>" + "Failed" + "</td><td width = 50%>"+ errors.toString().replace("[", "").replace("]","").replace(",","") +"</td></tr>"+"\n");
                        exctres = -1;
                    }
                    System.out.println(throwables.getMessage());
                }
            }
        }
        htmlgen.Genhtml(results,sydb);
    }
}
