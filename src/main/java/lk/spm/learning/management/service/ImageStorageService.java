package lk.spm.learning.management.service;
import lk.spm.learning.management.model.FileModel;
import lk.spm.learning.management.model.ImageModel;
import lk.spm.learning.management.repository.FileRepository;
import lk.spm.learning.management.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

import java.awt.*;
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
public class ImageStorageService {
    @Autowired
    private ImageRepository imageRepository;
    private final Path imageStoragePath;
    private final String imageStorageLocation;

    public ImageStorageService (@Value("${file.storage.location:img}") String fileStorageLocation){
        this.imageStorageLocation = fileStorageLocation;
        imageStoragePath = Paths.get(fileStorageLocation).toAbsolutePath().normalize();

        try {
            Files.createDirectories(imageStoragePath);
        } catch (IOException e){
            throw new RuntimeException("File creation Issue");
        }
    }

    public String storeFile (MultipartFile file){
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        Path filePath = Paths.get(imageStoragePath + "\\" + fileName);
        try {
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e){
            throw new RuntimeException("Storing file error");
        }
        return fileName;
    }

    public Resource downloadFile(String fileName){
        Path path = Paths.get(imageStorageLocation).toAbsolutePath().resolve(fileName);
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
    public ImageModel saveFilesToTheDatabase(String fileName, String name, String description, String tutorName) {
        ImageModel imageModel = new ImageModel(name, description, tutorName,fileName);
        return imageRepository.save(imageModel);
    }

    public List<ImageModel> getAllFiles() {
        List<ImageModel> files = imageRepository.findAll();
        if(files.size() > 0){
            return files;
        } else {
            List<ImageModel> empty = new ArrayList<>();
            return empty;
        }
    }
}
