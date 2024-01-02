package com.cavbleu.kh.webcontroller.modelDTO;

import lombok.Data;

/**
 * @author cavbleu
 * @project cloud-sensor
 * @create 2023-12-31 08:51
 */

@Data
public class UserRequestDTO {
    private String id;
    private String username;
    private String firstname;
    private String lastname;
    private Boolean isEnabled;
    private String password;
    private String role;
}