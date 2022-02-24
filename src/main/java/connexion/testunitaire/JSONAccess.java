package connexion.testunitaire;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONAccess {
  @SuppressWarnings("exports")
  public static JSONArray getJSON(){
    JSONParser parser = new JSONParser();
    try (Reader reader = new FileReader("src/main/resources/connexion/testunitaire/user.json")){
      JSONArray jsonArray = (JSONArray) parser.parse(reader);
      return jsonArray;
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return new JSONArray();
  }
  
  @SuppressWarnings("exports")
  public static void writeToJSON(JSONArray jsonArray) {
    try (FileWriter file = new FileWriter("src\\main\\resources\\connexion\\testunitaire\\user.json")){
      System.out.println(jsonArray.toJSONString());
      file.write(jsonArray.toJSONString());
      file.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
