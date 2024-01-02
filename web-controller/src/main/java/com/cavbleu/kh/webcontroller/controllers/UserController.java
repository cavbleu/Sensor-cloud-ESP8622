package com.cavbleu.kh.webcontroller.controllers;

import com.cavbleu.kh.webcontroller.modelDTO.UserRequestDTO;
import com.cavbleu.kh.webcontroller.services.IKeycloackService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * @author cavbleu
 * @project cloud-sensor
 * @create 2023-12-31 08:50
 */

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private static class Active {
        private Boolean isEnabled;

        public Active(Boolean isEnabled) {
            this.isEnabled = isEnabled;
        }

        public Boolean getActive() {
            return isEnabled;
        }
    }

    private final IKeycloackService keycloakService;

    @GetMapping("/all")
    @RolesAllowed("admin")
    public String user(Principal principal, Model model) {
        List<UserRepresentation> userRepresentationList = keycloakService.viewRealmUser();
        model.addAttribute("username", principal.getName());
        model.addAttribute("userList", userRepresentationList);
        return "userList";
    }

    @GetMapping("/create")
    @RolesAllowed("admin")
    public String createUser(Model model, Principal principal) {
        Active active = new Active(false);
        model.addAttribute("username", principal.getName());
        model.addAttribute("newuser", new UserRequestDTO());
        return "create-user";
    }

    @PostMapping("/create")
    @RolesAllowed("admin")
    public String createUser(@ModelAttribute("newuser") UserRequestDTO userRequestDTO,
                             HttpServletRequest request) {
        keycloakService.addUser(userRequestDTO);
        return "redirect:/users/all?create_success";
    }

    @GetMapping("/delUser/{id}")
    @RolesAllowed("admin")
    public String delUser(@PathVariable("id") String id){
        keycloakService.deleteRealmUser(id);
        return "redirect:/users/all?delete_success";
    }

//    @GetMapping("/updPass/{id}")
//    @RolesAllowed("admin")
//    public String updPassUser(@PathVariable("id") String id,
//                              Principal principal, Model model){
//        String password = null;
//        UserRequestDTO userRequestDTO = new UserRequestDTO();
//        userRequestDTO.setId(id);
//        model.addAttribute("username", principal.getName());
//        model.addAttribute("updPassuser", userRequestDTO);
//        return "upd-userPass";
//    }
//
//    @PostMapping("/updPassword")
//    @RolesAllowed("admin")
//    public String updPasswordUser(@ModelAttribute("updPassuser") UserRequestDTO userRequestDTO,
//                             HttpServletRequest request) {
//        keycloakService.updateRealmPasswordReset(userRequestDTO.getId(),userRequestDTO.getPassword());
//        return "redirect:/users/all?updatePass_success";
//    }

    @GetMapping("/isActive/{id}")
    @RolesAllowed("admin")
    public String activeUser(@ModelAttribute("newuser") UserRequestDTO userRequestDTO,
                             @PathVariable("id") String id)
    {
        keycloakService.updateRealmIsEnabled(id);
        return "redirect:/users/all?active_success";
    }
}
