package util;

import dto.UserDTO;
import io.smallrye.jwt.build.Jwt;
import jakarta.inject.Singleton;

import java.util.HashSet;

@Singleton
public class TokenHelper {
    public String generateToken(UserDTO user){
        long accessTokenExpiration = 15 * 60;

        var roles = new HashSet<>(user.getRoles());
        return Jwt.issuer("some-issuer")
                .upn(user.email)
                .groups(roles)
                .claim("name", user.name)
                .expiresAt(currentTimeInSecs() + accessTokenExpiration)
                .sign();
    }

    private long currentTimeInSecs() {
        return System.currentTimeMillis() / 1000L;
    }
}
