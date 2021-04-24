package com.example.homeautomation.repositories;
import com.example.homeautomation.models.Relay;
import com.example.homeautomation.models.RelayComposite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelayRepo extends JpaRepository<Relay, RelayComposite> {
}
