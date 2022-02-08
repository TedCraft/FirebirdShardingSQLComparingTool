import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Parser {
    public static ArrayList<String> testcase = new ArrayList<>();
    public void parsejson(String file){
        try{
            Object obj = new JSONParser().parse(new FileReader("src/main/resources/json/" + file));
            JSONObject jsobj1 = (JSONObject) obj;
            for(int i = 0;i<jsobj1.size();i++){
                JSONObject jsobj2 = (JSONObject) jsobj1.get("Test"+(i+1));
                String name = (String) jsobj2.get("Name");
                System.out.println(name);//debug
                testcase.add("Name: " + jsobj2.get("Name").toString());
                JSONArray cases = (JSONArray) jsobj2.get("Case");
                Iterator itr = cases.iterator();
                while (itr.hasNext()) {
                    JSONObject jsobj3 = (JSONObject) itr.next();
                    String namecase = (String) jsobj3.get("NameCase");
                    //System.out.println(namecase);
                    testcase.add("Case: " + jsobj3.get("NameCase").toString());
                    for (int y = 1; y < jsobj3.size(); y++) {
                        String query = (String) jsobj3.get("Query"+(y));
                        //System.out.println(query);
                        testcase.add("Query: " + jsobj3.get("Query"+(y)).toString());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
