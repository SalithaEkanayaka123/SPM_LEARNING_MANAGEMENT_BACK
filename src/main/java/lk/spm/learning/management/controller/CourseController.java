package lk.spm.learning.management.controller;

import lk.spm.learning.management.model.Course;
import lk.spm.learning.management.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class CourseController {

    @Autowired
    CourseRepository courseRepository;


    //GET EMPLOYEES
    @GetMapping("/courses")
    public ResponseEntity<?> getCourse(){
        List<Course> courses = courseRepository.findAll();
        if(courses.size() > 0){
            return new ResponseEntity<List<Course>>(courses, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No Course Available", HttpStatus.NOT_FOUND);
        }
    }
    //GET EMPLOYEE BY ID
    @GetMapping("/courses/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable long id){

        Optional<Course> courses = courseRepository.findById(id);
        if(courses.isPresent()){
            return new ResponseEntity<>(courses.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No Course Available", HttpStatus.NOT_FOUND);
        }
    }

    //SAVE EMPLOYEE
    @PostMapping("/course")
    public ResponseEntity<?> createCourse(@RequestBody Course course){
        try {
            System.out.println("user is " + course);
            courseRepository.save(course);
            return new ResponseEntity<Course>(course, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    //UPDATE EMPLOYEE
    @PutMapping("course/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable(value = "id") Long employeeId, @RequestBody Course updatedCourse){
        Optional<Course> yetToUpdate = courseRepository.findById(employeeId);
        if(yetToUpdate.isPresent()) {
            Course yetToUpdateEmployee = yetToUpdate.get();
            yetToUpdateEmployee.setTitle(updatedCourse.getTitle());
            yetToUpdateEmployee.setDescription(updatedCourse.getDescription());
            yetToUpdateEmployee.setBody(updatedCourse.getBody());

            //SAVE THE UPDATED USER.
            courseRepository.save(yetToUpdateEmployee);
            return new ResponseEntity<Course>(updatedCourse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Course doesn't exist", HttpStatus.NOT_FOUND);
        }
    }

    //DELETE COURSE.
    @DeleteMapping("delete/course/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long courseId ){
        try {
            courseRepository.deleteById(courseId);
            return new ResponseEntity<String>("Item Deleted" + courseId, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
