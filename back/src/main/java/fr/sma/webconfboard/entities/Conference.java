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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private @NonNull String title;
    private @NonNull String expert;
    private @NonNull String description;
    private @NonNull String categorie;
    private Integer nbVotes;
    private Integer score;
    private Double rate;
    private Boolean isVoted;


    public Conference(Long id, String title, String expert, String description, String categorie, Integer nbVotes, Integer score, Double rate) {
        this.id = id;
        this.title = title;
        this.expert = expert;
        this.description = description;
        this.categorie = categorie;
        this.nbVotes = nbVotes;
        this.score = score;
        this.rate = rate;
        this.isVoted = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExpert() {
        return expert;
    }

    public void setExpert(String expert) {
        this.expert = expert;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public Integer getNbVotes() {
        return nbVotes;
    }

    public void setNbVotes(Integer nbVotes) {
        this.nbVotes = nbVotes;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Boolean getIsVoted() {
        return isVoted;
    }

    public void setIsVoted(Boolean voted) {
        isVoted = voted;
    }

}
