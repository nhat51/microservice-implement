package com.example.authenticationservice.user;

import com.example.authenticationservice.credential.KeycloakAccessToken;
import com.example.authenticationservice.entity.RoleKeyCloak;
import com.example.authenticationservice.entityDto.KeyCloakUserInfo;
import com.example.authenticationservice.util.Peggable;
import com.example.authenticationservice.util.Peggy;
import com.example.authenticationservice.util.Specifearcation;

import java.io.IOException;
import java.util.Optional;

/*
 * Call keycloak rest api for admin to this. No way database.
 */
public interface KeycloakService {
    KeycloakAccessToken login(String username, String password) throws IOException;
    void prepareAdminToken() throws IOException;
    boolean save(KeycloakUser keycloakUser) throws IOException;
    Peggy<KeycloakUser> findAll(Specifearcation specifearcation, Peggable pageable) throws IOException;
    Optional<KeycloakUser> findById(String id) throws IOException;
    boolean update(String id, KeycloakUser updateKeycloakUser) throws IOException;
    boolean delete(String id) throws IOException; // to soft delete, move to update.
    Optional<KeyCloakUserInfo> userInfo() throws IOException;
    boolean addRoleUser(String userId, RoleKeyCloak role) throws IOException;
}
