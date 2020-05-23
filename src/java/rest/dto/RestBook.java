package rest.dto;

import entity.Book;
import entity.BookKey;
import entity.Genre;
import java.text.SimpleDateFormat;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ryouhei
 */
@XmlRootElement
public class RestBook {
    // キー
    private BookKey key;
    // タイトル
    private String title;
    // 著者
    private String author;
    // 値段
    private Integer price;
    // 出版社
    private String publisher;
    // 出版日yyyy-MM-dd
    private String published;
    // 読了日yyyy-MM-dd
    private String completeDate;
    // ジャンル
    private Genre genre;
    // 感想
    private String memo;
    // 評価
    private Integer rate;
    // 画像URL
    private String imgUrl;
    // 情報ページURL
    private String infoUrl;

    public RestBook() {
    }
    
    public RestBook(Book book) {
        this.key = book.getKey();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.price = book.getPrice();
        this.publisher = book.getPublisher();
        this.published = new SimpleDateFormat("yyyy-MM-dd").format(book.getPublished());
        this.completeDate = new SimpleDateFormat("yyyy-MM-dd").format(book.getCompleteDate());
        this.genre = book.getGenre();
        this.memo = book.getMemo();
        this.rate = book.getRate();
        this.imgUrl = book.getImgUrl();
        this.infoUrl = book.getInfoUrl();
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

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public String getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(String completeDate) {
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
