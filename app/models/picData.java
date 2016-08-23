package models;

import com.avaje.ebean.Finder;
import com.avaje.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by admin on 2016/8/8.
 */
@Entity
@Table(name = "picdata")
public class picData extends Model {
    private Long id;
    private String url;
    private String value;

    public static Finder<Long, picData> find = new Finder<>(picData.class);

    @Column(name = "piclink")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "picvalue")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
