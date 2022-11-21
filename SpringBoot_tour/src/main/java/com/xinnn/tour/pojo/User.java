package com.xinnn.tour.pojo;

/**
 * 用户实体类
 */
public class User {
    private Integer id;
    private String username;
    private String passwd;
    private String authorImg;

    public User(Integer id, String username, String passwd, String authorImg) {
        this.id = id;
        this.username = username;
        this.passwd = passwd;
        this.authorImg = authorImg;
    }

    public User(Integer id, String username, String authorImg) {
        this.id = id;
        this.username = username;
        this.authorImg = authorImg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getAuthorImg() {
        return authorImg;
    }

    public void setAuthorImg(String authorImg) {
        this.authorImg = authorImg;
    }
}
