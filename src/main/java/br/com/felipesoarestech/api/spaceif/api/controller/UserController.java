package br.com.felipesoarestech.api.spaceif.api.controller;


import br.com.felipesoarestech.api.spaceif.domain.model.User;
import br.com.felipesoarestech.api.spaceif.domain.repository.UserRepository;
import br.com.felipesoarestech.api.spaceif.domain.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;
    //    @PostMapping("/login")
//    public ResponseEntity login(@RequestBody @Valid UserRequestDTO data){
//        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(),data.password());
//        try{
//            var auth = this.authenticationManager.authenticate(usernamePassword);
//
//            var token = tokenService.generateToken((User) auth.getPrincipal());
//
//            return ResponseEntity.ok(new LoginResponseDTO(token));
//
//        }catch (AuthenticationException e){
//            throw new UserPasswordNotExists("Usuário ou senha incorretos!!");
//        }
//
//    }
    @PatchMapping("/{userID}")
    public ResponseEntity<?> partialUpdate(@PathVariable Integer userID, @RequestBody Map<String, Object> fields){
        Optional<User> currentUser = userRepository.findById(userID);
        if (currentUser.isPresent()) {
            merge(fields, currentUser.get());
            return updateUser(userID, currentUser.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @PutMapping("/{userID}")
    public ResponseEntity<Object> updateUser(@PathVariable Integer userID, @RequestBody User user) {
        Optional<User> currentUser = userRepository.findById(userID);
        if (currentUser.isPresent()) {
            BeanUtils.copyProperties(user, currentUser.get(), "id");
            User saveUser = userRepository.save(currentUser.get());
            return ResponseEntity.status(HttpStatus.OK).body(saveUser);
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("test");
    }

    private void merge(Map<String, Object> sourceData, User targetUser) {
        ObjectMapper objectMapper = new ObjectMapper();
        User sourceUser = objectMapper.convertValue(sourceData, User.class);

        sourceData.forEach((nomePropriedade, valorPropriedade) -> {
            Field field = ReflectionUtils.findField(User.class, nomePropriedade);
            if (field != null) {
                field.setAccessible(true);
                Object newValue = ReflectionUtils.getField(field, sourceUser);
                if (newValue != null && field.getType() == byte[].class) {
                    newValue = ((String) newValue).getBytes(); // Ajuste conforme necessário
                }
                ReflectionUtils.setField(field, targetUser, newValue);
            }
        });
    }

}
