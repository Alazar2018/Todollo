package et.com.alazdev.todollo.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import et.com.alazdev.todollo.model.enums.Priorites;
import et.com.alazdev.todollo.model.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;
@Entity
@Table(name = "tasks")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    private String title;
    private String description;
    @NonNull
    private Priorites priorites;
    @NonNull
    private Status status;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "catagory_id")
    private Categories categories;

//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(name = "user_id")
//    private Users userID;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updatedOn;

    @NonNull
    private Date taskEndDate;
}
