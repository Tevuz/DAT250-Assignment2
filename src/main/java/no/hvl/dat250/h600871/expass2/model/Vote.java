package no.hvl.dat250.h600871.expass2.model;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "vote")
public final class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private Instant publishedAt;
    @ManyToOne
    private User user;
    @OneToMany
    private List<VoteOption> voteOption;

    public Vote(
            long id,
            Instant publishedAt,
            User user,
            List<VoteOption> voteOption
    ) {
        this.id = id;
        this.publishedAt = publishedAt;
        this.user = user;
        this.voteOption = voteOption;
    }

    public Vote() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Instant getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Instant publishedAt) {
        this.publishedAt = publishedAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<VoteOption> getVoteOption() {
        return voteOption;
    }

    public void setVoteOption(List<VoteOption> voteOption) {
        this.voteOption = voteOption;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Vote) obj;
        return this.id == that.id &&
                Objects.equals(this.publishedAt, that.publishedAt) &&
                Objects.equals(this.user, that.user) &&
                Objects.equals(this.voteOption, that.voteOption);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, publishedAt, user, voteOption);
    }

    @Override
    public String toString() {
        return "Vote[" +
                "id=" + id + ", " +
                "publishedAt=" + publishedAt + ", " +
                "user=" + user + ", " +
                "voteOption=" + voteOption + ']';
    }
}

