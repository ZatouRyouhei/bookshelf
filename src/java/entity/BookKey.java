package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * 本の主キー
 * @author ryouhei
 */
@Embeddable
public class BookKey implements Serializable {
    public static final int SIZE_USERID = 20;
    
    @Column(length=SIZE_USERID)
    private String userId;
    private Integer seqNo;

    public BookKey() {
    }

    public BookKey(String userId, Integer seqNo) {
        this.userId = userId;
        this.seqNo = seqNo;
    }
    
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
    }
}
