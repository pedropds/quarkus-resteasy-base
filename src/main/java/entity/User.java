package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import repository.BaseEntity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "appuser")
public class User extends BaseEntity<String> {

    @Id
    public String email;

    public String name;

    public String password;

    public String roles;

    @Override
    public String getId() {
        return email;
    }
}

