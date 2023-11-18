package com.fenrir.auth.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@DynamicInsert
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue(generator = "user_id_seq_gen")
    @SequenceGenerator(name = "user_id_seq_gen",
            sequenceName = "users_id_seq",
            allocationSize = 1)
    private Long id;

    @Column(columnDefinition = "bpchar",
            insertable = false,
            updatable = false)
    private String code;
    private String authType;
    private String externalId;
    private String externalName;
    private String name;
    private String email;
    private String password;

    @Column(nullable = false)
    private Boolean verified;

    @Column(nullable = false)
    private Boolean enabled;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

    @ManyToOne
    private RoleEntity role;

    @PrePersist
    @PreUpdate
    private void setDefaults() {
        if (verified == null) setVerified(false);
        if (enabled == null) setEnabled(true);
    }
}
