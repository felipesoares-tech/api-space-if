package br.com.felipesoarestech.api.spaceif.domain.util;

import br.com.felipesoarestech.api.spaceif.domain.exception.NotAllowedException;
import br.com.felipesoarestech.api.spaceif.domain.model.User;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.IOException;
import java.util.Base64;

public class SecurityUtil{
    public static User getAuthenticatedUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userDetails instanceof User) {
            return ((User) userDetails);
        }
        throw new IllegalStateException("Authenticated user is not of type User");
    }

    public static boolean idVerify(Integer userID){
        boolean allowed = getAuthenticatedUser().getId().equals(userID);
        if (!allowed){
            throw new NotAllowedException("Operação Não Permitida !!");
        }
        return allowed;
    }
}