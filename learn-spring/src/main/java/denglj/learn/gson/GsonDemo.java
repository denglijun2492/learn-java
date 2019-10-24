package denglj.learn.gson;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;

@Slf4j
public class GsonDemo {
    public static void main(String[] args)throws Exception {
        InputStream resourceAsStream = GsonDemo.class.getResourceAsStream("person.json");
//        InputStreamReader inputStreamReader = new InputStreamReader(resourceAsStream);
        String json = IOUtils.toString(resourceAsStream);
        //1.
        Person gsonPerson = new Gson().fromJson(json, Person.class);
        log.info(gsonPerson.getBirthday());

        //2.
        JsonObject jsonObject = (JsonObject)new JsonParser().parse(json);
        log.info(jsonObject.get("name").getAsString());

    }
}
