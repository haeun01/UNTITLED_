package PTR.PTR.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name="calendar_exercise_record")
public class CalendarExerciseRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "calendar_id")
    private Calendar calendar;
    @Column
    private int aerobic;
    @Column
    private int muscle;
    @Column
    private int stretch;
    @Column
    private int yoga;
    @Column
    private String detail;
}
