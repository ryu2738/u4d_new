package com.chahoo.u4d.api.buildInfo;

import com.chahoo.u4d.entity.Qr;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jjryu on 2017-02-07.
 */

public interface BuildInfoRepository extends JpaRepository<Qr, Long> {
}
