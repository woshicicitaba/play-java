package models;

import com.avaje.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by admin on 2016-8-25.
 */
@Entity
@Table(name = "arrrt")
public class dataBase extends Model {
    private Long id;
    private String type;
    private String arrt1;
    private String arrt2;
    private String arrt3;
    private String arrt4;
    private String arrt5;
    private String arrt6;
    private String arrt7;
    private String arrt8;
    private String arrt9;
    private String arrt10;
    private String arrt11;
    private String arrt12;
    private String arrt13;
    private String arrt14;
    private String arrt15;
    private String arrt16;

    public static Finder<Long, dataBase> find = new Finder<>(dataBase.class);

    @Id
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "arrrt1")
    public String getArrt1() {
        return arrt1;
    }

    public void setArrt1(String arrt1) {
        this.arrt1 = arrt1;
    }

    @Column(name = "arrrt2")
    public String getArrt2() {
        return arrt2;
    }

    public void setArrt2(String arrt2) {
        this.arrt2 = arrt2;
    }

    @Column(name = "arrrt3")
    public String getArrt3() {
        return arrt3;
    }

    public void setArrt3(String arrt3) {
        this.arrt3 = arrt3;
    }

    @Column(name = "arrrt4")
    public String getArrt4() {
        return arrt4;
    }

    public void setArrt4(String arrt4) {
        this.arrt4 = arrt4;
    }

    @Column(name = "arrrt5")
    public String getArrt5() {
        return arrt5;
    }

    public void setArrt5(String arrt5) {
        this.arrt5 = arrt5;
    }

    @Column(name = "arrrt6")
    public String getArrt6() {
        return arrt6;
    }

    public void setArrt6(String arrt6) {
        this.arrt6 = arrt6;
    }

    @Column(name = "arrrt7")
    public String getArrt7() {
        return arrt7;
    }

    public void setArrt7(String arrt7) {
        this.arrt7 = arrt7;
    }

    @Column(name = "arrrt8")
    public String getArrt8() {
        return arrt8;
    }

    public void setArrt8(String arrt8) {
        this.arrt8 = arrt8;
    }


    @Column(name = "arrrt9")
    public String getArrt9() {
        return arrt9;
    }

    public void setArrt9(String arrt9) {
        this.arrt9 = arrt9;
    }


    @Column(name = "arrrt10")
    public String getArrt10() {
        return arrt10;
    }

    public void setArrt10(String arrt10) {
        this.arrt10 = arrt10;
    }

    @Column(name = "arrrt11")
    public String getArrt11() {
        return arrt11;
    }

    public void setArrt11(String arrt11) {
        this.arrt11 = arrt11;
    }

    @Column(name = "arrrt12")
    public String getArrt12() {
        return arrt12;
    }

    public void setArrt12(String arrt12) {
        this.arrt12 = arrt12;
    }


    @Column(name = "arrrt13")
    public String getArrt13() {
        return arrt13;
    }

    public void setArrt13(String arrt13) {
        this.arrt13 = arrt13;
    }

    @Column(name = "arrrt14")
    public String getArrt14() {
        return arrt14;
    }

    public void setArrt14(String arrt14) {
        this.arrt14 = arrt14;
    }

    @Column(name = "arrrt15")
    public String getArrt15() {
        return arrt15;
    }

    public void setArrt15(String arrt15) {
        this.arrt15 = arrt15;
    }


    @Column(name = "arrrt16")
    public String getArrt16() {
        return arrt16;
    }

    public void setArrt16(String arrt16) {
        this.arrt16 = arrt16;
    }
}