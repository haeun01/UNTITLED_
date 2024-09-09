package PTR.PTR.model;

import PTR.PTR.model.Teacher;
import PTR.PTR.model.User;
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
@Table(name="notice")
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String title;
    @Column
    private String text;
    @ManyToOne
    @JoinColumn
    private Admin admin;
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}
