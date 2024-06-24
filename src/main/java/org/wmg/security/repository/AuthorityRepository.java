package org.wmg.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wmg.security.model.Authority;

/**
 * @author wmg
 * @date 2024/6/18
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
