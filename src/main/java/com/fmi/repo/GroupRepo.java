package com.fmi.repo;

import com.fmi.domain.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepo extends JpaRepository<Group, Long> {

    Group findOneByName(String name);
}
