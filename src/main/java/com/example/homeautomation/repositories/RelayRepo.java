package com.example.homeautomation.repositories;


import com.example.homeautomation.models.Relay;
import com.example.homeautomation.models.RelayComposite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface RelayRepo extends JpaRepository<Relay, RelayComposite> {
    @Query(value = "select * from relay where device_mac = :mac_address",nativeQuery = true)
    List<Relay> getDevices_relays(String mac_address);

    @Modifying
    @Transactional
    @Query(value = "update relay set status = :status, mod_time = :mod_time where device_mac = :mac and relay_name  = :relay_name", nativeQuery = true)
    void setRelayOnOff(String mac, String relay_name, int status, String mod_time);
}
