package ru.aston.entity;

import lombok.*;

import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Project {
    private Long id;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private Long ownerId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Project project)) return false;
        return Objects.equals(getId(), project.getId()) && Objects.equals(getName(), project.getName()) && Objects.equals(getDescription(), project.getDescription()) && Objects.equals(getStartDate(), project.getStartDate()) && Objects.equals(getEndDate(), project.getEndDate()) && Objects.equals(getOwnerId(), project.getOwnerId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), getStartDate(), getEndDate(), getOwnerId());
    }
}
