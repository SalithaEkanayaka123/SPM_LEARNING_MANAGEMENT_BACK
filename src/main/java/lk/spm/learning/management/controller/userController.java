package lk.spm.learning.management.controller;

import lk.spm.learning.management.model.User;
import lk.spm.learning.management.model.loginUser;
import lk.spm.learning.management.repository.loginUserRepository;
import lk.spm.learning.management.repository.userRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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


    @GetMapping("/usercount")
    public ResponseEntity<?> getTeacherCount(){
        List<User> teachers = loginUserRepository.getTeacherList();
        List<User> students = loginUserRepository.getStudentList();
        List<User> user = loginUserRepository.getUserList();
        ArrayList users = new ArrayList();
        users.add(teachers.size());
        users.add(students.size());
        users.add(user.size());
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/teachercount")
    public ResponseEntity<?> getValidTeacherCount(){
        List<User> valid = loginUserRepository.getValidTeacherList();
        List<User> invalid = loginUserRepository.getInvalidTeacherList();
        List<User> pending = loginUserRepository.getPendingTeacherList();
        ArrayList users = new ArrayList();
        users.add(valid.size());
        users.add(invalid.size());
        users.add(pending.size());
        return new ResponseEntity<>(users, HttpStatus.OK);
    }



    //Add user
    @PostMapping("/useradd")
    public ResponseEntity<?> createUser( @RequestBody User user) {
        try {
            System.out.println("user is " + user.getPassword());
            userRepository.save(user);
            return new ResponseEntity<User>(user, HttpStatus.OK);//
        } catch (DataIntegrityViolationException e){
            return new ResponseEntity<>("username exists", HttpStatus.OK);
        }catch (Exception e){
            System.out.println("si " + e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }
    }

    //Get User Method.
    @PostMapping("/validate")
    public ResponseEntity<?> validateUser (@RequestBody User user){
        Optional<User> users = null;
        try {
            users = userRepository.findById(Long.valueOf(loginUserRepository.getUserID(user)));
            return new ResponseEntity<>(users.get() , HttpStatus.OK);
        } catch (java.lang.NullPointerException e){
            return new ResponseEntity<>(users.get(), HttpStatus.OK);
        }
        catch (Exception e) {
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
            User value = userRepository.save(updateUser);
            //System.out.println("hi " + updateUser);
            return new ResponseEntity<>(value, HttpStatus.OK);
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


