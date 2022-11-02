package cis.springboot.market;

import cis.springboot.AbstractMarketAppTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.List;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.mockito.BDDMockito.*;
import static org.hamcrest.Matchers.*;

public class ProductControllerTest extends AbstractMarketAppTest {
    @MockBean
    private ProductService productService;

    @Test
    public void whenGetAllProducts_ThenReturnJsonArray() throws Exception {
        Product product1=new Product("Tomato",20,50 );
        Product product2=new Product("Carrot",10,100 );
        Product product3=new Product("Banana",30,20 );
        List<Product> testData= Arrays.asList(product1,product2,product3);

        given(productService.getAllProducts()).willReturn(testData);
        mockMvc.perform(doGet("/api/v1/market/getAllProducts")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(3)))
                .andExpect(jsonPath("$[0].name",equalTo(product1.getName())))
                .andExpect(jsonPath("$[1].name",equalTo(product2.getName())));
    }


    @Test
    public void whenAddProduct_thenSaveProduct() throws Exception{
        Product product1=new Product("Tomato",20,50 );
        given(productService.saveProduct(Mockito.any(Product.class))).willReturn(product1);
        ObjectMapper mapper=new ObjectMapper();
        mockMvc.perform(doPost("/api/v1/market/save").contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(product1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name",is(product1.getName())));
    }


}
