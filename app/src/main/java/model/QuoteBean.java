package model;

/**
 * Created by 89003337 on 2017/3/22.
 */

public class QuoteBean {


    /**
     * ID : 1475
     * title : Sabo Tercero
     * content : <p>We are all designers, the difference is that only a few of us do it full-time.</p>

     * link : https://quotesondesign.com/sabo-tercero/
     */

    private int ID;
    private String title;
    private String content;
    private String link;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "QuoteBean{" +
                "ID=" + ID +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
