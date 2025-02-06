package vn.vothien.ghost.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.vothien.ghost.domain.User;

@Repository
interface UserRepository extends JpaRepository<User, Long> {

}
