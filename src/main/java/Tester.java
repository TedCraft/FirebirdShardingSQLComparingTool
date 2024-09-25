import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.sql.Connection;

public class Tester {
    Html htmlgen = new Html();
    int exctres;
//    public static ArrayList<String> results = new ArrayList<>();
    public static ArrayList<String> errors = new ArrayList<>();
    public LinkedHashMap<String, ArrayList<String>> testcheck(String sydb, Connection con){
        LinkedHashMap<String, ArrayList<String>> results = new LinkedHashMap<>();
        String curName = "";
        for (Object obj : Parser.testcase) {
            String str = (String) obj;
//            System.out.println(str);
            if(str.contains("Name:")){
                exctres = 0;
                str = str.replace("Name:","");
                curName = "<tr><th colspan = 5>" + str + "</th></tr>"+"\n";
                results.put(curName, new ArrayList<>());
            }
            if(str.contains("Case:")){
                exctres = 0;
                errors.clear();
                str = str.replace("Case:","");
                results.get(curName).add("<tr><td>"+str+"</td>"+"\n");
            }
            if (str.contains("Query:")){
                if (exctres == 0)
                    results.get(curName).add("none");
                str = str.replace("Query:","");
                Statement statement = null;
                try {
                    statement = con.createStatement();
                    if (str.contains("COMMIT"))
                        con.commit();
                    else if (str.contains("ROLLBACK"))
                        con.rollback();
                    else
                        statement.execute(str);
                    if (exctres == 1 || exctres == 0) {
                        results.get(curName).remove(results.get(curName).size() - 1);
                        results.get(curName).add("<td colspan = 3 align = right><b>" + "OK" + "</td><td width = 50%> no error</td></tr>"+"\n");
                        exctres = 1;
                    }
//                    System.out.println("Запрос выполнен!");
                } catch (Exception throwables) {
                    errors.add("<table border=\"1\"><tr><td>"+throwables.getMessage()+"</td></tr><tr><td>"+Arrays.toString(throwables.getStackTrace())+"</td></tr></table>"+"\n");
                    if (exctres == 1 || exctres == 0){
//                        System.out.println("Запрос не выполнен!");
                        results.get(curName).remove(results.get(curName).size()-1);
                        results.get(curName).add("<td colspan = 3 align = right>" + "Failed" + "</td><td width = 50%>"+ errors.toString().replace("[", "").replace("]","").replace(",","") +"</td></tr>"+"\n");
                        exctres = -1;
                    }
//                    System.out.println(throwables.getMessage());
                    try {
                        statement.close();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
//        htmlgen.Genhtml(results,sydb);
        return results;
    }
}
