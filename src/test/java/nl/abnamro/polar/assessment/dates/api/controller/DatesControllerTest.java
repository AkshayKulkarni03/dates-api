package nl.abnamro.polar.assessment.dates.api.controller;

import nl.abnamro.polar.assessment.dates.api.model.DateResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DatesControllerTest {

    @Autowired
    private DatesController unitToTest;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void contextLoads() throws Exception {
        assertThat(unitToTest).isNotNull();
    }

    @Test
    public void getDaysForInputDateFormat1() {
        String inputDate = LocalDate.now().minusDays(20).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        ResponseEntity<DateResponse> resultEntity = this.restTemplate.getForEntity("http://localhost:" + port + "/api/difference-in-days/" + inputDate, DateResponse.class);

        assertThat(resultEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(resultEntity.getBody()).isNotNull();
        assertThat(resultEntity.getBody().getDays()).isEqualTo(20);

    }

    @Test
    public void getDaysForInputDateFormat2() {
        String inputDate = LocalDate.now().minusYears(1).format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));

        ResponseEntity<DateResponse> resultEntity = this.restTemplate.getForEntity("http://localhost:" + port + "/api/difference-in-days/" + inputDate, DateResponse.class);

        assertThat(resultEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(resultEntity.getBody()).isNotNull();
        assertThat(resultEntity.getBody().getDays()).isEqualTo(365);

    }

    @Test
    public void getDaysForInputDateFormat3() {
        String inputDate = LocalDate.now().minusDays(60).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        ResponseEntity<DateResponse> resultEntity = this.restTemplate.getForEntity("http://localhost:" + port + "/api/difference-in-days/" + inputDate, DateResponse.class);

        assertThat(resultEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(resultEntity.getBody()).isNotNull();
        assertThat(resultEntity.getBody().getDays()).isEqualTo(60);

    }

    @Test
    public void getDaysForInputDateDateFormatException() {
        String inputDate = LocalDate.now().minusDays(2).format(DateTimeFormatter.ofPattern("dd-MM-yy"));

        ResponseEntity<String> resultEntity = this.restTemplate.getForEntity("http://localhost:" + port + "/api/difference-in-days/" + inputDate, String.class);

        assertThat(resultEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(resultEntity.getBody()).isNotNull();
        assertThat(resultEntity.getBody()).isEqualTo(String.format("Input Date %s is not in any supported format", inputDate));

    }


}
