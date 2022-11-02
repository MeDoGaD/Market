package cis.springboot.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter extends OncePerRequestFilter {

    @Value("${auth.header}")
    private String HEADER;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenUtil tokenUtil;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
         String header=request.getHeader(HEADER);
        SecurityContext securityContext=SecurityContextHolder.getContext();
         if(header!=null&&securityContext.getAuthentication()==null){
          String token=header.substring("Bearer ".length());
          String username=tokenUtil.getUsernameFromToken(token);
          if(username!=null)
          {
              UserDetails userDetails=userService.loadUserByUsername(username);
              if(tokenUtil.isTokenValid(userDetails,token))
              {
                  UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                  authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                  SecurityContextHolder.getContext().setAuthentication(authentication);
              }
          }
         }
         filterChain.doFilter(request,response);
    }
}
