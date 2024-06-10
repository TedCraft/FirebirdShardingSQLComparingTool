import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
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
import java.util.jar.JarFile;
import java.util.jar.JarEntry;

public class Main {
    public static  String stand;
    public static void main(String[] args) throws URISyntaxException, IOException {
        //
        Connections connections = new Connections();
        Parser parser = new Parser();

        //Вывод на экран всех найденных стандартов
        ClassLoader cl = Main.class.getClassLoader();
        URL res = cl.getResource("json/SQL2011 for Red DataBase.json");
        File file = new File(res.getPath());
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(cl.getResourceAsStream("json/SQL2011 for Red DataBase.json")));
        stand = file.getName();
        stand = stand.replace(".json", "");
        parser.parsejson(bufferedReader);
        connections.dbconnection(3);
        //
    }
}
