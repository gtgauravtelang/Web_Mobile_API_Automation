package utilities;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;

public class jsonReaderCode {

    public static String getTestData(String key) throws IOException, ParseException {
        String data;
        return data = (String) returnPair().get(key);
    }

    public static JSONObject returnPair() throws IOException, ParseException {
        File file1 = new File("resources/TestData/testURLs.json");
        String jsonf = FileUtils.readFileToString(file1, "UTF-8");
        Object obj1 = new JSONParser().parse(jsonf);
        JSONObject jsonObj = (JSONObject) obj1;
        return jsonObj;
    }
}
