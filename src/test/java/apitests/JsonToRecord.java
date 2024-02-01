package apitests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.burt.jmespath.JmesPath;
import io.burt.jmespath.jackson.JacksonRuntime;
import io.restassured.response.Response;

import java.io.IOException;

public class JsonToRecord {
    static ObjectMapper mapper = new ObjectMapper();
    static JmesPath<JsonNode> jmespath = new JacksonRuntime();
    public static <T> T deserialize(Response response, String jmesPath, Class<T> record) {
        try {
            return mapper.treeToValue(jmespath.compile(jmesPath).search(mapper.readTree(response.getBody().asString())), record);
        } catch (JsonProcessingException e){}
        return null;
    }
}
