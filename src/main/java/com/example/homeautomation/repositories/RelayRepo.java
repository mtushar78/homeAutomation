package com.example.homeautomation.repositories;
import com.example.homeautomation.models.Devices;
import com.example.homeautomation.models.Relay;
import com.example.homeautomation.models.RelayComposite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelayRepo extends JpaRepository<Relay, RelayComposite> {
    @Query(value = "select * from relay where device_mac = :mac_address",nativeQuery = true)
    List<Relay> getDevices_relays(String mac_address);
}
