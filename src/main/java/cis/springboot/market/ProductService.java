package cis.springboot.market;

import cis.springboot.errors.ConflictException;
import cis.springboot.errors.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    public List<Product>getAllProducts(){
        return productRepository.findAll();
    }

    public Product getByName(String name){
        return productRepository.getByName(name);
    }

    public Product saveProduct(Product product){
        try{
            Product check=productRepository.getByName(product.getName());
            if(check==null)
                return productRepository.save(product);
            else
            {
                product.setQuantity(check.getQuantity()+product.getQuantity());
                product.setLastedit(new Date());
                product.setCreated(check.getCreated());
                deleteProduct(product.getName());
                return saveProduct(product);
            }
        }catch (Exception ex){
            throw new ConflictException("An Error OCCURED !!");
        }

    }
    public void deleteProduct(String name){
         productRepository.deleteByName(name);
    }
    public double buyProduct(String id,int quan){
        Product product=productRepository.getById(id);
        if(product.getQuantity()>=quan)
        {
            double price = product.getPrice() * quan;
            product.setQuantity(product.getQuantity()-quan);
            product.setLastedit(new Date());
            saveProduct(product);
            return price;
        }
        else
            throw new ConflictException("There aren't enough items to buy :(");

    }
    public void deleteAllProducts(){
         productRepository.deleteAll();
    }

    public Product addQuantityOfProduct(String id, int quan) {
        try {
            Product product=productRepository.getById(id);
            product.setQuantity(product.getQuantity()+quan);
            product.setLastedit(new Date());
            saveProduct(product);
            return product;
        }catch (Exception ex){
            throw new NotFoundException("The item doesn't exist !!");
        }

    }
}
