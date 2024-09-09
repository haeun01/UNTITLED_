package PTR.PTR.service;

import PTR.PTR.model.Admin;
import PTR.PTR.model.LectureUser;
import PTR.PTR.model.Teacher;
import PTR.PTR.model.User;
import PTR.PTR.repository.AdminRepository;
import PTR.PTR.repository.TeacherRepository;
import PTR.PTR.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminService {
    AdminRepository adminRepository;
    TeacherRepository teacherRepository;
    UserRepository userRepository;

    public AdminService(AdminRepository adminRepository, TeacherRepository teacherRepository, UserRepository userRepository) {
        this.adminRepository = adminRepository;
        this.teacherRepository = teacherRepository;
        this.userRepository = userRepository;
    }

    public String grantTeacher(User user){
        if (teacherRepository.findByUser(user)!=null){
            return "이미 권한이 부여되었습니다.";
        }
        Teacher teacher = new Teacher();
        teacher.setUser(user);
        teacherRepository.save(teacher);
        return teacher.getUser().getUserId();
    }

    public List<User> allUser(){
        return userRepository.findAll();
    }

    public List<Admin> allAdmin(){
        return adminRepository.findAll();
    }

    public List<Teacher> allTeacher(){
        return teacherRepository.findAll();
    }

    public List<User> onlyUsers(){
        List<User> users = userRepository.findAll();
        List<User> teachers = allTeacher().stream().map(Teacher::getUser).toList();
        List<User> admins = allAdmin().stream().map(Admin::getUser).toList();
        List<User> onlyUsers = new ArrayList<>();
        for (int i=0; i<users.size(); i++){
            boolean yes = true;
            for (int j=0; j<teachers.size(); j++){
                if (users.get(i).equals(teachers.get(j))){
                    yes = false;
                }
            }
            for (int j=0; j<admins.size(); j++){
                if (users.get(i).equals(admins.get(j))){
                    yes = false;
                }
            }
            if (yes){
                onlyUsers.add(users.get(i));
            }
        }
        return onlyUsers;
    }
}
