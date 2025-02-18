package com.test.demo.criteria;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.test.demo.model.User;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;

@Data
public class UserCriteria {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;

    public Specification<User> getSpecification() {
        return new Specification<User>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (getId() != null) {
                    predicates.add(cb.equal(root.get("id"), getId()));
                }
                if (getName() != null) {
                    predicates.add(cb.equal(root.get("name"), getName()));
                }
                if (getEmail() != null) {
                    predicates.add(cb.equal(root.get("email"), getEmail()));
                }
                if (getPhone() != null) {
                    predicates.add(cb.equal(root.get("phone"), getPhone()));
                }
                if (getAddress() != null) {
                    predicates.add(cb.equal(root.get("address"), getAddress()));
                }

                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}