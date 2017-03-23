package models;

import com.avaje.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by admin on 2017-3-21.
 */
@Entity
@Table(name = "commit_name")
public class commitName {
    private Long id;
    private String name;

    public static Model.Finder<Long, commitName> find = new Model.Finder<>(commitName.class);

    @Id
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
