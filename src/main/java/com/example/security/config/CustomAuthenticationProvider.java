package com.example.security.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final CustomUserDetailsManager customUserDetailsManager;

    private final PasswordEncoder passwordEncoder;

    public CustomAuthenticationProvider(CustomUserDetailsManager customUserDetailsManager, PasswordEncoder passwordEncoder) {
        this.customUserDetailsManager = customUserDetailsManager;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * @param authentication the authentication request object.
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserDetails userDetails = customUserDetailsManager.loadUserByUsername(username);

        if(!this.passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid credentials!");
        } else {
            return new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
        }
    }

    /**
     * @param authentication
     * @return
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
