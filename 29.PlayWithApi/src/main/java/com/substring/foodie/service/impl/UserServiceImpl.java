package com.substring.foodie.service.impl;

import com.substring.foodie.dto_with_respect_to_entity.UserDto;
import com.substring.foodie.entity.User;
import com.substring.foodie.repository.UserRepo;
import com.substring.foodie.service.UserService;
import com.substring.foodie.utils.HelperId;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDto saveUser(UserDto userdto) {
        if (userRepo.findByEmail(userdto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists: " + userdto.getEmail());
        }
        userdto.setId(HelperId.generateRandomId());
        User u = convertUserDtoToUser(userdto);

        User savUser = userRepo.save(u);
        return convertUserToUserDto(savUser);

        // throw new UnsupportedOperationException("Unimplemented method 'saveUser'");
    }

    // entities and datatranfer seperate karrenge
    private User convertUserDtoToUser(UserDto ud) {
        User u = new User();
        u.setId(ud.getId());
        u.setName(ud.getName());
        u.setEmail(ud.getEmail());
        u.setAddress(ud.getAddress());
        u.setPhoneNumber(ud.getPhoneNumber());
        // u.setRole(null);
        return u;

    }

    // ulta convert karre abhi hum
    private UserDto convertUserToUserDto(User u) {
        UserDto ud = new UserDto();
        ud.setId(u.getId());
        ud.setName(u.getName());
        ud.setEmail(u.getEmail());
        ud.setAddress(u.getAddress());
        ud.setPhoneNumber(u.getPhoneNumber());
        return ud;

    }

    @Override
    public UserDto updateUser(User user, String userId) {
        // TODO Auto-generated method stub
        User user1 = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFound("user not found"));
        user1.setName(user.getName());
        user1.setEmail(user.getEmail());
        user1.setAddress(user.getAddress());
        user1.setPhoneNumber(user.getPhoneNumber());
        // ..all fields
        User save = userRepo.save(user1);
        return convertUserToUserDto(save);
        // throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> u = userRepo.findAll();
        List<UserDto> l = u.stream().

                map((User user) -> convertUserToUserDto(user))
                .toList();
        return l;
    }

    @Override
    public List<UserDto> getUserByName(String name) {
        return userRepo.findByName(name)
                .stream()
                .map(this::convertUserToUserDto)
                .toList();

    }

    @Override
    public UserDto getUserByEmail(String email) {
        User u = userRepo.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFound("user not found"));
        return convertUserToUserDto(u);
    }

    @Override
    public UserDto getUserById(String userId) {
        // TODO Auto-generated method stub
        User u = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFound("user not found"));
        // throw new UnsupportedOperationException("Unimplemented method
        // 'getUserById'");
        return convertUserToUserDto(u);
    }

    @Override
    public void deleteUserById(String userId) {
        User u = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFound("user not found"));
        userRepo.delete(u);
    }

    @Override
    public List<UserDto> searchUsers(String keyword) {
        List<User> u = userRepo.searchByKeyword(keyword);
        List<UserDto> l = u.stream().

                map((User user) -> convertUserToUserDto(user))
                .toList();
        return l;
    }

    @Override
    public Page<UserDto> getAllUsers(Pageable page) {
        //List<User> u = userRepo.findAll(page).getContent();
        Page<User> userPage = userRepo
                              .findAll(page);
        // List<UserDto> l = userPage
        //         .stream()
        //         .map((User user) -> convertUserToUserDto(user))
        //         .toList();

        //converting page of user to page of userdto
        return userPage.map(user-> convertUserToUserDto(user));
       // return userPage.map(this::convertUserToUserDto);
    }

    // private UserRepo userRepo;

    // public UserServiceImpl(UserRepo userRepo) {
    // this.userRepo = userRepo;
    // }

    // @Override

    // public User saveUser(User user) {
    // user.setId(UUID.randomUUID().toString());
    // User savedEntity = userRepo.save(user);
    // return savedEntity;
    // }

    // @Transactional
    // public User updateUser(User user, String userId) {
    // // get user
    // // usko ek naya restaurant add karnuga

    // User user1 = userRepo.findById(userId).orElseThrow(() -> new
    // RuntimeException("user not found"));
    // user1.setName(user.getName());
    // // ..all fields
    // User save = userRepo.save(user1);
    // return save;
    // }

    // @Override
    // public void testUserRole() {

    // User user = new User();
    // user.setId(UUID.randomUUID().toString());
    // user.setName("Vivek Ashok");
    // user.setEmail("vivek@gmail.com");
    // user.setAvailable(true);
    // user.setAddress("Testing address for many to many");
    // user.setPassword("abc");

    // //
    // RoleEntity entity1 = new RoleEntity();
    // entity1.setName("ROLE_ADMIN");

    // RoleEntity entity2 = new RoleEntity();
    // entity2.setName("ROLE_GUEST");

    // //link
    // //user ki taraf se
    // user.getRoleEntities().add(entity1);
    // user.getRoleEntities().add(entity2);

    // // entities
    // entity1.getUsers().add(user);
    // entity2.getUsers().add(user);

    // userRepo.save(user);

    // System.out.println("user saved:");

    // }

    // @Override
    // public User saveUser(UserDto userdto) {
    // // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method 'saveUser'");
    // }

    // @Override
    // public List<UserDto> getAllUsers() {
    // // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method
    // 'getAllUsers'");
    // }

    // @Override
    // public List<UserDto> getAllUserByName(String name) {
    // // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method
    // 'getAllUserByName'");
    // }

    // @Override
    // public List<UserDto> getAllUserByEmail(String email) {
    // // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method
    // 'getAllUserByEmail'");
    // }
}
