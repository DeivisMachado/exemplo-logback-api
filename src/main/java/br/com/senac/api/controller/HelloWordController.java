package br.com.senac.api.controller;

import br.com.senac.api.entitys.Usuarios;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/helloWord")
public class HelloWordController {

    Logger logger = LoggerFactory.getLogger(HelloWordController.class);

    @PreAuthorize("@rolesService.validarPermissoes('ADMIN')")
    @GetMapping("/teste")
    public ResponseEntity<String> teste (@RequestParam int t1){
        logger.info("T1: {} T2: {}", t1, 2);

        Usuarios usuarioContext = (Usuarios) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        logger.info("Login: {}, Senha: {}", usuarioContext.getLogin(),
                usuarioContext.getSenha());

        return ResponseEntity.ok("Olá Mundo");
    }
}
