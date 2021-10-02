package lk.spm.learning.management.controller;
import lk.spm.learning.management.model.ChartData;
import lk.spm.learning.management.model.FileModel;
import lk.spm.learning.management.repository.Impl.UserImplementation;
import lk.spm.learning.management.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class FileController {

    @Autowired
    UserImplementation userImplementation;
    private final FileStorageService fileStorageService;

    public FileController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @PostMapping("single/upload")
    FileModel singleFileUpload(@RequestParam("file")MultipartFile file,
                               @RequestParam("name")String name,
                               @RequestParam("price")String price,
                               @RequestParam("course")String course,
                               @RequestParam("description")String description
    ) throws IOException {
        String fileName = fileStorageService.storeFile(file);
        String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(fileName)
                .toUriString();
        String contentType = file.getContentType();

        // Testing File Path
        String currentDirectory = System.getProperty("user.dir");
        String fullPath = currentDirectory + "\\temp\\" + fileName;

        fileStorageService.saveFilesToTheDatabase(fileName, url, contentType,price, description, course, name);
        FileModel response = new FileModel(fileName, url, contentType,price, description, course, name);
        return response;
    }

    @GetMapping("/download/{fileName}")
    ResponseEntity<Resource> downloadSigleFile(@PathVariable String fileName, HttpServletRequest request){
        Resource resource = fileStorageService.downloadFile(fileName);
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

    @GetMapping("/files")
    ResponseEntity<?> getAllFiles(){
        List<FileModel> files = fileStorageService.getAllFiles();
        if(files.size() > 0){
            return new ResponseEntity<>(files, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No Files Available", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/tutor/charts")
    public ResponseEntity<?> getChartData() {
        List<ChartData> data = userImplementation.getGraphData();
        if(data.size() > 0){
            return new ResponseEntity<>(data, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No Files Available", HttpStatus.NOT_FOUND);
        }
    }
}
