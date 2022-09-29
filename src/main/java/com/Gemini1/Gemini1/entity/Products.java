package com.Gemini1.Gemini1.entity;





import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.time.LocalDate;


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Entity


    public class Products {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int productId;
        private String productName;
        private String productDescription;
        private int price;
        private boolean active=Boolean.TRUE;
        private boolean deleted=Boolean.FALSE;
        @CreationTimestamp
        @Column(updatable = false)
        LocalDate createDate;
        @UpdateTimestamp
        LocalDate updateDate;
        @ManyToOne
        @JoinColumn(name = "category_id")
        private Category category;


    }


