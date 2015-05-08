package cn.evendy.iutil.module;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


/**
 * @author: evendy
 * @time: 2015/5/6 15:42
 */
@DatabaseTable(tableName = "tab_article")
public class Article {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "title")
    private String title;
    @DatabaseField(canBeNull = true, foreign = true, columnName = "user_id")
    private User user;

    public Article() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "article[" + "id=" + id + ",title=" + title + "," + user + "]";
    }
}
