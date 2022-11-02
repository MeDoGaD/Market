package cis.springboot.market;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.*;
import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
public class ProductServiceTest {

    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private ProductService service;

    @TestConfiguration
    static class ProductServiceContextConfiguration{
        @Bean
        public ProductService productService(){return new ProductService();}
    }

    @Test
    public void whenFindAll_ReturnProductsList(){
        //Mockup
        Product product1=new Product("Tomato",20,50 );
        Product product2=new Product("Carrot",10,100 );
        Product product3=new Product("Banana",30,20 );

        List<Product>testData= Arrays.asList(product1,product2,product3);

        given(productRepository.findAll()).willReturn(testData);

        assertThat(service.getAllProducts()).hasSize(3).contains(product1,product2,product3);
    }

    @Test
    public void WhenGetByName_ProductShouldBeFound(){
        Product product1=new Product("Tomato",20,50 );

        given(productRepository.getByName(anyString())).willReturn(product1);

        Product product=service.getByName("Tomato");
        assertThat(product.getName()).contains("Tomato");
    }

    @Test
    public void deleteAllProducts(){
        Product product1=new Product("Tomato",20,50 );
        Product product2=new Product("Carrot",10,100 );
        Product product3=new Product("Banana",30,20 );
        service.deleteAllProducts();
        assertThat(productRepository.findAll()).hasSize(0);
    }

}
