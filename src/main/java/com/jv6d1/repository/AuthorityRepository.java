package com.jv6d1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jv6d1.entity.Account;
import com.jv6d1.entity.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
	@Query("SELECT DISTINCT a FROM Authority a Where a.account IN ?1" )
	List<Authority> authorritiesOf(List<Account> accounts);

}
