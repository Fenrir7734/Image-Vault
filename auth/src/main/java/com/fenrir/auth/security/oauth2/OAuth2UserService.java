package com.fenrir.auth.security.oauth2;

import com.fenrir.auth.entity.RoleEntity;
import com.fenrir.auth.entity.UserEntity;
import com.fenrir.auth.enums.Role;
import com.fenrir.auth.exception.exceptions.DisabledException;
import com.fenrir.auth.exception.exceptions.OAuth2AuthenticationProcessingException;
import com.fenrir.auth.exception.exceptions.UnverifiedException;
import com.fenrir.auth.exception.message.ErrorCode;
import com.fenrir.auth.repository.RoleRepository;
import com.fenrir.auth.repository.UserRepository;
import com.fenrir.auth.security.oauth2.user.OAuth2UserInfo;
import com.fenrir.auth.security.oauth2.user.OAuth2UserInfoFactory;
import com.fenrir.auth.security.oauth2.user.UserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
public class OAuth2UserService extends DefaultOAuth2UserService {
    private static final String UNVERIFIED_EXCEPTION_MESSAGE = "Account is unverified";
    private static final String DISABLED_EXCEPTION_MESSAGE = "Account is disabled";
    private static final String EMAIL_ALREADY_IN_USE = "Email is already in use";

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        return processOAuth2User(userRequest, oAuth2User);
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        OAuth2UserInfo oAuth2UserInfo = toOAuth2UserInfo(oAuth2UserRequest, oAuth2User);
        UserEntity user = userRepository.findByEmail(oAuth2UserInfo.getEmail())
                .map(u -> checkProvider(u, oAuth2UserInfo))
                .orElseGet(() -> registerUser(oAuth2UserInfo));
        Map<String, Object> attributes = oAuth2User.getAttributes();
        UserPrincipal userPrincipal = UserPrincipal.create(user, attributes);

        assertAccountVerified(userPrincipal);
        assertAccountEnabled(userPrincipal);

        return userPrincipal;
    }

    private UserEntity checkProvider(UserEntity user, OAuth2UserInfo oAuth2UserInfo) {
        String oauth2Provider = oAuth2UserInfo.getProvider().name();
        String userAuthType = user.getAuthType();

        if (!oauth2Provider.equals(userAuthType)) {
            throw new OAuth2AuthenticationProcessingException(
                    EMAIL_ALREADY_IN_USE, ErrorCode.EMAIL_USED_WITH_DIFFERENT_OAUTH_PROVIDER_ERROR);
        }
        return user;
    }

    private OAuth2UserInfo toOAuth2UserInfo(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        String registrationId = oAuth2UserRequest.getClientRegistration().getRegistrationId();
        Map<String, Object> attributes = oAuth2User.getAttributes();
        return toOAuth2UserInfo(registrationId, attributes);
    }

    private OAuth2UserInfo toOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        try {
            return OAuth2UserInfoFactory.getOAuth2UserInfo(registrationId, attributes);
        } catch (OAuth2AuthenticationProcessingException e) {
            log.warn(String.format("Provider = %s. %s", registrationId, e.getMessage()));
            throw e;
        }
    }

    private UserEntity registerUser(OAuth2UserInfo oAuth2User) {
        UserEntity user = new UserEntity();
        user.setAuthType(oAuth2User.getProvider().name());
        user.setExternalId(oAuth2User.getId());
        user.setExternalName(oAuth2User.getName());
        user.setEmail(oAuth2User.getEmail());
        user.setVerified(false);
        user.setRole(getUserRole());
        return userRepository.saveAndFlush(user);
    }

    private RoleEntity getUserRole() {
        return roleRepository.getByName(Role.VIEWER.getName());
    }

    private void assertAccountVerified(UserPrincipal userPrincipal) {
        if (!userPrincipal.isVerified()) {
            throw new UnverifiedException(UNVERIFIED_EXCEPTION_MESSAGE, ErrorCode.ACCOUNT_UNVERIFIED_ERROR);
        }
    }

    private void assertAccountEnabled(UserPrincipal userPrincipal) {
        if (!userPrincipal.isEnabled()) {
            throw new DisabledException(DISABLED_EXCEPTION_MESSAGE, ErrorCode.ACCOUNT_DISABLED_ERROR);
        }
    }
}
