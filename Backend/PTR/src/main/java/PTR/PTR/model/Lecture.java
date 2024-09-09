package PTR.PTR.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name="lecture")
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    @Column(name="thumbnail")
    private String thumbnail;
    @Column(name = "image")
    private String image;
    @Column(name = "description")
    private String description;
    @Column(name = "lecture_name")
    private String lectureName;
    @Column(name = "price")
    private int price;
    @Column(name = "url")
    private String url;
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
}
