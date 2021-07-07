package com.lambdaschool.school.model;

import com.lambdaschool.school.exceptions.ValidationError;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResourceNotFound {
    //error message title
    private String title;

    //404, etc
    private int status;

    //what the Exception was
    private String detail;

    //write format of the timestamp
    private String timestamp;

    //System msg
    private String developermessage;

    //Return a string of errors in case there's multiple
    private Map<String, List<ValidationError>> errors = new HashMap<>();


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = new SimpleDateFormat("dd MMM yyyy HH:mm:ss:SSS").format(new Date(timestamp));
    }

    public String getDevelopermessage() {
        return developermessage;
    }

    public void setDevelopermessage(String developermessage) {
        this.developermessage = developermessage;
    }

    public Map<String, List<ValidationError>> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, List<ValidationError>> errors) {
        this.errors = errors;
    }
}
