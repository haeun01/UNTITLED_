package PTR.PTR.controller;

import PTR.PTR.service.AuthorityService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorityController {
    AuthorityService authorityService;

    public AuthorityController(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }
}