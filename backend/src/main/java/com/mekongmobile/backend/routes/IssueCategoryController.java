
package com.mekongmobile.backend.routes;

import com.mekongmobile.backend.IssueCategory;
import com.mekongmobile.backend.repositories.IssueCategoryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/issue-categories")
public class IssueCategoryController {

    private final IssueCategoryRepository repository;

    public IssueCategoryController(IssueCategoryRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<IssueCategory> getAll() {
        return repository.findAll();
    }
}
