package org.acme;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@QuarkusTest
public class LoanApprovalTest {

    static {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    public void testApproved() {
        given()
                .body("{\"FICO Score\":765,\"DTI Ratio\":0.1,\"PITI Ratio\":0.1}")
                .contentType(ContentType.JSON)
                .when()
                .post("/Loan_approvals")
                .then()
                .statusCode(200)
                .body("'DTI Rating'", is("Good"))
                .body("'PITI Rating'", is("Good"))
                .body("'Loan Approval'", is("Approved"));
    }

}