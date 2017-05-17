package com.chahoo.u4d.api.accounts;

import com.chahoo.u4d.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jjryu on 2017-02-07.
 */

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByLoginId(String loginId);//구현끝 : spring jpa가 자동적으로 구현해줌.
    Account findByUserName(String username);
}
