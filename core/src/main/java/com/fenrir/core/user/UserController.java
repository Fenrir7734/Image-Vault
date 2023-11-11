package com.fenrir.core.user;

import com.fenrir.core.user.dto.MeResponse;
import com.fenrir.core.user.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
class UserController {
    private final UserService userService;

    @GetMapping("/me")
    ResponseEntity<MeResponse> getMe() {
        return ResponseEntity.ok(userService.getAuthorizedUser());
    }

    @GetMapping("/{id}")
    ResponseEntity<UserResponse> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }
}
