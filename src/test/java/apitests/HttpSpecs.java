package apitests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class HttpSpecs {
    public static RequestSpecification reqSpec(String url, String basePath){
        return new RequestSpecBuilder()
                .setBaseUri(url)
                .setBasePath(basePath)
                .build();
    }
    public static ResponseSpecification respSpec(){
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }
    public static void setSpecs(RequestSpecification request, ResponseSpecification response){
        RestAssured.requestSpecification = request;
        RestAssured.responseSpecification = response;
    }
}
