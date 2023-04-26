package com.cardona.apiservlet.webapp.services;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.Optional;

public class LoginServiceCookieIMPL implements LoginService {

    @Override
    public Optional<String> getUsername(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies() != null ? req.getCookies() : new Cookie[0];
        Optional<String> optionalCookie;
        optionalCookie = Arrays.stream(cookies)
                .filter(c -> "username".equals(c.getName())).map(Cookie::getValue).findFirst();
        return optionalCookie;
    }
}
