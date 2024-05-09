package com.ega.repositories;

import com.ega.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByAccountNumber(String accountNumber);
    Account findByUser_Id(Long id);
}
