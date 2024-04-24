package roomescape.controller;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class ReservationControllerTest {

    @Test
    @DisplayName("해당 ID를 가진 시간이 존재하지 않는다면 400으로 응답한다.")
    void createReservation_AbsenceId_BadRequest() {
        Map<String, String> params = Map.of(
                "name", "브라운",
                "date", "2023-08-05",
                "timeId", "0"
        );

        RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(params)
                .when().post("/reservations")
                .then().log().all()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
}