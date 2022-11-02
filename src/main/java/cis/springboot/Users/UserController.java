package cis.springboot.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenUtil tokenUtil;

    @PostMapping(value ={"","/"})
    public JwtResponse signIn(@RequestBody SignInRequest signInRequest)
    {
      final Authentication authentication= authenticationManager.
                authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getUsername(),signInRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails user= userService.loadUserByUsername(signInRequest.getUsername());
        String token=tokenUtil.generateToken(user);
        JwtResponse jwtResponse=new JwtResponse(token);
        return jwtResponse;
    }

    @PostMapping(value ="/save")
    public Admin saveAdmin(@Valid @RequestBody Admin admin){
       return userService.saveAdmin(admin);
    }

}
