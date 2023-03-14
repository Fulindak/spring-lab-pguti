package com.example.springkuzmin.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.Hibernate;
import java.util.Objects;


@Getter
@Setter
@ToString
@Accessors(chain = true)
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @Column(name = "name", nullable = false)
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Role role = (Role) o;
        return getName() != null && Objects.equals(getName(), role.getName());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
