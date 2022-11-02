package cis.springboot.util;

import cis.springboot.Users.Admin;
import cis.springboot.Users.UserService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class FirstTimeInitialiizer implements CommandLineRunner {
    @Autowired
    public UserService userService;
    private Log logger= LogFactory.getLog(FirstTimeInitialiizer.class);

    @Override
    public void run(String... args) throws Exception {
        if(userService.getAllAdmins().isEmpty())
        {
            logger.info("We have just created a user for you :)");
            userService.saveAdmin(new Admin("Medo","medo@g.com","medo123","011","Cairo"));
        }
    }
}
