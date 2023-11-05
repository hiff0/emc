package com.vladhiff.emc.gof.patterns.decorator;

public class UserServiceImp implements UserService {
    @Override
    public void updateUser(Long id, String firstName, String lastName) {
        // Обновляем пользавателя
//        User user = userRepository.findById(id)
//                .orElseThrow(() -> new NoSuchElementException("No User with ID=" + id));
//        user.update(firstName, lastName);
    }
}

