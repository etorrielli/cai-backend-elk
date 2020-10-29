package com.ekeepoit.cai;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CaiApplicationTests {

    static final Logger LOGGER = LoggerFactory.getLogger(CaiApplicationTests.class);
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() {
    }

    /*
     * 1. Devolver todos los accidentes ocurridos entre 2 fechas dadas
     */
    @Test
    public void testGetAccidentsByDates() {
        ResponseEntity<Object[]> responseEntity = restTemplate.getForEntity("/api/accident/elk/datefrom/2016-02-08/dateto/2016-02-20", Object[].class);

        if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            Object[] objects = responseEntity.getBody();
            LOGGER.info("cantidad: " + objects.length);
            // ArrayList<Object> accidentList = new ArrayList<Object>(Arrays.asList(objects));
            // accidentList.forEach(accident -> System.out.println(accident));
        } else {
            System.out.println(responseEntity.getStatusCode().value() + responseEntity.getStatusCode().getReasonPhrase());
        }
    }

    /*
     * 2. Determinar las condiciones más comunes en los accidentes
     */
    @Test
    public void testTopStates() {
        ResponseEntity<Object[]> responseEntity = restTemplate.getForEntity("/api/accident/elk/top-states", Object[].class);

        if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            Object[] objects = responseEntity.getBody();
            ArrayList<Object> topStatesDTOList = new ArrayList<Object>(Arrays.asList(objects));
            topStatesDTOList.forEach(topStatesDTO -> System.out.println(topStatesDTO));
        } else {
            LOGGER.info(responseEntity.getStatusCode().value() + responseEntity.getStatusCode().getReasonPhrase());
        }
    }
}
