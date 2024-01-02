package com.cavbleu.kh.webcontroller.services.impl;

import com.cavbleu.kh.webcontroller.modelDTO.UserRequestDTO;
import com.cavbleu.kh.webcontroller.services.IKeycloackService;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.RoleMappingResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames.PASSWORD;

/**
 * @author cavbleu
 * @project cloud-sensor
 * @create 2023-12-31 08:51
 */

@Service
@RequiredArgsConstructor
public class KeycloakService implements IKeycloackService {


    @Autowired
    private Keycloak keycloak;

    @Value("${keycloak.realm}")
    private String realm;

    @Override
    public void addUser(UserRequestDTO dto) {
        String username = dto.getUsername();
        CredentialRepresentation credential = createPasswordCredentials(dto.getPassword());
        UserRepresentation user = new UserRepresentation();
        user.setUsername(username);
        user.setCredentials(Collections.singletonList(credential));
        user.setEnabled(dto.getIsEnabled());
        user.setFirstName(dto.getFirstname());
        user.setLastName(dto.getLastname());
        UsersResource usersResource = getUsersResource();
        usersResource.create(user);
        addRealmRoleToUser(username, dto.getRole());
    }


    private void addRealmRoleToUser(String userName, String roleName) {
        RealmResource realmResource = keycloak.realm(realm);
        List<UserRepresentation> users = realmResource.users().search(userName);
        UserResource userResource = realmResource.users().get(users.get(0).getId());
        RoleRepresentation role = realmResource.roles().get(roleName).toRepresentation();
        RoleMappingResource roleMappingResource = userResource.roles();
        roleMappingResource.realmLevel().add(Collections.singletonList(role));
    }

    @Override
    public List<UserRepresentation> viewRealmUser ()
    {
        return keycloak.realm(realm).roles().get("user").getUserMembers();
    }

    @Override
    public void deleteRealmUser(String id)
    {
        keycloak.realm(realm).users().delete(id);
    }


    private UsersResource getUsersResource() {
        return keycloak.realm(realm).users();
    }

    private static CredentialRepresentation createPasswordCredentials(String password) {
        CredentialRepresentation passwordCredentials = new CredentialRepresentation();
        passwordCredentials.setTemporary(false);
        passwordCredentials.setType(CredentialRepresentation.PASSWORD);
        passwordCredentials.setValue(password);
        return passwordCredentials;
    }

    @Override
    public void updateRealmIsEnabled(String id)
    {
            UserResource userResource = keycloak.realm(realm).users().get(id);
            UserRepresentation user = userResource.toRepresentation();
            user.setEnabled(!user.isEnabled());
            userResource.update(user);
    }

}
