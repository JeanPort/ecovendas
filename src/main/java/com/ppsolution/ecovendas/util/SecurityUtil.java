package com.ppsolution.ecovendas.util;

import com.ppsolution.ecovendas.exception.UnauthenticatedUser;
import com.ppsolution.ecovendas.model.AuthenticatedUser;
import com.ppsolution.ecovendas.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

    public static User getUserAuthenticated(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof AuthenticatedUser authUser)){
            throw new UnauthenticatedUser();
        }
        return authUser.getUser();
    }

    public static Long getIdUserAuthenticated(){
        var userAuthenticated = getUserAuthenticated();
        return userAuthenticated.getId();
    }
}
