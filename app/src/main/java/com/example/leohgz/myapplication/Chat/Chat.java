package com.example.leohgz.myapplication.Chat;

/**
 * Created by Leonardo on 22/7/2016.
 */
public class Chat {
    private String text;
    private Long user_id;
    private boolean owns=false;

    public void setChatUser(String currentUser) {
        if (currentUser.trim().equals(user_id.toString())){
            owns=true;
        }

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Chat(){

    }

    public boolean owns(){
        return owns;
    }

}
