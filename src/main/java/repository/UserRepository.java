package repository;

import dto.UserDTO;
import entity.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import mapper.AbstractRepositoryMapper;
import mapper.PersonMapper;

@ApplicationScoped
public class UserRepository extends AbstractRepository<User, UserDTO> {

    @Inject
    PersonMapper mapper;

    @Override
    AbstractRepositoryMapper<User, UserDTO> getMapper() {
        return mapper;
    }

    public UserDTO findByEmail(String email) {
        var user = find("email", email).firstResult();
        return user != null ? mapper.toDTO(user) : null;
    }

    public UserDTO authenticate(String email, String password) {
        User user = find("email", email).firstResult();
        if (user != null && user.getPassword().equals(password)) {
            return mapper.toDTO(user);
        }
        return null;
    }
}
