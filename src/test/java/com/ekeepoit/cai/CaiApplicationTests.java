package com.ekeepoit.cai;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.Date;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CaiApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() {
    }

//    @Test
//    public void testGetAccidentsByDates() {
//        ResponseEntity<Object[]> responseEntity = restTemplate.getForEntity("/api/accident/datefrom/2016-02-08/dateto/2016-02-09", Object[].class);
//
//        if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
//            Object[] objects = responseEntity.getBody();
//            System.out.println("cantidad: " + objects.length);
//            // ArrayList<Object> accidentList = new ArrayList<Object>(Arrays.asList(objects));
//            // accidentList.forEach(accident -> System.out.println(accident));
//        } else {
//            System.out.println(responseEntity.getStatusCode().value() + responseEntity.getStatusCode().getReasonPhrase());
//        }
//    }

    @Test
    @SuppressWarnings("deprecation")
    public void testGetAccidentsGroup() {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = mongoClient.getDatabase("accidentdb");

        MongoCollection<Document> collection = database.getCollection("accident");

        long antes = new Date().getTime();
        System.out.println("cantidad " + collection.countDocuments(Filters.eq("State", "OH")));
        long despues = new Date().getTime();
        System.out.println("tiempo " + (despues - antes) + " milisegundos");
    }
}
