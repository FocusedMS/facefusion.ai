package ai.facefusion.repository;

import ai.facefusion.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    // Basic CRUD operations are automatically provided by JpaRepository
} 