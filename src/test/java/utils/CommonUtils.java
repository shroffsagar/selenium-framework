package utils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CommonUtils {
    public static String readFileFromClasspath(String resourceName){
        URL urlToResource = CommonUtils.class.getClassLoader().getResource(resourceName);
        if(urlToResource == null) throw new RuntimeException(resourceName+" does not exists");
        try {
            File file = new File(urlToResource.toURI());
            byte[] data = Files.readAllBytes(Paths.get(file.toURI()));
            return new String(data);
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
