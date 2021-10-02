package lk.spm.learning.management.controller;
import lk.spm.learning.management.model.Payment;
import lk.spm.learning.management.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
@RestController

public class PaymentController {

    @Autowired
    PaymentRepository paymentRepository;

    //================ Get All Payment Details =======================
    @GetMapping("/payment")
    public ResponseEntity<?> getAllPayments(){
        List<Payment> payments = paymentRepository.findAll();
        if(payments.size() > 0){
            return new ResponseEntity<>("Payment Available", HttpStatus.OK);
            //return new ResponseEntity<List<Payment>>(payments, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No User Available", HttpStatus.NOT_FOUND);
        }
    }

    //================ Add single Payment Details =======================
    @PostMapping("/addPayment")
    public ResponseEntity<?> addPayment(@RequestBody Payment payment) {
        System.out.println("payment is " + payment);
        try {
            System.out.println("payment is " + payment);
            paymentRepository.save(payment);
            return new ResponseEntity<Payment>(payment, HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        //return  new ResponseEntity<>("lol", HttpStatus.NOT_FOUND);
    }
}
