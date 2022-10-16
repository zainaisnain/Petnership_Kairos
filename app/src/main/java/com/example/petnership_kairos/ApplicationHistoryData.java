package com.example.petnership_kairos;

public class ApplicationHistoryData {
    private String apphistoName;
    private String apphistoStatus;
    private Integer apphistoImage;

    public ApplicationHistoryData(String apphistoName, String apphistoStatus, Integer apphistoImage) {
        this.apphistoName = apphistoName;
        this.apphistoStatus = apphistoStatus;
        this.apphistoImage = apphistoImage;
    }

    public String getapphistoName() {
        return apphistoName;
    }
    public String getapphistoStatus() {
        return apphistoStatus;
    }
    public Integer getapphistoImage() {
        return apphistoImage;
    }

    public void setapphistoName(String apphistoName) {
        this.apphistoName = apphistoName;
    }

    public void setapphistoStatus(String apphistoStatus) {
        this.apphistoStatus = apphistoStatus;
    }

    public void setapphistoImage(Integer apphistoImage) {
        this.apphistoImage = apphistoImage;
    }
}
