package com.ppsolution.ecovendas.service.authentication;

import com.ppsolution.ecovendas.model.AuthenticatedUser;
import com.ppsolution.ecovendas.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = repository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Credenciais invalidas"));
        return new AuthenticatedUser(user);
    }
}
