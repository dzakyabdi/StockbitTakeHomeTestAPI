import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.victools.jsonschema.generator.OptionPreset;
import com.github.victools.jsonschema.generator.SchemaGenerator;
import com.github.victools.jsonschema.generator.SchemaGeneratorConfig;
import com.github.victools.jsonschema.generator.SchemaGeneratorConfigBuilder;
import com.github.victools.jsonschema.generator.SchemaVersion;

import static io.restassured.RestAssured.given;

public class fakerapiNo3 {
    @Test
    public void getCompanyAPINo3() {
        RestAssured.baseURI = "https://fakerapi.it/api/v1";
        CompanyPojo getCompaniesResponse = given().log().all().header("Content-Type","application/json")
                .when().get("/companies").as(CompanyPojo.class);

        for(CompanyDetailPojo companyDetail: getCompaniesResponse.getCompany()){
            System.out.println(companyDetail.getId());
            // answer for question No. 3a
            Assert.assertNotNull(companyDetail.getId(), "Company ID is null");
        }

        //answer for question No. 3b
        SchemaGeneratorConfigBuilder configBuilder = new SchemaGeneratorConfigBuilder(SchemaVersion.DRAFT_2020_12, OptionPreset.PLAIN_JSON);
        SchemaGeneratorConfig config = configBuilder.build();
        SchemaGenerator generator = new SchemaGenerator(config);
        JsonNode jsonSchema = generator.generateSchema(CompanyPojo.class);

        System.out.println(jsonSchema.toPrettyString());
    }
}