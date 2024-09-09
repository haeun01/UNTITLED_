package PTR.PTR.controller;

import PTR.PTR.model.Admin;
import PTR.PTR.model.Teacher;
import PTR.PTR.model.User;
import PTR.PTR.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class AdminController {
    AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/api/grantTeacher")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<String> grantTeacher(@RequestBody User user){
        return new ResponseEntity<>(adminService.grantTeacher(user),
                HttpStatus.OK);

    }

    @GetMapping("/api/allUser")
    public ResponseEntity<List<User>> allUser(){
        return new ResponseEntity<>(adminService.allUser(),HttpStatus.OK);
    }

    @GetMapping("/api/allAdmin")
    public ResponseEntity<List<Admin>> allAdmin(){
        return new ResponseEntity<>(adminService.allAdmin(),HttpStatus.OK);
    }

    @GetMapping("/api/allTeacher")
    public ResponseEntity<List<Teacher>> allTeacher(){
        return new ResponseEntity<>(adminService.allTeacher(),HttpStatus.OK);
    }

    @GetMapping("/api/onlyUsers")
    public ResponseEntity<List<User>> onlyUsers(){
        return new ResponseEntity<>(adminService.onlyUsers(),HttpStatus.OK);
    }
}
