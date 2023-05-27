package ua.lviv.iot.docslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.docslab.model.Driver;

@Repository
public interface DriverDao extends JpaRepository<Driver, Long> {
}
