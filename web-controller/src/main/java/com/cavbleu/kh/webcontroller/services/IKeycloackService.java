package com.cavbleu.kh.webcontroller.services;

import com.cavbleu.kh.webcontroller.modelDTO.UserRequestDTO;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.List;

/**
 * @author cavbleu
 * @project cloud-sensor
 * @create 2024-01-01 20:59
 */

public interface IKeycloackService {

    public void addUser(UserRequestDTO dto);
    public List<UserRepresentation> viewRealmUser ();
    public void deleteRealmUser(String id);
    public void updateRealmIsEnabled(String id);
}
