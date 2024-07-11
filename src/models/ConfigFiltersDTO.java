package models;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;

import org.springframework.format.annotation.DateTimeFormat;

public class ConfigFiltersDTO {
	private String category;
	private String screentype;
    private String screensize;
    private String scanningfrequency;
    private String resolution;
    private String utilities;
    private String operatingsystem;

    // Getters và Setters


    public String getScreentype() {
        return screentype;
    }

    public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setScreentype(String screentype) {
        this.screentype = screentype;
    }

    public String getScreensize() {
        return screensize;
    }

    public void setScreensize(String screensize) {
        this.screensize = screensize;
    }

    public String getScanningfrequency() {
        return scanningfrequency;
    }

    public void setScanningfrequency(String scanningfrequency) {
        this.scanningfrequency = scanningfrequency;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getUtilities() {
        return utilities;
    }

    public void setUtilities(String utilities) {
        this.utilities = utilities;
    }

    public String getOperatingsystem() {
        return operatingsystem;
    }

    public void setOperatingsystem(String operatingsystem) {
        this.operatingsystem = operatingsystem;
    }



}