package util;

import dto.UserDTO;
import io.smallrye.jwt.build.Jwt;
import jakarta.inject.Singleton;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.HashSet;

@Singleton
public class TokenHelper {
    @ConfigProperty(name = "mp.jwt.verify.issuer")
    private String issuer;

    @ConfigProperty(name = "quarkus-resteasy-base.token.expiration")
    private Long accessTokenExpiration;

    private static final String NAME = "name";

    public String generateToken(UserDTO user){
        var roles = new HashSet<>(user.getRoles());
        return Jwt.issuer(issuer)
                .upn(user.email)
                .groups(roles)
                .claim(NAME, user.name)
                .expiresAt(currentTimeInSecs() + accessTokenExpiration)
                .sign();
    }

    private long currentTimeInSecs() {
        return System.currentTimeMillis() / 1000L;
    }
}
