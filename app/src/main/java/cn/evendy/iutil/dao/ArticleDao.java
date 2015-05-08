package cn.evendy.iutil.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.util.List;

import cn.evendy.iutil.module.Article;
import cn.evendy.iutil.module.User;

/**
 * @author: evendy
 * @time: 2015/5/6 15:50
 */
public class ArticleDao {
    private Context mContext;
    private Dao<Article, Integer> articleDao;
    private DatabaseHelper helper;

    public ArticleDao(Context context) {
        this.mContext = context;
        try {
            helper = DatabaseHelper.getHelper(mContext);
            articleDao = helper.getDao(Article.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean addArticle(Article article) {
        int result = 0;
        try {
            result = articleDao.create(article);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result > 0 ? true : false;
    }

    public Article getArticleWithUserInfoById(int id) {
        Article article = null;
        try {
            article = articleDao.queryForId(id);
            helper.getDao(User.class).refresh(article.getUser());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return article;
    }

    public Article getArticleById(int id) {
        Article article = null;
        try {
            article = articleDao.queryForId(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return article;
    }

    public List<Article> listByUserId(int userId) {
        List<Article> articles = null;
        try {
            articles = articleDao.queryBuilder().where().eq("user_id", userId).query();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return articles;
    }
}
