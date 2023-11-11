package com.fenrir.auth.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import java.util.Set;

@Entity
@Table(name = "roles")
@Immutable
@NoArgsConstructor
@Getter
public class RoleEntity {
    @Id
    private Long id;
    private String name;

    @OneToMany(mappedBy = "role")
    private Set<UserEntity> user;
}
