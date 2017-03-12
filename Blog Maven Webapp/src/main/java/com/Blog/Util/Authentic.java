package com.Blog.Util;
import javax.mail.PasswordAuthentication;
import javax.mail.Authenticator;
/*
 * 邮件的验证类
 */
public class Authentic extends Authenticator{

    public Authentic() {
    }

    public Authentic(String name,String password)
    {
        this.setUsername(name);
        this.setPwd(password);
    }
    //这里是我重载的构造方法，你也可以不去重载它，但是这样写会有一定的灵活性

    private String username ="";    //大多数是你邮件@前面的部分，例如hackq@163.com即填hack
    private String pwd = "";    	//实际上不用填，实际上调用时就会传
    //验证邮箱的用户名和密码
   protected PasswordAuthentication getPasswordAuthentication() {
          return new PasswordAuthentication(getUsername(), getPwd());
         }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPwd() {
        return pwd;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
