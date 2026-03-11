package com.substring.foodie.controller;

import com.substring.foodie.dto_with_respect_to_entity.UserDto;
import com.substring.foodie.service.UserService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




@RestController
@RequestMapping("/api/version1/users")
public class UserController {
//conmtroller req ascept and service tak leke jayege
private UserService uS;

public UserController(UserService uS) {
    this.uS = uS;
}
//crete user
//response code bhi bhejna hai
@PostMapping
public ResponseEntity<UserDto> create(@RequestBody UserDto ud) {
    //TODO: process POST request
    UserDto userDtoResult = uS.saveUser(ud);
    //return ResponseEntity.ok(userDtoResult); //201 status code aa jayega
    //return new ResponseEntity<>(userDtoResult,HttpStatus.CREATED);
    return ResponseEntity.status(HttpStatus.CREATED).body(userDtoResult);
    //response enetity of status and body dono bhej sakte hai
}
//get all users
// @GetMapping
// public ResponseEntity<List<UserDto>> getAllUsers() {
//   //  Pageable page = Pageable.; // or you can create a pageable object with specific page number and size
//    return ResponseEntity.ok(uS.getAllUsers());
//     //return new ResponseEntity<>(uS.getAllUsers(),HttpStatus.OK);
//     //return ResponseEntity.status(HttpStatus.OK).body(uS.getAllUsers());
// }// yaha hamne service se data leke response entity me body me daal ke bhej diya hai
@GetMapping
public ResponseEntity<Page<UserDto>> getAllPage(@RequestParam(value="page",required = false,defaultValue = "0") int page,
                                                @RequestParam(value="size",required = false,defaultValue = "10") int size,
                                            @RequestParam(value="sortBy",required = false,defaultValue = "createdDate") String sortBy,
                                            @RequestParam(value="sortDir",required = false,defaultValue = "desc") String sortDir) {

    Sort s = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
    Pageable pageable = PageRequest.of(page, size, s);
    return ResponseEntity.ok(uS.getAllUsers(pageable));
         // Example: page size of 10 and page number 0
   
}

//get user by id
@GetMapping("/{userId}")
public ResponseEntity<UserDto> findUserById(@PathVariable("userId") String userId) {
    return ResponseEntity.ok(uS.getUserById(userId));
    //return new ResponseEntity<>(uS.getUserById(userId),HttpStatus.OK);
    //return ResponseEntity.status(HttpStatus.OK).body(uS.getUserById(userId));
//note : - yaha hamne request param se user id leke service me bhej diya hai aur service se data leke response entity me body me daal ke bhej diya hai
}

}
