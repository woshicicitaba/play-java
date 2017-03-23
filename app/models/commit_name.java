package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by admin on 2017-3-21.
 */
@Entity
@Table(name = "commit_name")
public class commit_name {
    private Long id;
    private String commit_name;

    @Id
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "commit_name")
    public String commit_name() {
        return commit_name;
    }

    public void commit_name(String commit_name) {
        this.commit_name = commit_name;
    }
}
