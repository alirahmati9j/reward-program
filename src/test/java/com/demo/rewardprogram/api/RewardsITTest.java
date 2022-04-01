package com.demo.rewardprogram.api;

import com.demo.rewardprogram.config.BaseRestOAuthAwareIT;
import com.demo.rewardprogram.dto.OrderDTO;
import com.demo.rewardprogram.dto.PointsDTO;
import com.demo.rewardprogram.errorhandler.ErrorResponse;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.util.UriComponentsBuilder;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@Sql(
        scripts = { "classpath:sql/truncate.sql", "classpath:sql/RewardsIT.sql", "classpath:sql/UsersIT.sql"},
        config = @SqlConfig(transactionMode = SqlConfig.TransactionMode.ISOLATED))
@TestPropertySource("classpath:application-test.properties")
@FixMethodOrder(MethodSorters.JVM)
@ActiveProfiles("LOCAL")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RewardsITTest extends BaseRestOAuthAwareIT {

    @Test
    @Order(1)
    public void calculateRewardWhenPurchase120() {

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setTotal(120.0);
        orderDTO.setUsername("demo.user");

        HttpEntity<Object> request = new HttpEntity<Object>(orderDTO, null);

        UriComponentsBuilder uriComponentsBuilder = createUriComponent("/reward");
        ResponseEntity<PointsDTO> responseEntity = getRestTemplate().postForEntity
                (uriComponentsBuilder.build().toUri(), request, PointsDTO.class);

        assertEquals(90, responseEntity.getBody().getEarnedPoints());

    }

    @Test
    @Order(2)
    public void calculateRewardWhenPurchase60() {

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setTotal(60.0);
        orderDTO.setUsername("demo.user");

        HttpEntity<Object> request = new HttpEntity<Object>(orderDTO, null);

        UriComponentsBuilder uriComponentsBuilder = createUriComponent("/reward");
        ResponseEntity<PointsDTO> responseEntity = getRestTemplate().postForEntity
                (uriComponentsBuilder.build().toUri(), request, PointsDTO.class);

        assertEquals(10, responseEntity.getBody().getEarnedPoints());

    }

    @Test
    @Order(3)
    public void calculateRewardWhenPurchase49() {

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setTotal(49.0);
        orderDTO.setUsername("demo.user");

        HttpEntity<Object> request = new HttpEntity<Object>(orderDTO, null);

        UriComponentsBuilder uriComponentsBuilder = createUriComponent("/reward");
        ResponseEntity<PointsDTO> responseEntity = getRestTemplate().postForEntity
                (uriComponentsBuilder.build().toUri(), request, PointsDTO.class);

        assertEquals(0, responseEntity.getBody().getEarnedPoints());

    }

    @Test(expected = HttpClientErrorException.BadRequest.class)
    @Order(4)
    public void calculateRewardWhenUsernameIsInvalid() {

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setTotal(120.0);
        orderDTO.setUsername("demo.user2");

        HttpEntity<Object> request = new HttpEntity<Object>(orderDTO, null);

        UriComponentsBuilder uriComponentsBuilder = createUriComponent("/reward");
        ResponseEntity<ErrorResponse> responseEntity = getRestTemplate().postForEntity
                (uriComponentsBuilder.build().toUri(), request, ErrorResponse.class);

        assertEquals(false, responseEntity.getBody().isStatus());
        assertEquals("User does not exist.", responseEntity.getBody().getMessage());
    }


    @Test(expected = HttpClientErrorException.BadRequest.class)
    @Order(4)
    public void calculateRewardWhenPurchaseIsInvalid() {

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setTotal(-10.0);
        orderDTO.setUsername("demo.user");

        HttpEntity<Object> request = new HttpEntity<Object>(orderDTO, null);

        UriComponentsBuilder uriComponentsBuilder = createUriComponent("/reward");
        ResponseEntity<ErrorResponse> responseEntity = getRestTemplate().postForEntity
                (uriComponentsBuilder.build().toUri(), request, ErrorResponse.class);

        assertEquals(false, responseEntity.getBody().isStatus());
        assertEquals("User does not exist.", responseEntity.getBody().getMessage());
    }

    @Test
    @Order(4)
    public void getTotalRewardsEarnedIn022022() {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json");

        HttpEntity<Object> request = new HttpEntity<Object>(null, httpHeaders);

        ResponseEntity<PointsDTO> responseEntityAsString =
                getRestTemplate().exchange(
                        createUriComponent("/reward?username=demo.user&date=02-2022").toUriString(), HttpMethod.GET, request, PointsDTO.class);

        assertEquals(219, responseEntityAsString.getBody().getEarnedPoints());
    }
}
