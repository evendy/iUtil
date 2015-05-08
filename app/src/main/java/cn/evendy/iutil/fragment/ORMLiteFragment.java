package cn.evendy.iutil.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cn.evendy.iutil.R;
import cn.evendy.iutil.dao.ArticleDao;
import cn.evendy.iutil.dao.UserDao;
import cn.evendy.iutil.module.Article;
import cn.evendy.iutil.module.User;
import cn.evendy.iutil_lib.base.BaseFragment;

/**
 * Created by evendy on 2015/5/6.
 */
public class ORMLiteFragment extends BaseFragment {
    private TextView textView;
    private ArticleDao articleDao;
    private UserDao userDao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ormlite, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        textView = findViewById(R.id.text);

        userDao = new UserDao(getContext());
        articleDao = new ArticleDao(getContext());

        addUser("zhangsan", 18);
        addArticle("god like", "zhangsan");
        addArticle("happy day", "zhangsan");
        addArticle("uuuuuuu", "zhangsan");
        addArticle("mm", "lisi");

//        queryArticleById(1);
//        queryArticleByUserId(0);
        queryArticleAndUserInfoById(3);

    }

    private void queryArticleByUserId(int userId) {
        List<Article> articles = articleDao.listByUserId(userId);
        StringBuffer sb = new StringBuffer();
        for (Article article : articles) {
            sb.append(article + "\n");
        }
        textView.setText(sb.toString());
    }

    private void queryArticleById(int articleId) {
        Article article = articleDao.getArticleById(articleId);
        textView.setText(article.toString());
    }

    private void queryArticleAndUserInfoById(int articleId) {
        Article article = articleDao.getArticleWithUserInfoById(articleId);
        textView.setText(article.toString());
    }

    private void addUser(String name, int age) {
        User user = new User();
        user.setName(name);
        user.setAge(age);
        userDao.addUser(user);
    }

    private void addArticle(String title, String userName) {
        User user = userDao.queryUserByName(userName);
        Article article = new Article();
        article.setTitle(title);
        article.setUser(user);
        articleDao.addArticle(article);
    }
}
