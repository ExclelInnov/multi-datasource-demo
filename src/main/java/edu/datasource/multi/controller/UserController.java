package edu.datasource.multi.controller;

import edu.datasource.multi.model.AppUser;
import edu.datasource.multi.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static edu.datasource.multi.util.CommonUtils.isNotValidString;
import static edu.datasource.multi.util.Constants.INVALID_USER_DETAILS;

@RestController
@RequestMapping("/v1/user")
@AllArgsConstructor
@Slf4j
public class UserController {

    private UserService userService;

    @GetMapping("/{userName}")
    public ResponseEntity<?> getUserWithId(@PathVariable("userName") String userName) {
        log.info("UserController - getUserWithId, start");

        if(isNotValidString(userName)) {
            log.info("UserController - getUserWithId, userName is invalid");
            return ResponseEntity
                    .badRequest()
                    .body(INVALID_USER_DETAILS);
        }

        log.info("UserController - getUserWithId, end");
        return ResponseEntity.ok(userService.getUserDetails(userName));
    }

    @PostMapping("/new")
    public ResponseEntity<?> registerUser(@RequestBody final AppUser newUser) {
        log.info("UserController - createNewUser, start");

        if(newUser == null ||
                (isNotValidString(newUser.getUserName()) || newUser.getAge() <= 0 || isNotValidString(newUser.getPwd()))) {
            log.info("UserController - createNewUser, invalid user details: {}", newUser.toString());
            return ResponseEntity
                    .badRequest()
                    .body(INVALID_USER_DETAILS);
        }

        log.info("UserController - createNewUser, end");
        return ResponseEntity.ok(userService.validateAndCreateUser(newUser));
    }
}
