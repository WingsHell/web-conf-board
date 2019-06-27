package fr.sma.webconfboard.entities;

import lombok.*;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Conference {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private @NonNull String title;
    private @NonNull String expert;
    private @NonNull String description;
    private @NonNull String categorie;
    private Integer nbVotes;
    private Integer score;
    private Double rate;
    private @NonNull Boolean isVoted;


    public Conference(Long id, String title, String expert, String description, String categorie, Integer nbVotes, Integer score, Double rate, Boolean isVoted) {
        this.id = id;
        this.title = title;
        this.expert = expert;
        this.description = description;
        this.categorie = categorie;
        this.nbVotes = nbVotes;
        this.score = score;
        this.rate = rate;
        this.isVoted = isVoted;
    }

}
