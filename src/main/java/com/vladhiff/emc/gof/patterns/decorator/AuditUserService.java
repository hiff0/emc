package com.vladhiff.emc.gof.patterns.decorator;

// Например, нужно отправлять отчет, если пользователь не обновлен
public class AuditUserService implements UserService {
    private final UserServiceImp origin;
    private final AuditService auditService;

    public AuditUserService(UserServiceImp origin, AuditService auditService) {
        this.origin = origin;
        this.auditService = auditService;
    }

    @Override
    public void updateUser(Long id, String firstName, String lastName) {
        try {
            origin.updateUser(id, firstName, lastName);
        }
        catch (RuntimeException e) {
            // Если проихошла ошибка, фиксируем информацию в системе аудита
//            auditService.auditFailedUserCreation(id, e);
//            throw e;
        }
    }
}
