package edu.datasource.multi.service;

import edu.datasource.multi.model.AppUser;
import edu.datasource.multi.repositories.ReplicaUserRepository;
import edu.datasource.multi.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {

    private UserRepository userRepo;
    private ReplicaUserRepository readUserRepo;

    public AppUser getUserDetails(final String userName) {
        Optional<AppUser> appUser = readUserRepo.findById(userName);
        return Optional
                .ofNullable(appUser)
                .get()
                .orElseThrow(() -> new RuntimeException("No user with given user name exists"));
    }

    public AppUser validateAndCreateUser(final AppUser newUser) {
        log.info("UserService - validateAndCreateUser, start");
        if(isUserAlreadyRegistered(newUser.getUserName())) {
            log.info("UserService - validateAndCreateUser, user already registered");
            return null;
        }

        try {
            return userRepo.save(newUser);
        } catch (Exception savingError) {
            log.info("UserService - validateAndCreateUser, error while registering the user: {}",
                    savingError.getMessage());
        }
        log.info("UserService - validateAndCreateUser, end");
        return null;
    }

    private boolean isUserAlreadyRegistered(final String checkUserName) {
        return readUserRepo.findById(checkUserName).isPresent();
    }
}
