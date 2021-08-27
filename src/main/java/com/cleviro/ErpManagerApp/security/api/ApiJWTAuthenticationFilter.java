package com.cleviro.ErpManagerApp.security.api;

import com.cleviro.ErpManagerApp.model.masters.Department;
import com.cleviro.ErpManagerApp.model.masters.Location;
import com.cleviro.ErpManagerApp.model.masters.Store;
import com.cleviro.ErpManagerApp.model.people.User;
import com.cleviro.ErpManagerApp.service.people.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static com.cleviro.ErpManagerApp.security.SecurityConstants.*;

/**
 * Created by Julius kimathi.
 */
public class ApiJWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public ApiJWTAuthenticationFilter(AuthenticationManager authenticationManager, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/api/v1/users/auth/login", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            User user = new ObjectMapper().readValue(req.getInputStream(), User.class);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getEmail(),
                            user.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        Map<String, Object> data = new HashMap<>();
        data.put(
                "timestamp",
                Calendar.getInstance().getTime());
        data.put(
                "errors",
                failed.getMessage());

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        new ObjectMapper().writeValue(response.getOutputStream(), data);
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        if (auth.getPrincipal() != null) {
            org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) auth.getPrincipal();
            String login = user.getUsername();
            Date expiresAt = new Date(System.currentTimeMillis() + EXPIRATION_TIME);
            if (login != null && login.length() > 0) {
                Claims claims = Jwts.claims().setSubject(login);
                List<String> roles = new ArrayList<>();
                user.getAuthorities().stream().forEach(authority -> roles.add(authority.getAuthority()));
                claims.put("roles", roles);
                String token = Jwts.builder()
                        .setClaims(claims)
                        .setExpiration(expiresAt)
                        .signWith(SignatureAlgorithm.HS512, SECRET)
                        .compact();
                User applicationUser = userService.findApplicationUserByEmail(login);
                Set<Location> locations = new HashSet<>(applicationUser.getLocations());
                Set<Store> stores = new HashSet<>(applicationUser.getStores());
                Set<Department> departments = new HashSet<>(applicationUser.getDepartments());

                Map<String, Object> tokens = new HashMap<>();
                tokens.put("access_token", TOKEN_PREFIX + token);
                tokens.put("expiresAt", expiresAt);
                tokens.put("Name", applicationUser.getFullName());
                tokens.put("email", login);
                tokens.put("userType", applicationUser.getUserType());
                tokens.put("roles", roles);
                tokens.put("locations", locations);
                tokens.put("stores", stores);
                tokens.put("departments", departments);

                res.setContentType("application/json");
                res.setCharacterEncoding("UTF-8");
                new ObjectMapper().writeValue(res.getOutputStream(), tokens);
            }
        }
    }
}
