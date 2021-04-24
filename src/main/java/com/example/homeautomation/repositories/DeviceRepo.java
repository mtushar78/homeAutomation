package com.example.homeautomation.repositories;

import com.example.homeautomation.models.DeviceComposite;
import com.example.homeautomation.models.Devices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepo extends JpaRepository<Devices, DeviceComposite> {
    @Query(value = "select case when count(id)>0 then true else false end from devices where mac_address = :mac_address and user_id  =  :user_id", nativeQuery = true)
    Integer existsById(String mac_address, int user_id);

    @Query(value = "select IFNULL(max(id)+1,1) from devices", nativeQuery = true)
    Integer maxId();
}
