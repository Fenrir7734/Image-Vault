package com.fenrir.core.feature.user;

import com.fenrir.core.domain.UserEntity;
import com.fenrir.core.feature.user.dto.MeResponse;
import com.fenrir.core.feature.user.dto.UserResponse;
import org.mapstruct.Mapper;

@Mapper
interface UserMapper {
    MeResponse mapToMeResponse(UserEntity user);
    UserResponse mapToUserResponse(UserEntity user);
}
