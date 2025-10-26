import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class fakerapiNo2 {
    @Test(dataProvider = "getCompany")
    public void getCompanyAPINo2(int numberOfData){
        RestAssured.baseURI = "https://fakerapi.it/api/v1";
        String getCompaniesResponse = given().log().all().header("Content-Type","application/json")
                .queryParam("_quantity",numberOfData)
                .when().get("/companies")
                .then().log().all().assertThat().statusCode(200).extract().response().asString();

        JsonPath getCompanies = JsonPath.from(getCompaniesResponse);

        // answer for question No. 2
        Assert.assertEquals(getCompanies.getInt("company.size()"), numberOfData, "Incorrect company count in getCompanyAPI");
    }

    @DataProvider(name = "getCompany")
    public Object[][] testDataCompany(){
        return new Object[][]{{20},{5},{1}};
    }
}
