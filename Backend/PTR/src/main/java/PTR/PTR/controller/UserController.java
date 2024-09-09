package PTR.PTR.controller;

import PTR.PTR.dto.LoginDto;
import PTR.PTR.dto.SessionDto;
import PTR.PTR.dto.SignupDto;
import PTR.PTR.model.User;
import PTR.PTR.model.UserCategory;
import PTR.PTR.service.UserDetailService;
import PTR.PTR.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class UserController {
    private UserService userService;
    private UserDetailService userDetailService;
    private AuthenticationManager authenticationManager;
    private HttpServletRequest httpServletRequest;

    public UserController(UserService userService, UserDetailService userDetailService, AuthenticationManager authenticationManager, HttpServletRequest httpServletRequest) {
        this.userService = userService;
        this.userDetailService = userDetailService;
        this.authenticationManager = authenticationManager;
        this.httpServletRequest = httpServletRequest;
    }
    // 회원가입
    @PostMapping("/api/signup")
    public ResponseEntity<String> signup(@RequestBody SignupDto signupDto){
        String response = userService.saveUser(signupDto);
        if ("이미 등록된 아이디입니다.".equals(response)) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>("회원가입이 완료되었습니다.", HttpStatus.CREATED);
    }
    // 로그인
    @PostMapping("/api/user/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto, HttpServletRequest request){
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getUserId(), loginDto.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            HttpSession session = request.getSession(true);
            session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
            return ResponseEntity.ok("Success");
        }
        catch (AuthenticationException e) {
            // 인증 실패 처리 (아이디 또는 비밀번호 오류)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("아이디 또는 비밀번호가 틀렸습니다.");
        }

    }
    //로그아웃
    @PostMapping("/api/user/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null){
            session.invalidate();
        }
        return "You have been logged out.";
    }

    @GetMapping("/api/current")
    public SessionDto getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("User is not authenticated");
        }
        SessionDto sessionDto = new SessionDto();
        sessionDto.setUserId(authentication.getName());
        sessionDto.setAuthority(authentication.getAuthorities());
        return sessionDto;
    }
    // 유저 카테고리 수정 및 저장
    @PostMapping("/api/saveUserCategory")
    public ResponseEntity<String> saveUserCategory(@RequestBody List<UserCategory> userCategories){
        return new ResponseEntity<>(userService.saveUserCategory(userCategories), HttpStatus.OK);
    }
    // 유저 카테고리 삭제
    @DeleteMapping("/api/deleteUserCategory")
    public ResponseEntity<String> deleteUserCategory(@RequestBody User user){
        return new ResponseEntity<>(userService.deleteUserCategory(user), HttpStatus.OK);
    }
    // 유저 카테고리 조회
    @PostMapping("/api/findUserCategory")
    public ResponseEntity<List<UserCategory>> findUserCategory(@RequestBody User user){
        return new ResponseEntity<>(userService.findUserCategory(user), HttpStatus.OK);
    }
    // 유저 비밀번호 수정
    @PostMapping("/api/changePassword")
    public ResponseEntity<User> changePassword(@RequestBody User user){
        return new ResponseEntity<>(userService.changePassword(user), HttpStatus.OK);
    }
    // 유저 프로필 이미지 수정
    @PostMapping("/api/changeProfileImg")
    public ResponseEntity<User> changeProfileImg(@RequestBody User user){
        return new ResponseEntity<>(userService.changeProfileImg(user), HttpStatus.OK);
    }
    // 유저 프로필 글 수정
    @PostMapping("/api/changeProfileText")
    public ResponseEntity<User> changeProfileText(@RequestBody User user){
        return new ResponseEntity<>(userService.changeProfileText(user), HttpStatus.OK);
    }

    @PostMapping("/api/findCoin")
    public ResponseEntity<Integer> findCoin(@RequestBody User user){
        return new ResponseEntity<>(userService.findCoin(user), HttpStatus.OK);
    }
    @PostMapping("/api/changeCoin")
    public ResponseEntity<User> changeCoin(@RequestBody User user){
        return new ResponseEntity<>(userService.changeCoin(user), HttpStatus.OK);
    }

    @PostMapping("/api/sendUser")
    public ResponseEntity<User> sendUser(@RequestBody User user){
        return new ResponseEntity<>(userService.sendUser(user), HttpStatus.OK);
    }


    @PostMapping("/api/changeUserName")
    public ResponseEntity<User> changeUserName(@RequestBody User user){
        return new ResponseEntity<>(userService.changeUserName(user), HttpStatus.OK);
    }
    @PostMapping("/api/changeUserEmail")
    public ResponseEntity<User> changeUserEmail(@RequestBody User user){
        return new ResponseEntity<>(userService.changeUserEmail(user), HttpStatus.OK);
    }
    @PostMapping("/api/changeUserBirthday")
    public ResponseEntity<User> changeUserBirthday(@RequestBody User user){
        return new ResponseEntity<>(userService.changeUserBirthday(user), HttpStatus.OK);
    }
    @PostMapping("/api/upload/user")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file, @RequestParam("user") String userId) {
        return new ResponseEntity<>(userService.uploadImage(file, userId), HttpStatus.OK);
    }
}
