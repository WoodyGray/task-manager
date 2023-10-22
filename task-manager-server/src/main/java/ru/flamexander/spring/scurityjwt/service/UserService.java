package ru.flamexander.spring.scurityjwt.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.flamexander.spring.scurityjwt.dto.RegistrationUserDto;
import ru.flamexander.spring.scurityjwt.dto.UpdateUserPasswordDto;
import ru.flamexander.spring.scurityjwt.entity.PersonalSubtask;
import ru.flamexander.spring.scurityjwt.entity.PersonalTask;
import ru.flamexander.spring.scurityjwt.entity.PublicTask;
import ru.flamexander.spring.scurityjwt.entity.User;
import ru.flamexander.spring.scurityjwt.exception.AppError;
import ru.flamexander.spring.scurityjwt.repository.RoleRepository;
import ru.flamexander.spring.scurityjwt.repository.UserRepository;
import ru.flamexander.spring.scurityjwt.util.JwtTokenUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenUtils jwtTokenUtils;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setJwtTokenUtils(JwtTokenUtils jwtTokenUtils) {
        this.jwtTokenUtils = jwtTokenUtils;
    }

    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }
    public User findByToken(String token){
        String username = jwtTokenUtils.getUsername(token);
        User user = findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("user with username: "
                        + username + " not found"));
        return user;
    }

    public ResponseEntity<?> findPublicTaskByToken(String token){
        User user = findByToken(token);
        List<PublicTask> publicTasks = user.getPublicTasks();
        if (publicTasks == null || publicTasks.isEmpty()){
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "public task list is null"),
                    HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(publicTasks);
    }
    public ResponseEntity<?> findPersonalTaskByToken(String token) {
        User user = findByToken(token);
        List<PublicTask> publicTasks = user.getPublicTasks();
        if (publicTasks == null || publicTasks.isEmpty()){
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "personal task list is null"),
                    HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(publicTasks);
    }

    public String updatePasswordByToken(String token, UpdateUserPasswordDto updateUserPasswordDto){
        if (!updateUserPasswordDto.getNewPassword().equals(updateUserPasswordDto.getConfirmNewPassword())){
            return "new password and confirm new password must match";
        }
        User user = findByToken(token);
        user.setPassword(passwordEncoder.encode(updateUserPasswordDto.getNewPassword()));
        userRepository.save(user);
        return "change of password success";
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("user with username: "
                + username + " not found"));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList())
        );
    }

    public User createNewUser(RegistrationUserDto registrationUserDto){
        User user = new User();
        user.setUsername(registrationUserDto.getUsername());
        user.setEmail(registrationUserDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationUserDto.getPassword()));
        user.setFullName(registrationUserDto.getFullName());
        user.setRoles(List.of(roleRepository.findByName("ROLE_USER").get()));
        return userRepository.save(user);
    }
}
