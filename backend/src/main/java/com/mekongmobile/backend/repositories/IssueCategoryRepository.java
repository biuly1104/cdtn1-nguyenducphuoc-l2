
package com.mekongmobile.backend.repositories;

import com.mekongmobile.backend.IssueCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueCategoryRepository
        extends JpaRepository<IssueCategory, Long> {
}
