package fr.sma.webconfboard.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Comment")
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    @Column
    private @NonNull String message;
    @Column
    private @NonNull Long userId;
    @Column
    private @NonNull Long conferenceId;

    public Comment(Long id, String message, Long userId, Long conferenceId) {
        this.message = message;
        this.userId = userId;
        this.conferenceId = conferenceId;
    }
}
