package fr.sma.webconfboard.entities;

import lombok.*;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Conference")
@NoArgsConstructor
public class Conference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private @NonNull String title;
    @Column
    private @NonNull String expert;
    @Column
    private @NonNull String description;
    @Column
    private @NonNull String categorie;
    @Column
    private Integer nbVotes;
    @Column
    private Integer score;
    @Column
    private Double rate;
    @Column
    private Boolean isVoted;


    public Conference(String title, String expert, String description, String categorie, Boolean isVoted) {
        this.title = title;
        this.expert = expert;
        this.description = description;
        this.categorie = categorie;
        this.nbVotes = 0;
        this.score = 0;
        this.rate = 0.0;
        if(isVoted == null){
            this.isVoted = false;
        }else {
            this.isVoted = isVoted;
        }
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
