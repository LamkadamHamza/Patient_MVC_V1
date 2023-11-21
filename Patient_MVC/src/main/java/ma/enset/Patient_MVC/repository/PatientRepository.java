package ma.enset.Patient_MVC.repository;

import ma.enset.Patient_MVC.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {


    Page<Patient> findByNomContains(String keyword, Pageable pageable);
}
