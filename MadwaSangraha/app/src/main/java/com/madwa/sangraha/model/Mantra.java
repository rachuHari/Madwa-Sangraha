package com.madwa.sangraha.model;

/**
 * Created by admin on 10-01-2016.
 */
public class Mantra {

    int id;
    String title;
    int lang_id;
    int cat_id;
    String mantra_file_url;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLang_id() {
        return lang_id;
    }

    public void setLang_id(int lang_id) {
        this.lang_id = lang_id;
    }

    public int getCat_id() {
        return cat_id;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }

    public String getMantra_file_url() {
        return mantra_file_url;
    }

    public void setMantra_file_url(String mantra_file_url) {
        this.mantra_file_url = mantra_file_url;
    }
}
