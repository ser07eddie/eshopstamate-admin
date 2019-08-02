package ro.edward.admin;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SecuritySession {

    boolean isLogged;

    public void markUserLogged() {
        isLogged = true;
    }

    public boolean isUserLogged() {
        return isLogged;
    }

    public boolean userNotLogged() {
        return isLogged = false;
    }
}
