package com.example.latoris.myword.Bean;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/**
 * Created by Latoris on 2017/10/2.
 */

public class News implements Parcelable {
    private String time;
    private List<String> pics_url;
    private String video_url;
    private String title;
    private String content;
    private String url;
    private Bitmap pic = null;
    private String pic_s;

    public String getPic_s() {
        return pic_s;
    }

    public void setPic_s(String pic_s) {
        this.pic_s = pic_s;
    }

    public Bitmap getPic() {
        return pic;
    }

    public void setPic(Bitmap pic) {
        this.pic = pic;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public News(){

    }
    public News(Parcel dest) {
        this.time = dest.readString();
        this.video_url = dest.readString();
        this.title = dest.readString();
        this.content = dest.readString();
        this.url = dest.readString();
        this.pic_s = dest.readString();
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<String> getPics_url() {
        return pics_url;
    }

    public void setPics_url(List<String> pics_url) {
        this.pics_url = pics_url;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
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

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    private String tags;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(time);
        dest.writeString(video_url);
        dest.writeString(title);
        dest.writeString(content);
        dest.writeString(tags);
        dest.writeString(pic_s);
    }

    public static final Parcelable.Creator<News> CREATOR = new Parcelable.Creator<News>(){

        @Override
        public News createFromParcel(Parcel source) {
            return new News(source);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };
}
