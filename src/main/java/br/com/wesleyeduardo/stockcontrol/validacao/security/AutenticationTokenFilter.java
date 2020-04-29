package br.com.wesleyeduardo.stockcontrol.validacao.security;

import br.com.wesleyeduardo.stockcontrol.model.User;
import br.com.wesleyeduardo.stockcontrol.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AutenticationTokenFilter extends OncePerRequestFilter {

    private TokenService tokenService;
    private UserRepository userRepository;

    public AutenticationTokenFilter(TokenService tokenService, UserRepository repository) {
        this.tokenService = tokenService;
        this.userRepository = repository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = recoveryToken(request);

        boolean valid = tokenService.isValidToken(token);

        if(valid){
            autenticarCliente(token);
        }

        filterChain.doFilter(request,response);

    }

    private void autenticarCliente(String token) {

        Long idUser= tokenService.getIdUser(token);

        User user = userRepository.findById(idUser).get();

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String recoveryToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }

        return token.substring(7, token.length());
    }
}
