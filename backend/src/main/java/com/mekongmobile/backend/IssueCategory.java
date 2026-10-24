
package com.mekongmobile.backend;

import jakarta.persistence.*;

@Entity
@Table(name = "issue_category")
public class IssueCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "name", nullable = false)
    private String name;

    public Long getCategoryId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }
}
