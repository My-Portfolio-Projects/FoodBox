package com.foodbox.backend.controller;

import com.foodbox.backend.entity.OrderItem;
import com.foodbox.backend.service.OrderItemService;
import com.foodbox.backend.service.OrderService;
import com.google.gson.Gson;
import com.stripe.StripeClient;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(value="http://localhost:4200")
@RestController
@RequestMapping(value="/api")
public class PaymentIntentController {

    StripeClient client = new StripeClient("sk_test_51O1ktxSDatT9yImL4ZklbKYt937EvF2CLqxNgo0vxsYcTu13TimHSW09wJG4GxSW43uHye61jo4jdZAoUSkHo9FS00QKPI5UEO");
    OrderService orderService;
    OrderItemService orderItemService;
    PaymentIntentController(OrderService orderService,OrderItemService orderItemService){
        this.orderService = orderService;
        this.orderItemService = orderItemService;
    }
    Gson gson = new Gson();

    @PostMapping(value="/create-checkout-session")
    public Map<String, String> createCheckoutSession(@RequestBody Map<String,Object> request) throws StripeException {


        int orderId = (Integer) request.get("OrderId");
        List<OrderItem> orderItems = orderItemService.getOrderItemsByOrderId(orderId);
        List<SessionCreateParams.LineItem> allItems = new ArrayList<>();


        SessionCreateParams Params =
                SessionCreateParams
                        .builder()
                        .addAllLineItem(allItems)
                        .setUiMode(SessionCreateParams.UiMode.EMBEDDED)
                        .setMode(SessionCreateParams.Mode.PAYMENT).build();

        for(OrderItem item:orderItems){
            SessionCreateParams.LineItem lineItem = SessionCreateParams.LineItem.builder()
                    .setPrice(String.valueOf(item.getPrice()))
                    .setQuantity((long) item.getQuantity())
                    .build();
        allItems.add(lineItem);
        }

        Session session = Session.create(Params);

        Map<String, String> map = new HashMap<>();
        map.put("clientSecret",session.getRawJsonObject().getAsJsonPrimitive("client_secret").getAsString());
        return map;
    }

@GetMapping(value="session-status")
    public Map<String, String> getSessionStatus(@RequestParam("session-id") Session session){

        Map<String,String> map = new HashMap<>();
        map.put("status",session.getRawJsonObject().getAsJsonPrimitive("status").getAsString());
        map.put("customer_email",session.getRawJsonObject().getAsJsonObject("customer_details").getAsJsonPrimitive("email").getAsString());

        return map;
}

}


