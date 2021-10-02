package lk.spm.learning.management.controller;

import lk.spm.learning.management.model.Class;
import lk.spm.learning.management.model.FeedbackModel;
import lk.spm.learning.management.repository.FeedbackRepository;
import lk.spm.learning.management.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class FeedbackController {

    @Autowired
    FeedbackRepository feedbackRepository;

    @GetMapping("/feedback")
    public ResponseEntity<?> getFeedback(){
        List<FeedbackModel> classes = feedbackRepository.findAll();
        if(classes.size() > 0){
            return new ResponseEntity<>(classes, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No Feedbacks Available", HttpStatus.NOT_FOUND);
        }
    }



}
