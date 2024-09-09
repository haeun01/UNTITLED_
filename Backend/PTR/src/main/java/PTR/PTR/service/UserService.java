package PTR.PTR.service;

import PTR.PTR.dto.LoginDto;
import PTR.PTR.dto.SignupDto;
import PTR.PTR.model.Authority;
import PTR.PTR.model.Category;
import PTR.PTR.model.User;
import PTR.PTR.model.UserCategory;
import PTR.PTR.repository.AuthorityRepository;
import PTR.PTR.repository.UserCategoryRepository;
import PTR.PTR.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    UserRepository userRepository;
    UserCategoryRepository userCategoryRepository;
    AuthorityRepository authorityRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, UserCategoryRepository userCategoryRepository, AuthorityRepository authorityRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.userCategoryRepository = userCategoryRepository;
        this.authorityRepository = authorityRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public String saveUser(SignupDto signupDto) {
        Optional<User> userOptional = userRepository.findByUserId(signupDto.getUserId());
        if (userOptional.isPresent()){
            return "이미 등록된 아이디입니다.";
        }
        Authority authority = new Authority();
        authority.setAuthorityName("ROLE_USER");

        Optional<Authority> authorityOptional = authorityRepository.findById("ROLE_USER");
        if (authorityOptional.isEmpty()){
            authorityRepository.save(authority);
        }

        User user = new User(
                signupDto.getUserId(),
                bCryptPasswordEncoder.encode(signupDto.getPassword()),
                signupDto.getUserName(),
                signupDto.getUserEmail(),
                signupDto.getBirthday(),
                LocalDateTime.now(),
                "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMzEwMDZfNTYg%2FMDAxNjk2NTkwNTMzMDAw.5EbiBwYZ9wWxrN831q0z4AC92SmxB6lE4i4nja-qzDkg.Nx6ER8bleU0SDZJc7s6SM5ITsJv9GP_WUa-RW_cwGuog.JPEG.tmvldkrk%2F105.jpg&type=sc960_832",
                null,
                "",
                0,
                authority);
        return userRepository.save(user).getUserId();
    }

    public boolean authenticateUser(LoginDto loginDto) {
        Optional<User> userOptional = userRepository.findByUserId(loginDto.getUserId());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return bCryptPasswordEncoder.matches(loginDto.getPassword(), user.getPassword());
        }
        return false;
    }

    public String saveUserCategory(List<UserCategory> userCategories){
        if (!userCategoryRepository.findByUser(userCategories.getFirst().getUser()).isEmpty()){
            userCategoryRepository.deleteAll(userCategoryRepository.findByUser(userCategories.getFirst().getUser()));
        }
        userCategoryRepository.saveAll(userCategories);
        return "카테고리가 저장됨";
    }
    public String deleteUserCategory(User user){
        if (!userCategoryRepository.findByUser(user).isEmpty()){
            userCategoryRepository.deleteAll(userCategoryRepository.findByUser(user));
        }
        return "카테고리가 삭제됨";
    }
    public List<UserCategory> findUserCategory(User user){
        return userCategoryRepository.findByUser(user);
    }

    // 유저 비밀번호 수정
    public User changePassword(User user){
        User changeUser = userRepository.findById(user.getUserId()).get();
        changeUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(changeUser);
    }
    // 유저 프로필 이미지 수정
    public User changeProfileImg(User user){
        User changeUser = userRepository.findById(user.getUserId()).get();
        changeUser.setProfileImg(user.getProfileImg());
        return userRepository.save(changeUser);
    }
    // 유저 프로필 글 수정
    public User changeProfileText(User user){
        User changeUser = userRepository.findById(user.getUserId()).get();
        changeUser.setProfileText(user.getProfileText());
        return userRepository.save(changeUser);
    }

    public int findCoin(User user){
        return userRepository.findByUserId(user.getUserId()).get().getCoin();
    }

    public User changeCoin(User user){
        User changeUser = userRepository.findByUserId(user.getUserId()).get();
        changeUser.setCoin(user.getCoin());
        return userRepository.save(changeUser);
    }

    public User sendUser(User user){
        return userRepository.findByUserId(user.getUserId()).get();
    }

    public User changeUserName(User user){
        User changeUser = userRepository.findByUserId(user.getUserId()).get();
        changeUser.setUserName(user.getUserName());
        return userRepository.save(changeUser);
    }

    public User changeUserEmail(User user){
        User changeUser = userRepository.findByUserId(user.getUserId()).get();
        changeUser.setEmail(user.getEmail());
        return userRepository.save(changeUser);
    }

    public User changeUserBirthday(User user){
        User changeUser = userRepository.findByUserId(user.getUserId()).get();
        changeUser.setBirthday(user.getBirthday());
        return userRepository.save(changeUser);
    }

    public String uploadImage(MultipartFile file, String userId){
        try {
            byte[] fileBytes = file.getBytes();
            User changeUser = userRepository.findByUserId(userId).get();
            changeUser.setData(fileBytes);
            userRepository.save(changeUser);
            return "File uploaded to DB successfully: " + file;
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to upload file";
        }
    }
}