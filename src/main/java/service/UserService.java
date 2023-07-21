package service;

import exception.BadRequestException;
import repository.UserRepository;
import util.TokenHelper;
import dto.UserDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class UserService {

    @Inject
    UserRepository repository;

    @Inject
    TokenHelper tokenHelper;

    public List<UserDTO> findAllDTO() {
        return repository.getAll();
    }

    public UserDTO findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public String authenticate(UserDTO user) {
        var authenticatedUser = authenticate(user.email, user.password);

        if(authenticatedUser == null)
            throw new BadRequestException("Invalid credentials");

        return tokenHelper.generateToken(authenticatedUser);
    }

    private UserDTO authenticate(String username, String password) {
        return repository.authenticate(username, password);
    }

}
