package models;

import com.avaje.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by admin on 2016-12-7.
 */

@Entity
@Table(name = "data_comment")
public class dataComment extends Model {
    private Long id;
    private String comment_detail;
    private String comment_person;
    private Date comment_date;
    private String comment_header;
    private String other;
    private Long floor;

    public static Finder<Long,dataComment> find=new Finder<>(dataComment.class);

    @Id
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name="comment_detail")
    public String getComment_detail() {
        return comment_detail;
    }

    public void setComment_detail(String comment_detail) {
        this.comment_detail = comment_detail;
    }

    @Column(name="comment_person")
    public String getComment_person() {
        return comment_person;
    }

    public void setComment_person(String comment_person) {
        this.comment_person = comment_person;
    }

    @Column(name="comment_date")
    public Date getComment_date() {
        return comment_date;
    }

    public void setComment_date(Date comment_date) {
        this.comment_date = comment_date;
    }

    @Column(name="comment_header")
    public String getComment_header() {
        return comment_header;
    }

    public void setComment_header(String comment_header) {
        this.comment_header = comment_header;
    }

    @Column(name="other")
    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    @Column(name="floor")
    public Long getFloor() {
        return floor;
    }

    public void setFloor(Long floor) {
        this.floor = floor;
    }
}
