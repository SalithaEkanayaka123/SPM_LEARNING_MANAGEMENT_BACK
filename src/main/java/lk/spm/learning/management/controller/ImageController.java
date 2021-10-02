package lk.spm.learning.management.controller;

import lk.spm.learning.management.model.FileModel;
import lk.spm.learning.management.model.ImageModel;
import lk.spm.learning.management.model.TutorCountData;
import lk.spm.learning.management.model.User;
import lk.spm.learning.management.repository.Impl.UserImplementation;
import lk.spm.learning.management.repository.loginUserRepository;
import lk.spm.learning.management.service.FileStorageService;
import lk.spm.learning.management.service.ImageStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ImageController {

    private final ImageStorageService imageStorageService;
    @Autowired
    private loginUserRepository loginUserRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    UserImplementation userImplementation;
    public ImageController(ImageStorageService imageStorageService) {
        this.imageStorageService = imageStorageService;
    }

    @PostMapping("single/upload/image")
    ImageModel singleFileUpload(@RequestParam("file") MultipartFile file,
                                @RequestParam("name")String name,
                                @RequestParam("description")String description,
                                @RequestParam("tutorName")String tutorName
    ){
        String fileName = imageStorageService.storeFile(file);
        imageStorageService.saveFilesToTheDatabase(fileName, name, description, tutorName);
        ImageModel response = new ImageModel(name, description, tutorName,fileName);
        return response;
    }

    @GetMapping("/download/image/{fileName}")
    ResponseEntity<Resource> downloadSingleImage(@PathVariable String fileName, HttpServletRequest request){
        Resource resource = imageStorageService.downloadFile(fileName);
        String mimeType;
        try {
            mimeType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        }catch (IOException e){
            mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }
        mimeType = mimeType == null ? MediaType.APPLICATION_OCTET_STREAM_VALUE: mimeType;

        //Return response.
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(mimeType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + resource.getFilename())
                .body(resource);
    }

    @GetMapping("/images")
    ResponseEntity<?> getAllImages(){
        List<ImageModel> files = imageStorageService.getAllFiles();
        if(files.size() > 0){
            return new ResponseEntity<>(files, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No Files Available", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/teachercountbyclass")
    public ResponseEntity<?> getTeacherCountByClass(){

        List<TutorCountData> imageModels = userImplementation.getTutorListFromClasses();
        try {
            return new ResponseEntity<>(imageModels , HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage() , HttpStatus.OK);
        }
//        ArrayList users = new ArrayList();
//        users.add(imageModels.size());
//        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
