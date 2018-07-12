package com.liang.tind.www.tindtest.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * created by Administrator
 * <p>
 * date 2018/7/11
 */
@Entity
public class GameBean {

    /**
     * id : 1
     * name : learn
     * version : 3
     * url : http://oa-statics.caihonggou.com/3334
     */
    @Id(autoincrement = true)
    private Long _id;

    @Unique
    @NotNull
    private Long id;

    @Unique
    @NotNull
    private String name;

    private int version;

    @Unique
    @NotNull
    private String url;

    @Generated(hash = 1539776115)
    public GameBean(Long _id, @NotNull Long id, @NotNull String name, int version,
            @NotNull String url) {
        this._id = _id;
        this.id = id;
        this.name = name;
        this.version = version;
        this.url = url;
    }

    @Generated(hash = 1942203655)
    public GameBean() {
    }

    public Long get_id() {
        return this._id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVersion() {
        return this.version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "GameBean{" +
                "_id=" + _id +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", version=" + version +
                ", url='" + url + '\'' +
                '}';
    }
}
