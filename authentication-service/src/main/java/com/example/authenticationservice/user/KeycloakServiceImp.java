package com.example.authenticationservice.user;

import com.example.authenticationservice.credential.KeycloakAccessToken;
import com.example.authenticationservice.entity.RoleKeyCloak;
import com.example.authenticationservice.entityDto.KeyCloakUserInfo;
import com.example.authenticationservice.retrofiet.RetrofietServiceGenerator;
import com.example.authenticationservice.retrofiet.RetrofietUserService;
import com.example.authenticationservice.util.KeycloakConstant;
import com.example.authenticationservice.util.Peggable;
import com.example.authenticationservice.util.Peggy;
import com.example.authenticationservice.util.Specifearcation;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.io.IOException;
import java.util.*;

@Service
@Log4j2
public class KeycloakServiceImp implements KeycloakService {

    public static final String LOGIN_FORM_CLIENT_ID_KEY = "client_id";
    public static final String LOGIN_FORM_USERNAME_KEY = "username";
    public static final String LOGIN_FORM_PASSWORD_KEY = "password";
    public static final String LOGIN_FORM_GRANT_TYPE_KEY = "grant_type";
    private static String adminToken;

    @Override
    public KeycloakAccessToken login(String username, String password) throws IOException {
        Map<String, String> params = new HashMap<>();
        params.put(LOGIN_FORM_CLIENT_ID_KEY, KeycloakConstant.KEYCLOAK_CLIENT_ID);
        params.put(LOGIN_FORM_USERNAME_KEY, username);
        params.put(LOGIN_FORM_PASSWORD_KEY, password);
        params.put(LOGIN_FORM_GRANT_TYPE_KEY, KeycloakConstant.KEYCLOAK_CREDENTIAL_GRANT_TYPE);
        RetrofietUserService service
                = RetrofietServiceGenerator.createService(RetrofietUserService.class);
        Response<KeycloakAccessToken> response = service.login(params).execute();
        KeycloakAccessToken accessToken = response.body();
        adminToken = accessToken.getAccess_token();
        if (response.isSuccessful()) {
            return response.body();
        }
        throw new IOException();
    }

    @Override
    public boolean save(KeycloakUser keycloakUser) throws IOException {
        prepareAdminToken();
        RetrofietUserService service
                = RetrofietServiceGenerator.createService(RetrofietUserService.class, adminToken);
        Response<Void> response
                = service.save(keycloakUser).execute();
        if (!response.isSuccessful()) {
            if (response.code() == HttpStatus.UNAUTHORIZED.value()
                    || response.code() == HttpStatus.FORBIDDEN.value()) {
                adminToken = null;
            }
            throw new IOException(response.message());
        }
        return true;
    }

    @Override
    public Peggy<KeycloakUser> findAll(Specifearcation specifearcation, Peggable pageable) throws IOException {
        prepareAdminToken();
        RetrofietUserService service
                = RetrofietServiceGenerator.createService(RetrofietUserService.class, adminToken);
        Response<List<KeycloakUser>> response
                = service.findAll().execute();
        if (!response.isSuccessful()) {
            if (response.code() == HttpStatus.UNAUTHORIZED.value()
                    || response.code() == HttpStatus.FORBIDDEN.value()) {
                adminToken = null;
            }
            throw new IOException(response.message());
        }
        return Peggy.<KeycloakUser>builder().content(response.body()).limit(10).page(1).build();
    }

    @Override
    public Optional<KeycloakUser> findById(String id) throws IOException {
        prepareAdminToken();

        RetrofietUserService service
                = RetrofietServiceGenerator.createService(RetrofietUserService.class, adminToken);
        Response<KeycloakUser> response
                = service.findById(id).execute();
        if (!response.isSuccessful()) {
            if (response.code() == HttpStatus.UNAUTHORIZED.value()
                    || response.code() == HttpStatus.FORBIDDEN.value()) {
                adminToken = null;
            }
            throw new IOException(response.message());
        }
        return Optional.ofNullable(response.body());
    }

    @Override
    public boolean update(String id, KeycloakUser updateKeycloakUser) throws IOException {
        prepareAdminToken();
        RetrofietUserService service
                = RetrofietServiceGenerator.createService(RetrofietUserService.class, adminToken);
        Response<Void> response
                = service.update(id, updateKeycloakUser).execute();
        if (!response.isSuccessful()) {
            if (response.code() == HttpStatus.UNAUTHORIZED.value()
                    || response.code() == HttpStatus.FORBIDDEN.value()) {
                adminToken = null;
            }
            throw new IOException(response.message());
        }
        return true;
    }

    @Override
    public boolean delete(String id) throws IOException {
        prepareAdminToken();
        RetrofietUserService service
                = RetrofietServiceGenerator.createService(RetrofietUserService.class, adminToken);
        Response<Void> response
                = service.delete(id).execute();
        if (!response.isSuccessful()) {
            if (response.code() == HttpStatus.UNAUTHORIZED.value()
                    || response.code() == HttpStatus.FORBIDDEN.value()) {
                adminToken = null;
            }
            throw new IOException(response.message());
        }
        return true;
    }

    @Override
    public Optional<KeyCloakUserInfo> userInfo() throws IOException {
        RetrofietUserService service
                = RetrofietServiceGenerator.createService(RetrofietUserService.class,adminToken);
        Response<KeyCloakUserInfo> response
                = service.getUserInfo().execute();
        /*KeyCloakUserInfo keyCloakUserInfo = response.body();
        System.out.println(keyCloakUserInfo.toString());*/
        if (!response.isSuccessful()) {
            if (response.code() == HttpStatus.UNAUTHORIZED.value()
                    || response.code() == HttpStatus.FORBIDDEN.value()) {
                adminToken = null;
            }
            throw new IOException(response.message());
        }
        return Optional.ofNullable(response.body());
    }

    @Override
    public boolean addRoleUser(String id, RoleKeyCloak role) throws IOException {
        prepareAdminToken();
        RetrofietUserService service
                = RetrofietServiceGenerator.createService(RetrofietUserService.class,adminToken);
        System.out.println("Chay den day r");
        Set<RoleKeyCloak> listRole = new HashSet<>();
        listRole.add(role);
        Response<Void> response =service.addRoleUser(id,listRole ).execute();
        System.out.println(response);
        if (response.isSuccessful()){
            return true;
        }
        return false;
    }

    @Override
    public void prepareAdminToken() throws IOException {
        log.info("Token:" + adminToken);
        if (adminToken != null && adminToken.length() > 0) {
            return;
        }
        KeycloakAccessToken token
                = login(KeycloakConstant.KEYCLOAK_ADMIN_USERNAME, KeycloakConstant.KEYCLOAK_ADMIN_PASSWORD);
        if (token == null) {
            throw new IOException();
        }
        adminToken = token.getAccess_token();

    }
}
