package com.example.app.modules.security;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.app.modules.providers.JWTProvider;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

  @Autowired
  private JWTProvider jwtOrovider;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
      SecurityContextHolder.getContext().setAuthentication(null);
      String header = request.getHeader("Authorization");
      if(header != null){
        var subjectToken = this.jwtOrovider.validateToken(header);
        if(subjectToken.isEmpty()){
          response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
          return;
        }
        request.setAttribute("company_id", response);
             UsernamePasswordAuthenticationToken auth = 
             new UsernamePasswordAuthenticationToken(subjectToken, null,
            Collections.emptyList());
            SecurityContextHolder.getContext().setAuthentication(auth);

            
      }
      filterChain.doFilter(request, response);
  }

  
}


