package com.fenrir.auth.init;

import com.fenrir.auth.entity.UserEntity;
import com.fenrir.auth.enums.Role;
import com.fenrir.auth.repository.StartupRepository;
import com.fenrir.auth.repository.RoleRepository;
import com.fenrir.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AppInitializer implements CommandLineRunner {
    private static final String AUTH_TYPE_STANDARD = "STANDARD";
    private static final String ADMIN_CREATED = "ADMIN_CREATED";

    private final StartupRepository startupRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        startupRepository.findById(ADMIN_CREATED).ifPresent(config -> {
            boolean adminExists = Boolean.parseBoolean(config.getValue());
            if (adminExists) {
                return;
            }
            userRepository.saveAndFlush(createAdmin());

            config.setValue(String.valueOf(true));
            startupRepository.saveAndFlush(config);
        });
    }

    private UserEntity createAdmin() {
        UserEntity user = new UserEntity();
        user.setAuthType(AUTH_TYPE_STANDARD);
        user.setName("admin");
        user.setEmail("admin@admin.com");
        user.setPassword(passwordEncoder.encode("admin"));
        user.setRole(roleRepository.getByName(Role.ADMIN.getName()));
        user.setVerified(true);
        return user;
    }
}
