package PTR.PTR.repository;

import PTR.PTR.model.Category;
import PTR.PTR.model.Lecture;
import PTR.PTR.model.LectureCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectureCategoryRepository extends JpaRepository<LectureCategory, Long> {
    LectureCategory findByLectureAndCategory(Lecture lecture, Category category);
    List<LectureCategory> findByLecture(Lecture lecture);
    List<LectureCategory> findByCategory(Category category);
    List<LectureCategory> findAllByCategoryIn(List<Category> Category);
}
