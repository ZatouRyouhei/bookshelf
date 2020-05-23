package entity;

import java.io.Serializable;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ジャンル
 * @author ryouhei
 */
@Entity
@Table(name="t_genre")
@Cacheable(false)
public class Genre implements Serializable {
    public static final int SIZE_NAME = 10;
    
    @Id
    private Integer id;
    @Column(length=SIZE_NAME)
    private String name;

    public Genre() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
