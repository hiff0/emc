package com.vladhiff.emc.gof.patterns.decorator;

// Например, нужно отправлять соотбещение администратору на почту, при успешной регистрации
public class EmailUserService implements UserService {
    private final UserServiceImp origin;
    private final EmailService emailService;

    public EmailUserService(UserServiceImp userService, EmailService emailService) {
        this.origin = userService;
        this.emailService = emailService;
    }

    @Override
    public void updateUser(Long id, String firstName, String lastName) {
        origin.updateUser(id, firstName, lastName);
//        emailService.notifyAdmin(new UserUpdatedEvent(id));
    }
}

