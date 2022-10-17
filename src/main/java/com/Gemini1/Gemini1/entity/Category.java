package com.Gemini1.Gemini1.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity


@Table(name="category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int categoryId;

    @NotBlank(message = "Category name cannot be empty.")
    private String categoryName;

    private String categoryDescription;

    private boolean active=Boolean.TRUE;

    private boolean deleted=Boolean.FALSE;

    @CreationTimestamp
    @Column(updatable = false)
    LocalDate createDate;

    @UpdateTimestamp
    LocalDate updateDate;

}


