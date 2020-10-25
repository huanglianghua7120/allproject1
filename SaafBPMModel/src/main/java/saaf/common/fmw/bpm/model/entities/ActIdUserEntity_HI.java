package saaf.common.fmw.bpm.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * ActIdUserEntity_HI Entity Object
 * Wed Aug 23 11:33:54 CST 2017  Auto Generate
 */
@Entity
@Table(name = "act_id_user")
public class ActIdUserEntity_HI {
    private String id_;
    private Integer rev_;
    private String first_;
    private String last_;
    private String email_;
    private String pwd_;
    private String pictureId_;

    //	public void setAct_id_membershipEntity_HI(List<ActIdUserEntity_HI> Act_id_membershipEntity_HI) {
    //		this.Act_id_membershipEntity_HI = Act_id_membershipEntity_HI;
    //	}
    //
    //	@Column(name = "Act_id_membershipEntity_HI")	
    //	public List<ActIdUserEntity_HI> getAct_id_membershipEntity_HI() {
    //		return Act_id_membershipEntity_HI;
    //	}

    public void setId_(String id_) {
        this.id_ = id_;
    }

    @Id
    @GeneratedValue
    @Column(name = "id_", nullable = false, length = 64)
    public String getId_() {
        return id_;
    }

    public void setRev_(Integer rev_) {
        this.rev_ = rev_;
    }

    @Column(name = "rev_", nullable = true, length = 11)
    public Integer getRev_() {
        return rev_;
    }

    public void setFirst_(String first_) {
        this.first_ = first_;
    }

    @Column(name = "first_", nullable = true, length = 255)
    public String getFirst_() {
        return first_;
    }

    public void setLast_(String last_) {
        this.last_ = last_;
    }

    @Column(name = "last_", nullable = true, length = 255)
    public String getLast_() {
        return last_;
    }

    public void setEmail_(String email_) {
        this.email_ = email_;
    }

    @Column(name = "email_", nullable = true, length = 255)
    public String getEmail_() {
        return email_;
    }

    public void setPwd_(String pwd_) {
        this.pwd_ = pwd_;
    }

    @Column(name = "pwd_", nullable = true, length = 255)
    public String getPwd_() {
        return pwd_;
    }

    public void setPictureId_(String pictureId_) {
        this.pictureId_ = pictureId_;
    }

    @Column(name = "picture_id_", nullable = true, length = 64)
    public String getPictureId_() {
        return pictureId_;
    }
}
