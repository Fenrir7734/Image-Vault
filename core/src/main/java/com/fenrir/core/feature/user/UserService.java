package com.fenrir.core.feature.user;

import com.fenrir.core.common.exception.exceptions.NotFoundException;
import com.fenrir.core.domain.UserEntity;
import com.fenrir.core.repository.UserRepository;
import com.fenrir.core.common.security.AuthenticationFacade;
import com.fenrir.core.feature.user.dto.MeResponse;
import com.fenrir.core.feature.user.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class UserService {
    private final UserRepository userRepository;
    private final AuthenticationFacade authenticationFacade;
    private final UserMapper userMapper;

    public MeResponse getAuthorizedUser() {
        return userMapper.mapToMeResponse(getUser(authenticationFacade.getUserId()));
    }

    public UserResponse getUserById(Long id) {
        return userMapper.mapToUserResponse(getUser(id));
    }

    private UserEntity getUser(Long id) {
        return userRepository.findOne(id)
                .orElseThrow(NotFoundException::new);
    }
}
