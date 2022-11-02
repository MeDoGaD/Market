package cis.springboot.market;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value ={ "/api/v1/market"})
public class productController {
    @Autowired
    private ProductService service;

    @GetMapping(value = {"/getAllProducts"})
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK);
    }

    @PostMapping(value = {"/save"})
    public ResponseEntity<Product>saveProduct(@Valid @RequestBody Product product)
    {
        return new ResponseEntity<>(service.saveProduct(product),HttpStatus.CREATED);
    }

    @GetMapping(value = "/product/buy/{id}/{quan}")
    public ResponseEntity<String>buyProduct(@PathVariable String id,@PathVariable int quan){
      double price=service.buyProduct(id,quan);
      return new ResponseEntity<>(String.format("You have bought %d of this item with %.2f $",quan,price),HttpStatus.OK);
    }

    @GetMapping(value = "/product/add")
    public ResponseEntity<String>addProduct(@RequestParam("id") String id, @RequestParam("quan") int quan){
        Product res=service.addQuantityOfProduct(id,quan);
        return new ResponseEntity<>(String.format("You have added %d of %s >> The new quantity is %d",quan,res.getName(),res.getQuantity()),HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteAll")
    public ResponseEntity<String>deleteAllProducts(){
        service.deleteAllProducts();
        return new ResponseEntity<>("All products was deleted !!",HttpStatus.OK);
    }


}
