package br.com.wesleyeduardo.stockcontrol.validacao.security;

import br.com.wesleyeduardo.stockcontrol.model.User;
import br.com.wesleyeduardo.stockcontrol.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticateService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findByLogin(login);

        if(user.isPresent()){

            return user.get();
        }

        throw new UsernameNotFoundException("Dados Inválidos");
    }
}