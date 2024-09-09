package PTR.PTR.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SignupDto { // 원래는 loginDto(userId, password)와 signupDto(모든 유저 정보) 따로 나눠줘야함
    private String userId;
    private String password;
    private String userName;
    private String userEmail;
    private LocalDate birthday;
}
