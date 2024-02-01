package apitests.apiinfo;

import apitests.MyArgumentsProvider;
import apitests.HttpSpecs;
import apitests.JsonToRecord;
import apitests.apiinfo.records.ApiInfoQueryRecord;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static io.restassured.RestAssured.given;

public class TownsTests {
    @ParameterizedTest
    @ArgumentsSource(MyArgumentsProvider.class)
    void url(String city, Integer pageId) {
        HttpSpecs.setSpecs(HttpSpecs.reqSpec("https://en.wikipedia.org", "w/api.php"), HttpSpecs.respSpec());
        Response response = given()
                .param("action", "query")
                .param("inprop", "url")
                .param("prop", "info")
                .param("titles", city)
                .param("format", "json")
                .when().get().then().log().body().extract().response();
        ApiInfoQueryRecord newJsonNode = JsonToRecord.deserialize(response, "query.pages.[*][0][0]", ApiInfoQueryRecord.class);
        System.out.println(newJsonNode.fullurl());
        Assertions.assertThat(newJsonNode.pageid()).isEqualTo(pageId);
        Assertions.assertThat(newJsonNode).hasNoNullFieldsOrProperties();
    }
    @ParameterizedTest
    @ArgumentsSource(MyArgumentsProvider.class)
    void noUrl(String city) {
        HttpSpecs.setSpecs(HttpSpecs.reqSpec("https://en.wikipedia.org", "w/api.php"), HttpSpecs.respSpec());
        Response response = given()
                .param("action", "query")
                .param("prop", "info")
                .param("titles", city)
                .param("format", "json")
                .when().get().then().log().body().extract().response();
        System.out.println("SECOND TEST: "+response);
        ApiInfoQueryRecord newJsonNode = JsonToRecord.deserialize(response, "query.pages.[*][0][0]", ApiInfoQueryRecord.class);
        System.out.println(newJsonNode.fullurl());
        Assertions.assertThat(newJsonNode).hasNoNullFieldsOrProperties();
    }
    @ParameterizedTest
    @ArgumentsSource(MyArgumentsProvider.class)
    void title(String city) {
        HttpSpecs.setSpecs(HttpSpecs.reqSpec("https://en.wikipedia.org", "w/api.php"), HttpSpecs.respSpec());
        Response response = given()
                .param("action", "query")
                .param("prop", "info")
                .param("titles", city)
                .param("format", "json")
                .when().get().then().log().body().extract().response();
        System.out.println("THIRD TEST: "+response);
        ApiInfoQueryRecord newJsonNode = JsonToRecord.deserialize(response, "query.pages.[*][0][0]", ApiInfoQueryRecord.class);
        System.out.println(newJsonNode.title());
    }
}
