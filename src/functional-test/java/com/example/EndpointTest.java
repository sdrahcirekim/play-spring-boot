package com.example;

import com.example.builders.ExampleEntityBuilder;
import com.example.domain.ExampleEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = App.class)
public class EndpointTest {

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private ObjectMapper mapper;

    @Test
    public void shouldStoreValues() throws JsonProcessingException {
        ExampleEntity exampleEntity = new ExampleEntityBuilder().generateExampleValues().build();
        HttpEntity<String> entity = getStringHttpEntity(exampleEntity);

        ResponseEntity<String> response = restTemplate.postForEntity("/values/store", entity, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void givenRootIdShouldReturnAExampleEntityAssociatedWithRootId() throws JsonProcessingException {
        String rootId = "bob";
        populateExampleEntityForRoot(rootId);

        ResponseEntity<String> response = restTemplate.getForEntity("/values/read/" + rootId, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    private HttpEntity<String> getStringHttpEntity(Object object) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String jsonMeterData = mapper.writeValueAsString(object);
        return (HttpEntity<String>) new HttpEntity(jsonMeterData, headers);
    }

    private void populateExampleEntityForRoot(String rootId) throws JsonProcessingException {
        ExampleEntity readings = new ExampleEntityBuilder().setRootId(rootId)
                .generateExampleValues(20)
                .build();

        HttpEntity<String> entity = getStringHttpEntity(readings);
        restTemplate.postForEntity("/values/store", entity, String.class);
    }
}
