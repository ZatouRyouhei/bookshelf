package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 本
 * @author ryouhei
 */
@NamedQueries({
    @NamedQuery (
            name = Book.BOOK_GETMAXSEQ,
            query =   "SELECT MAX(b.key.seqNo)"
                    + "  FROM Book b"
                    + " WHERE b.key.userId = :userId"
    )
})
@Entity
@Table(name="t_book")
@Cacheable(false)
public class Book implements Serializable {
    public static final String BOOK_GETMAXSEQ = "BOOK_GETMAXSEQ";
    
    public static final int SIZE_TITLE = 50;
    public static final int SIZE_AUTHOR = 50;
    public static final int SIZE_PUBLISHER = 50;
    public static final int SIZE_MEMO = 500;
    public static final int SIZE_IMGURL = 500;
    public static final int SIZE_INFOURL = 500;
    
    @EmbeddedId
    private BookKey key;
    // タイトル
    @Column(length=SIZE_TITLE)
    private String title;
    // 著者
    @Column(length=SIZE_AUTHOR)
    private String author;
    // 値段
    private Integer price;
    // 出版社
    @Column(length=SIZE_PUBLISHER)
    private String publisher;
    // 出版日
    @Temporal(TemporalType.DATE)
    private Date published;
    // 読了日
    @Temporal(TemporalType.DATE)
    private Date completeDate;
    // ジャンル
    private Genre genre;
    // 感想
    @Column(length=SIZE_MEMO)
    private String memo;
    // 評価
    private Integer rate;
    // 画像URL
    @Column(length=SIZE_IMGURL)
    private String imgUrl;
    // 情報ページURL
    @Column(length=SIZE_INFOURL)
    private String infoUrl;

    public Book() {
    }

    public BookKey getKey() {
        return key;
    }

    public void setKey(BookKey key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getPublished() {
        return published;
    }

    public void setPublished(Date published) {
        this.published = published;
    }

    public Date getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(Date completeDate) {
        this.completeDate = completeDate;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getInfoUrl() {
        return infoUrl;
    }

    public void setInfoUrl(String infoUrl) {
        this.infoUrl = infoUrl;
    }
    
    
}
