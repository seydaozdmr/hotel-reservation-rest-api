package com.hotels.domain.model.hotel;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Description {
    @Id
    @GeneratedValue
    private int id;
    private String title;
    @Lob
    private String descriptionDetails;

    @OneToOne(mappedBy = "description")
    private Hotel hotel;

    public Description (){}

    public Description(String title, String descriptionDetails) {
        this.title = title;
        this.descriptionDetails = descriptionDetails;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescriptionDetails() {
        return descriptionDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Description that = (Description) o;
        return id == that.id && Objects.equals(title, that.title) && Objects.equals(descriptionDetails, that.descriptionDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, descriptionDetails);
    }

    @Override
    public String toString() {
        return "Description{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", descriptionDetails='" + descriptionDetails + '\'' +
                '}';
    }
}
