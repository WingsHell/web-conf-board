package fr.sma.webconfboard.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private @NonNull String message;
    private @NonNull Long userId;
    private @NonNull Long conferenceId;

    public Comment(Long id, String message, Long userId, Long conferenceId) {
        this.message = message;
        this.userId = userId;
        this.conferenceId = conferenceId;
    }
}
