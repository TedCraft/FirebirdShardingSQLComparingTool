import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main {
    public static  String stand;
    public static void main(String[] args) throws URISyntaxException {
        //
        Connections connections = new Connections();
        Scanner in = new Scanner(System.in);
        Parser parser = new Parser();
        ArrayList<String> st = new ArrayList<>();//Список для файлов
        int i = 1;//Переменная для счетчика файлов
        //
        System.out.println("Выберете стандарт:");
        //JAR
        //https://javascopes.com/java-read-a-file-from-resources-folder-7f688b08/
        /*List result;
        String jarPath = Main.class.getProtectionDomain()
                .getCodeSource()
                .getLocation()
                .toURI()
                .getPath();
        System.out.println("JAR Path:"+jarPath);
        URI uri = URI.create("jar:file:" + jarPath);
        try(FileSystem fs = FileSystems.newFileSystem(uri, Collections.emptyMap())){
            result = Files.walk(fs.getPath("json"))
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        //Вывод на экран всех найденных стандартов
        ClassLoader cl = Main.class.getClassLoader();;
        URL res = cl.getResource("json");
        File dir = new File(res.toURI());
        if(dir.isDirectory()){
            for(File item : dir.listFiles((dir1, name) -> name.endsWith(".json"))) {
                System.out.println(i + ". " + item.getName());
                st.add(item.getName());
                i++;
            }
        }
        int num = in.nextInt();
        if (num <= i){
            stand = st.get(num - 1);
            stand = stand.replace(".json", "");
            parser.parsejson(st.get(num - 1));
        }
        //
        //Вывод на экран списока СУБД
        //Заполняется вручную
        System.out.println("Выберете СУБД:");
        System.out.println("1. Firebird");
        System.out.println("2. Red DataBase");
        System.out.println("3. Oracle");
        System.out.println("4. H2");
        System.out.println("5. PostgreSQL");
        System.out.println("6. MYSQL");
        //
        //Передем номер СУБД для выполнения подключения
        num = in.nextInt();
        if (num >0)
            connections.dbconnection(num);
        //
    }
}
