
package com.example.pratik.earthporn.beans;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Image implements Serializable{

    @SerializedName("source")
    private Source source;
    @SerializedName("resolutions")
    private List<Resolution> resolutions = new ArrayList<Resolution>();
    @SerializedName("variants")
    private Variants variants;
    @SerializedName("id")
    private String id;

    /**
     * 
     * @return
     *     The source
     */
    public Source getSource() {
        return source;
    }

    /**
     * 
     * @param source
     *     The source
     */
    public void setSource(Source source) {
        this.source = source;
    }

    /**
     * 
     * @return
     *     The resolutions
     */
    public List<Resolution> getResolutions() {
        return resolutions;
    }

    /**
     * 
     * @param resolutions
     *     The resolutions
     */
    public void setResolutions(List<Resolution> resolutions) {
        this.resolutions = resolutions;
    }

    /**
     * 
     * @return
     *     The variants
     */
    public Variants getVariants() {
        return variants;
    }

    /**
     * 
     * @param variants
     *     The variants
     */
    public void setVariants(Variants variants) {
        this.variants = variants;
    }

    /**
     * 
     * @return
     *     The id
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(String id) {
        this.id = id;
    }

}
