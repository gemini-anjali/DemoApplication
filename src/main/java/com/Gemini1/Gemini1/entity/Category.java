package com.Gemini1.Gemini1.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name="category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int categoryId;
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


