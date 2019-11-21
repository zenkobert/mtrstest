package com.zezenk.mtrstest.repository;

import com.zezenk.mtrstest.model.TbUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<TbUser, Long> {

    Optional<TbUser> findByEmail(String email);

    Optional<TbUser> findByMobileNumber(String mobileNumber);
}
