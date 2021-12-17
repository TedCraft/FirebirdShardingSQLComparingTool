
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import org.apache.commons.io.FileUtils;
public class Html {
    public void Genhtml(ArrayList<String> res, String sydb){
        File htmlTemplateFile = new File("src/main/resources/template.html");
        String htmlString = null;
        try{
            htmlString = FileUtils.readFileToString(htmlTemplateFile,"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String title = "Результат сравнения";
        StringBuilder sb = new StringBuilder();
        for (Object obj : res){
            sb.append(obj);
        }
        String body = sb.toString();
        htmlString = htmlString.replace("$title", title);
        htmlString = htmlString.replace("$SYDB", sydb);
        htmlString = htmlString.replace("$Standart", Main.stand);
        htmlString = htmlString.replace("$result", body);
        File newHtmlFile = new File("src/main/resources/results.html");
        try {
            FileUtils.writeStringToFile(newHtmlFile, htmlString,"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
