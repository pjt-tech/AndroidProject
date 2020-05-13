package com.kye.myscretmemo;

import java.io.Serializable;

public class MemoItem implements Serializable {
    String contents,friendName,friendphone,timeStemp,imagePath;

    public MemoItem(String contents, String friendName, String friendphone, String timeStemp, String imagePath) {
        this.contents = contents;
        this.friendName = friendName;
        this.friendphone = friendphone;
        this.timeStemp = timeStemp;
        this.imagePath = imagePath;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    public String getFriendphone() {
        return friendphone;
    }

    public void setFriendphone(String friendphone) {
        this.friendphone = friendphone;
    }

    public String getTimeStemp() {
        return timeStemp;
    }

    public void setTimeStemp(String timeStemp) {
        this.timeStemp = timeStemp;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
