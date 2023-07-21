package mapper;

import dto.UserDTO;
import entity.User;
import org.mapstruct.Mapper;

import java.util.Arrays;
import java.util.List;


@Mapper(componentModel = "jakarta")
public interface PersonMapper extends AbstractRepositoryMapper<User, UserDTO>{

    default List<String> toRoles(String roles) {
        return Arrays.asList(roles.split(";"));
    }

    default String toRoles(List<String> roles) {
        return String.join(";", roles);
    }
}
