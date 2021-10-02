package lk.spm.learning.management.controller;

import lk.spm.learning.management.model.Class;
import lk.spm.learning.management.repository.ClassRepository;
import lk.spm.learning.management.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class ClassController {

    @Autowired
    ClassRepository classRepository;

    //GET classes
    @GetMapping("/classes")
    public ResponseEntity<?> getClasses(){
        List<Class> classes = classRepository.findAll();
        if(classes.size() > 0){
            return new ResponseEntity<>(classes, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No Classes Available", HttpStatus.NOT_FOUND);
        }
    }
    //get class by id
    @GetMapping("/classes/{id}")
    public ResponseEntity<?> getClassById(@PathVariable long id){

        Optional<Class> classes = classRepository.findById(id);
        if(classes.isPresent()){
            return new ResponseEntity<>(classes.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No Course Available", HttpStatus.NOT_FOUND);
        }
    }

    //add class
    @PostMapping("/class")
    public ResponseEntity<?> createClass(@RequestBody Class clz){
        try {
            System.out.println("class is " + clz);
            classRepository.save(clz);
            return new ResponseEntity<Class>(clz, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    //update class
    @PutMapping("class/{id}")
    public ResponseEntity<?> updateClass(@PathVariable(value = "id") Long employeeId, @RequestBody Class updatedClass){
        Optional<Class> yetToUpdate = classRepository.findById(employeeId);
        if(yetToUpdate.isPresent()) {
            Class yetToUpdateEmployee = yetToUpdate.get();
            yetToUpdateEmployee.setName(updatedClass.getName());
            yetToUpdateEmployee.setDescription(updatedClass.getName());
            yetToUpdateEmployee.setDescription(updatedClass.getDescription());
            yetToUpdateEmployee.setTutorName(updatedClass.getTutorName());
            yetToUpdateEmployee.setImage(updatedClass.getImage());

            //SAVE THE UPDATED USER.
            classRepository.save(yetToUpdateEmployee);
            return new ResponseEntity<Class>(updatedClass, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Class doesn't exist", HttpStatus.NOT_FOUND);
        }
    }
  
    //Delete class
    @DeleteMapping("/deleteclass/{id}")
    public ResponseEntity<?> deleteClass(@PathVariable("id") Long id){
        classRepository.deleteById(id);
        return new ResponseEntity<>("delete successful", HttpStatus.OK);
    }
}
