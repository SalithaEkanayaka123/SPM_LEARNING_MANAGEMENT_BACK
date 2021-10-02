package lk.spm.learning.management.service;
import lk.spm.learning.management.model.FileModel;
import lk.spm.learning.management.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class FileStorageService {
    @Autowired
    private FileRepository fileRepository;
    private final Path fileStoragePath;
    private final String fileStorageLocation;

    public FileStorageService (@Value("${file.storage.location:temp}") String fileStorageLocation){
        this.fileStorageLocation = fileStorageLocation;
        fileStoragePath = Paths.get(fileStorageLocation).toAbsolutePath().normalize();
        try {
            Files.createDirectories(fileStoragePath);
        } catch (IOException e){
            throw new RuntimeException("File creation Issue");
        }
    }

    public String storeFile (MultipartFile file){
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        Path filePath = Paths.get(fileStoragePath + "\\" + fileName);
        try {
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e){
            throw new RuntimeException("Storing file error");
        }
        return fileName;
    }

    public Resource downloadFile(String fileName){
        Path path = Paths.get(fileStorageLocation).toAbsolutePath().resolve(fileName);
        Resource resource;
        try {
            resource = new UrlResource(path.toUri());
        } catch(MalformedURLException e){
            throw new RuntimeException("Reading File Error");
        }
        if(resource.exists() && resource.isReadable()){
            return resource;
        } else {
            throw new RuntimeException("File not available");
        }

    }

    // Method to save the uploaded files in the database.
    public FileModel saveFilesToTheDatabase(String fileName, String fileUrl, String contentType, String price, String description, String course, String name) {
        FileModel fileModel = new FileModel(fileName, fileUrl, contentType, price, description, course, name);
        return fileRepository.save(fileModel);
    }

    public List<FileModel> getAllFiles() {
        List<FileModel> files = fileRepository.findAll();
        if(files.size() > 0){
            return files;
        } else {
            List<FileModel> empty = new ArrayList<>();
            return empty;
        }
    }
}
