package com.GoDo.godo.utilities_pack.mapRoute;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WaypointCacheRepo extends JpaRepository<WaypointCache, Long> {
    Optional<WaypointCache> findByLatAndLon(double lat, double lon);
}

