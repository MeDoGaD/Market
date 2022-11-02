package cis.springboot.market;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;

@Document(collection = "products")
public class Product {

    @Id
    private String id;
    @NotNull(message = "Name is required !!")
    private String name;
    @NotNull(message = "Price is required !!")
    @Min(value = 1,message = "The price must be greater than 0")
    private double price;
    @NotNull(message = "Quantity is required !!")
    @Min(value = 1,message = "The quantity must be greater than 0")
    private int quantity;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy >> hh:mm:ss")
    private Date created;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy >> hh:mm:ss")
    private Date lastedit;

    public Product(@NotNull String name, @NotNull double price,@NotNull int quantity) {
        this();
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Product() {
        this.created = new Date();
        this.lastedit = new Date();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getLastedit() {
        return lastedit;
    }

    public void setLastedit(Date lastedit) {
        this.lastedit = lastedit;
    }
}
