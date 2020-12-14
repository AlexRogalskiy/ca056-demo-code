package cashier;

import java.net.URI;
import java.util.logging.Logger;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Component
@RestController
public class OrderController {
    private final RestTemplate restTemplate;

    RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();

    public OrderController() {
        this.restTemplate = restTemplateBuilder.build();
    }

    @RequestMapping(value = "/localOrderUp", method = RequestMethod.POST)
    public String sendOrder(@RequestBody OrderMessageRequest order){
        System.out.println("-- Sending order for "+ order.orderName + " to our order sorter -- ");
        URI uri = URI.create("http://localhost:8080/orderUp/");
        String response = restTemplate.postForObject(uri, order, String.class);
        System.out.println("-- All good - the order has been sent -- ");
        return response;
    }


}
