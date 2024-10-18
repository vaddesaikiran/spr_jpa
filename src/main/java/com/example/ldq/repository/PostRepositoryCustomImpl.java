package com.example.ldq.repository;

import com.example.ldq.entity.Post;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;

import java.util.ArrayList;
import java.util.List;

public class PostRepositoryCustomImpl implements PostRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Post> findPostsByDynamicCriteria(String title, Long userId) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();


        CriteriaQuery<Post> cq = cb.createQuery(Post.class);


        Root<Post> post = cq.from(Post.class);

 
        List<Predicate> predicates = new ArrayList<>();


        if (title != null && !title.isEmpty()) {
            predicates.add(cb.like(post.get("title"), "%" + title + "%"));
        }


        if (userId != null) {
            predicates.add(cb.equal(post.get("user").get("id"), userId));
        }


        cq.where(predicates.toArray(new Predicate[0]));


        return entityManager.createQuery(cq).getResultList();
    }
}
