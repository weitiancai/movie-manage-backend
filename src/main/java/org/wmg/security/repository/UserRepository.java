package org.wmg.security.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.wmg.security.model.User;

import java.util.Optional;

/**
 * @author wmg
 * @date 2024/6/18
 */
public interface UserRepository extends BaseMapper<User> {
//    public interface UserRepository extends JpaRepository<User, Long> {

//    @EntityGraph(attributePaths = "authorities")
    Optional<User> findOneWithAuthoritiesByUsername(String username);

//    @EntityGraph(attributePaths = "authorities")
    Optional<User> findOneWithAuthoritiesByEmailIgnoreCase(String email);

}
