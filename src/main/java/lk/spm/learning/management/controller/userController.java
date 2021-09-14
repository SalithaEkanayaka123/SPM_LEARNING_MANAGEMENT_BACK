package lk.spm.learning.management.controller;

import lk.spm.learning.management.model.User;
import lk.spm.learning.management.model.loginUser;
import lk.spm.learning.management.repository.loginUserRepository;
import lk.spm.learning.management.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
@RestController
public class userController {
    @Autowired
    private userRepository userRepository;

    @Autowired
    private loginUserRepository loginUserRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers(){
        List<User> users = loginUserRepository.getUserList();
        if(users.size() > 0){
            return new ResponseEntity<List<User>>(users, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No User Available", HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/students")
    public ResponseEntity<?> getAllStudents(){
        List<User> users = loginUserRepository.getStudentList();
        try {
            return new ResponseEntity<>(users , HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage() , HttpStatus.OK);
        }
    }

    @GetMapping("/teachers")
    public ResponseEntity<?> getAllTeachers(){
        List<User> users = loginUserRepository.getTeacherList();
        try {
            return new ResponseEntity<>(users , HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage() , HttpStatus.OK);
        }
    }


    @GetMapping("/teachercount")
    public ResponseEntity<?> getTeacherCount(){
        List<User> users = loginUserRepository.getTeacherList();
        return new ResponseEntity<>(users.size(), HttpStatus.OK);
    }

    @GetMapping("/studentcount")
    public ResponseEntity<?> getStudentCount(){
        List<User> users = loginUserRepository.getStudentList();
        return new ResponseEntity<>(users.size(), HttpStatus.OK);
    }


    //Add user
    @PostMapping("/useradd")
    public ResponseEntity<?> createUser( @RequestBody User user) {
        try {
            System.out.println("user is " + user);
            userRepository.save(user);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    //Get User Method.
    @PostMapping("/validate")
    public ResponseEntity<?> validateUser (@RequestBody User user){
        System.out.println("user name2 " + user.getUsername());
        System.out.println("user name2 " +loginUserRepository.validateUser(user));
        System.out.println("user name3 " +loginUserRepository.getTeacherStatus(user));
        System.out.println("id " +loginUserRepository.getUserID(user));
        System.out.println("all data " +userRepository.findById(Long.valueOf(loginUserRepository.getUserID(user))).get());
        String userType = loginUserRepository.validateUser(user);
        String status = loginUserRepository.getTeacherStatus(user);
        String id = loginUserRepository.getUserID(user);
        Optional<User> users = userRepository.findById(Long.valueOf(loginUserRepository.getUserID(user)));
        ArrayList<String> list = new ArrayList<String>();
        list.add(status);
        list.add(userType);
        list.add(id);

        try {
            return new ResponseEntity<>(users.get() , HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage() , HttpStatus.OK);
        }
    }

    //Get Single user
    @GetMapping("/getsingleuser/{id}")
    public ResponseEntity<?> getSingleUser(@PathVariable("id") Long id){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No User Available", HttpStatus.NOT_FOUND);
        }
    }


    //Update user
    @PutMapping("/updateuser/{id}")
    public ResponseEntity<?> updateById(@PathVariable("id") Long id, @RequestBody User user){
        Optional<User> userUpdate = Optional.ofNullable(userRepository.getById(id));
        System.out.println("user updated " + userUpdate.isPresent());
        if(userUpdate.isPresent()){
            User updateUser = userUpdate.get();
            //updateUser.setId(user.getId()  != 0 ? user.getId() : updateUser.getId());
            updateUser.setName(user.getName() != null ? user.getName() : updateUser.getName());
            updateUser.setEmail(user.getEmail() != null ? user.getEmail() : updateUser.getEmail());
            updateUser.setAge(user.getAge() != null ? user.getAge() : updateUser.getAge());
            updateUser.setUsername(user.getUsername() != null ? user.getUsername() : updateUser.getUsername());
            updateUser.setStatus(user.getStatus() != null ? user.getStatus() : updateUser.getStatus());
            updateUser.setPassword(user.getPassword() != null ? user.getPassword() : updateUser.getPassword());
            updateUser.setType(user.getType() != null ? user.getType() : updateUser.getType());
            userRepository.save(updateUser);
            return new ResponseEntity<>("Update Successful", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No User Available", HttpStatus.NOT_FOUND);
        }
    }


    //Delete user
    @DeleteMapping("/deleteuser/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id){
        userRepository.deleteById(id);
        return new ResponseEntity<>("delete successful", HttpStatus.OK);
    }




}


