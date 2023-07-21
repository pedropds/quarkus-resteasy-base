package util;

import dto.UserDTO;
import io.smallrye.jwt.build.Jwt;
import jakarta.inject.Singleton;

import java.util.HashSet;

@Singleton
public class TokenHelper {
    public String generateToken(UserDTO user){
        var roles = new HashSet<>(user.getRoles());
        return Jwt.issuer("Issuer")
                .upn(user.email)
                .groups(roles)
                .claim("name", user.name)
                .sign();
    }
}
