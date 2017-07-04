package org.jackysoft.edu.entity;

import org.mongodb.morphia.annotations.Embedded;

@Embedded
public class NameValue {

    String name;
    String value;
    String text;

    public NameValue() {
    }

    /**
     * @param  name 名称
     * @param  value 内部值
     * */
    public NameValue(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public NameValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NameValue nameValue = (NameValue) o;

        return value != null ? value.equals(nameValue.value) : nameValue.value == null;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}
