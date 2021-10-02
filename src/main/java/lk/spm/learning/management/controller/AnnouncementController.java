package lk.spm.learning.management.controller;
import lk.spm.learning.management.model.Announcement;
import lk.spm.learning.management.model.TutorCountData;
import lk.spm.learning.management.repository.AnnouncementRepository;
import lk.spm.learning.management.repository.Impl.UserImplementation;
import lk.spm.learning.management.repository.loginUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import lk.spm.learning.management.model.AnnouncementCountData;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class AnnouncementController {

    @Autowired
    AnnouncementRepository announcementRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    UserImplementation userImplementation;

    //GET announcements
    @GetMapping("/announcements")
    public ResponseEntity<?> getAnnouncements(){
        List<Announcement> announcements = announcementRepository.findAll();
        if(announcements.size() > 0){
            return new ResponseEntity<>(announcements, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No Announcements Available", HttpStatus.NOT_FOUND);
        }
    }

    //get class by id
    @GetMapping("/announcements/{id}")
    public ResponseEntity<?> getAnnouncementById(@PathVariable long id){

        Optional<Announcement> announcements = announcementRepository.findById(id);
        if(announcements.isPresent()){
            return new ResponseEntity<>(announcements.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No Announcements Available", HttpStatus.NOT_FOUND);
        }
    }

    //add Announcement
    @PostMapping("/announcement")
    public ResponseEntity<?> createAnnouncement(@RequestBody Announcement announcement){
        try {
            System.out.println("class is " + announcement);
            announcementRepository.save(announcement);
            return new ResponseEntity<Announcement>(announcement, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    //update class
    @PutMapping("announcement/{id}")
    public ResponseEntity<?> updateAnnouncement(@PathVariable(value = "id") Long employeeId, @RequestBody Announcement updateAnnouncement){
        Optional<Announcement> yetToUpdate = announcementRepository.findById(employeeId);
        if(yetToUpdate.isPresent()) {
            Announcement yetToUpdateEmployee = yetToUpdate.get();
            yetToUpdateEmployee.setHeader(updateAnnouncement.getHeader());
            yetToUpdateEmployee.setBody(updateAnnouncement.getBody());
            yetToUpdateEmployee.setName(updateAnnouncement.getName());

            //SAVE THE UPDATED USER.
            announcementRepository.save(yetToUpdateEmployee);
            return new ResponseEntity<Announcement>(updateAnnouncement, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Announcement doesn't exist", HttpStatus.NOT_FOUND);
        }
    }

    //Delete class
    @DeleteMapping("/deleteannouncement/{id}")
    public ResponseEntity<?> deleteAnnouncement(@PathVariable("id") Long id){
        announcementRepository.deleteById(id);
        return new ResponseEntity<>("delete successful", HttpStatus.OK);
    }

    @GetMapping("/anncountbyclass")
    public ResponseEntity<?> getAnnCountByClass(){
        List<AnnouncementCountData> imageModels = userImplementation.getAnnListFromClasses();
        try {
            return new ResponseEntity<>(imageModels , HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage() , HttpStatus.OK);
        }

    }


}
