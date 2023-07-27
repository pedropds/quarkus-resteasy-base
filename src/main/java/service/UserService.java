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

    public UserDTO create(UserDTO user) {
        return repository.saveIfNotExists(user);
    }

    public UserDTO update(UserDTO user) {
        return repository.save(user);
    }

    public UserDTO findByEmail(String email) {
        return repository.findById(email);
    }

    public String login(UserDTO user) {
        var authenticatedUser = repository.authenticate(user.email, user.password);

        if(authenticatedUser == null)
            throw new BadRequestException("Invalid credentials");

        return tokenHelper.generateToken(authenticatedUser);
    }
}
