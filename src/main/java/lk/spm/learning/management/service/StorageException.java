package lk.spm.learning.management.service;

public class StorageException extends RuntimeException{
    public StorageException(String status){
        super(status);
    }
}
