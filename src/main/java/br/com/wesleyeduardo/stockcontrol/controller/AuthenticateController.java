package br.com.wesleyeduardo.stockcontrol.controller;

import br.com.wesleyeduardo.stockcontrol.dto.TokenDto;
import br.com.wesleyeduardo.stockcontrol.form.LoginForm;
import br.com.wesleyeduardo.stockcontrol.validacao.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticateController {


        @Autowired
        private AuthenticationManager authManager;

        @Autowired
        private TokenService tokenService;

        @PostMapping
        public ResponseEntity<TokenDto> authenticate(@RequestBody @Valid LoginForm form){

            UsernamePasswordAuthenticationToken loginData = form.converter();

            try {

                Authentication authentication =  authManager.authenticate(loginData);

                String token = tokenService.generateToken(authentication);

                return  ResponseEntity.ok(new TokenDto(token,"Bearer"));

            }catch (AuthenticationException e){
                    return  ResponseEntity.badRequest().build();
            }


        }

}
