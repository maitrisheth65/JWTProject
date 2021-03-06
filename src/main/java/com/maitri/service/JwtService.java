package com.maitri.service;

import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.maitri.dao.UserDao;
import com.maitri.model.JwtRequest;
import com.maitri.model.JwtResponse;
import com.maitri.model.User;
import com.maitri.util.JwtUtil;
/*
 * This class generates JWT token and implement other methods.
 */
@Service
public class JwtService implements UserDetailsService {
	//Creating Objects of different classes.
	@Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserDao userDao;
    public User user1;
    public String userRole;
    @Autowired
    private AuthenticationManager authenticationManager;
    //Authenticate userDetails through database and generate the JWT token.
    public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
        String userName = jwtRequest.getUserName();
        String userPassword = jwtRequest.getUserPassword();
        authenticate(userName, userPassword);
        UserDetails userDetails = loadUserByUsername(userName);
        String newGeneratedToken = jwtUtil.generateToken(userDetails);
        com.maitri.model.User user=userDao.findById(userName).get();
        return new JwtResponse(user, newGeneratedToken);
    }
    //Check weather particular user exists or not.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.maitri.model.User user = userDao.findById(username).get();

        if (user != null) {
            return new org.springframework.security.core.userdetails.User(
                    user.getUserName(),
                    user.getUserPassword(),
                    getAuthority(user)
            );
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
    //Check Role of logged in user.(Admin or User)
    public Set getAuthority(com.maitri.model.User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRole().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        });
        return authorities;
    }
   //Authenticate the user through database.
   private void authenticate(String userName, String userPassword) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
