package PTR.PTR.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name="lecture_user")
public class LectureUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "teacher_rating")
    private int teacherRating;
    @Column(name = "teacher_review")
    private String teacherReview;
}
