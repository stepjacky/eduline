package org.jackysoft.edu.form.bean;

import org.jackysoft.edu.entity.Violates;
import org.jackysoft.edu.formbean.ViolateResult;

import java.util.List;

public class ViolateBean {
    ViolateResult point;
    List<Violates> details;
    int current;

    public ViolateResult getPoint() {
        return point;
    }

    public void setPoint(ViolateResult point) {
        this.point = point;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public List<Violates> getDetails() {
        return details;
    }

    public void setDetails(List<Violates> details) {
        this.details = details;
    }
}
