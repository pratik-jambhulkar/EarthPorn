
package com.example.pratik.earthporn.beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Data implements Serializable{

    @SerializedName("modhash")
    private String modhash;
    @SerializedName("children")
    private List<Child> children = new ArrayList<Child>();
    @SerializedName("after")
    private String after;
    @SerializedName("before")
    @Expose
    private Object before;

    /**
     * 
     * @return
     *     The modhash
     */
    public String getModhash() {
        return modhash;
    }

    /**
     * 
     * @param modhash
     *     The modhash
     */
    public void setModhash(String modhash) {
        this.modhash = modhash;
    }

    /**
     * 
     * @return
     *     The children
     */
    public List<Child> getChildren() {
        return children;
    }

    /**
     * 
     * @param children
     *     The children
     */
    public void setChildren(List<Child> children) {
        this.children = children;
    }

    /**
     * 
     * @return
     *     The after
     */
    public String getAfter() {
        return after;
    }

    /**
     * 
     * @param after
     *     The after
     */
    public void setAfter(String after) {
        this.after = after;
    }

    /**
     * 
     * @return
     *     The before
     */
    public Object getBefore() {
        return before;
    }

    /**
     * 
     * @param before
     *     The before
     */
    public void setBefore(Object before) {
        this.before = before;
    }

}
