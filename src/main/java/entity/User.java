package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "appuser")
public class User {

    @Id
    public String email;

    public String name;

    public String password;

    public String roles;
}

