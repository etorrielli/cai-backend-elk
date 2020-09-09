package com.ekeepoit.cai;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CaiApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    public void testGetAccidentsByDates() {
        ResponseEntity<Object[]> responseEntity = restTemplate.getForEntity("/api/accident/datefrom/2016-02-08/dateto/2016-02-09", Object[].class);

        if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            Object[] objects = responseEntity.getBody();
            System.out.println("cantidad: " + objects.length);
            // ArrayList<Object> accidentList = new ArrayList<Object>(Arrays.asList(objects));
            // accidentList.forEach(accident -> System.out.println(accident));
        } else {
            System.out.println(responseEntity.getStatusCode().value() + responseEntity.getStatusCode().getReasonPhrase());
        }
    }
}
