package ucu.edu.ua.apps.flowerstore_continue;

import flowers.FlowerColor;
import flowers.FlowerType;
import flowerstore_continue.OrderController;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = OrderController.class)
@AutoConfigureMockMvc
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCreateOrder() throws Exception {
        mockMvc.perform(post("/orders/create")
                        .param("userId", "1111112311")
                        .param("payment", "paypal")
                        .param("delivery", "dhl"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderId", not(emptyString())))
                .andExpect(jsonPath("$.userId", is("1111112311")))
                .andExpect(jsonPath("$.payment", is("paypal")))
                .andExpect(jsonPath("$.delivery", is("dhl")))
                .andExpect(jsonPath("$.totalPrice", is(0.0)));
    }

    @Test
    void testAddItemAndGetTotalPrice() throws Exception {
        // 1. Create a new order
        String orderId = mockMvc.perform(post("/orders/create")
                        .param("userId", "99999")
                        .param("payment", "credit")
                        .param("delivery", "post"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString()
                .split("\"orderId\":\"")[1]
                .split("\"")[0]; // extract orderId from JSON

        // 2. Add an item
        mockMvc.perform(post("/orders/" + orderId + "/add")
                        .param("type", FlowerType.ROSE.name())
                        .param("color", FlowerColor.RED.name())
                        .param("price", "10.0")
                        .param("count", "3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].flower.price", is(10.0)))
                .andExpect(jsonPath("$[0].count", is(3)));

        // 3. Verify total price
        mockMvc.perform(get("/orders/" + orderId + "/price"))
                .andExpect(status().isOk())
                .andExpect(content().string(not("0.0")));
    }

    @Test
    void testPayAndDeliver() throws Exception {
        // Create order
        String orderId = mockMvc.perform(post("/orders/create")
                        .param("userId", "123")
                        .param("payment", "paypal")
                        .param("delivery", "dhl"))
                .andReturn()
                .getResponse()
                .getContentAsString()
                .split("\"orderId\":\"")[1]
                .split("\"")[0];

        // Pay for it
        mockMvc.perform(post("/orders/" + orderId + "/pay"))
                .andExpect(status().isOk())
                .andExpect(content().string(anyOf(is("Payment successful"), is("Payment failed"))));

        // Deliver
        mockMvc.perform(post("/orders/" + orderId + "/deliver")
                        .param("destination", "Kyiv"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Delivered")));
    }

    @Test
    void testInvalidPaymentThrowsError() throws Exception {
        mockMvc.perform(post("/orders/create")
                        .param("userId", "555")
                        .param("payment", "bitcoin")
                        .param("delivery", "dhl"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void testInvalidOrderThrowsError() throws Exception {
        mockMvc.perform(get("/orders/unknown123/price"))
                .andExpect(status().is4xxClientError());
    }
}
