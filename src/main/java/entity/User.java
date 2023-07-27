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
public class User extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQUENCE_GEN")
    @SequenceGenerator(name = "USER_SEQUENCE_GEN", sequenceName = "appuser_id_seq", allocationSize = 1)
    public Long id;

    public String email;

    public String name;

    public String password;

    public String roles;

    @Override
    public Long getId() {
        return id;
    }
}

