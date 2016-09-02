package com.example.latoris.recyclerviewpractise;

/**
 * Created by Latoris on 2016/8/31.
 */
public class Photo {
        private int img;
        private String title;

        public Photo(int img, String title) {
            this.img = img;
            this.title = title;
        }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
