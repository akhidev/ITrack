package com.atz.pmd.itrack;

import java.util.List;

import com.atz.pmd.itrack.myproject.MyProjects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItrackJpaRepository extends JpaRepository<MyProjects, Long>{
    List<MyProjects> findByUsername(String username);
}
