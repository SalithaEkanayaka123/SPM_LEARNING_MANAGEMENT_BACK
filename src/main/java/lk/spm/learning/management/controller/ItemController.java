package lk.spm.learning.management.controller;
import lk.spm.learning.management.model.Item;
import lk.spm.learning.management.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ItemController {

    @Autowired
    ItemRepository itemRepository;

    //Original Get All method.
    @GetMapping("/displayitem")
    public ResponseEntity<?> getAllItems(){
        // itemRepository.save();
        return null;
    }

    //Add item
    @PostMapping("/item")
    public ResponseEntity<?> createItem() {
        System.out.println("Hi");
        return null;
    }

    //Get Single item
    @GetMapping("/getsingleitem/{id}")
    public ResponseEntity<?> getSingleItem(@PathVariable("id") String id){
        return null;
    }

    //Update item
    @PutMapping("/updateitem/{id}")
    public ResponseEntity<?> updateById(@PathVariable("id") String id, @RequestBody Item item){
        return null;
    }

    //Delete Item
    @DeleteMapping("/deleteitem/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable("id") String id){
        return null;
    }
}
