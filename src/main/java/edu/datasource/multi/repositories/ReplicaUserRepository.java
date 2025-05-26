package edu.datasource.multi.repositories;

import edu.datasource.multi.configurations.ReplicaDataSourceRepository;
import edu.datasource.multi.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@ReplicaDataSourceRepository
public interface ReplicaUserRepository extends JpaRepository<AppUser, String> {
    public Optional<AppUser> findById(String id);

    public List<AppUser> findAll();
}
