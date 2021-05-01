package com.example.homeautomation.repositories;

import com.example.homeautomation.models.DeviceComposite;
import com.example.homeautomation.models.Devices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepo extends JpaRepository<Devices, DeviceComposite> {
    @Query(value = "select case when count(id)>0 then true else false end from devices where mac_address = :mac_address and user_id  =  :user_id", nativeQuery = true)
    Integer existsById(String mac_address, int user_id);

    @Query(value = "select IFNULL(max(id)+1,1) from devices", nativeQuery = true)
    Integer maxId();

    @Query(value = "select * from devices where user_id = :user_id and parent is null",nativeQuery = true)
    List<Devices> getDevices_forParent(int user_id);

    @Query(value = "select * from devices where user_id = :user_id and parent is not null and parent = :parent",nativeQuery = true)
    List<Devices> getSharedDevices(int user_id, int parent);

    @Query(value = "select d.mac_address, u.email from devices d join user u on u.id =d.user_id  where d.user_id <> :user_id and d.parent = :user_id", nativeQuery = true)
    List<Object[]> sharedDevices(int user_id);
}
