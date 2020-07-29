package com.openclassrooms.escalade.dao;

import com.openclassrooms.escalade.entities.TopoUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository to handle TopoUser DAO operations
 */
public interface TopoUserRepository extends JpaRepository<TopoUser, Long> {
    List<TopoUser> findAllByTopoIdAndOwnerId(Long topoId, Long ownerId);
    List<TopoUser> findAllByOwnerId(Long ownerId);
    List<TopoUser> findAllByTopoId(Long topoId);
    List<TopoUser> findAllByTenantId(Long tenantId);
}
