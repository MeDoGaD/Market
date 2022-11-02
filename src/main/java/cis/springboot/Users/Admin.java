package cis.springboot.Users;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Document(collection = "users")
public class Admin implements UserDetails {

    @Id
    private String id;
    @NotNull(message = "Name is required !!")
    private String name;
    @NotNull(message = "Username is required !!")
    private String username;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @NotNull(message = "Password is required !!")
    private String password;
    @NotNull(message = "Phone is required !!")
    private String phone;
    @NotNull(message = "City is required !!")
    private String city;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy >> hh:mm:ss")
    private Date created;

    public Admin(String name, String username,String password, String phone, String city) {
        this.name = name;
        this.username = username;
        this.phone = phone;
        this.password=password;
        this.city = city;
        this.created=new Date();
    }

    public Admin() {
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
