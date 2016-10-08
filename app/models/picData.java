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
    private Long like_num;
    private Long dis_like_num;


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

    @Id
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "like_num")
    public Long getLike_num() {
        return like_num;
    }

    public void setLike_num(Long like_num) {
        this.like_num = like_num;
    }

    @Column(name = "dislike_num")
    public Long getDis_like_num() {
        return dis_like_num;
    }

    public void setDis_like_num(Long dis_like_num) {
        this.dis_like_num = dis_like_num;
    }
}
