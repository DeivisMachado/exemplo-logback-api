package br.com.senac.api.useCases.roles;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service("rolesService")
public class RolesService {
    public boolean validarPermissoes(String permissoes){
        boolean autorizado = false;

        var authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        var permissoesUsuario = authentication.getAuthorities();

        for(GrantedAuthority permissao : permissoesUsuario){
            if(permissoes.contains(permissao.getAuthority()) ||
                    permissao.getAuthority().equals("ADMIN")){
                autorizado = true;
            }
        }

        return autorizado;
    }
}