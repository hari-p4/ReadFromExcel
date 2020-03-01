package io.spring.ReadFromExcel.Repository;

import io.spring.ReadFromExcel.Model.StudentDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReadFromExcelRepository extends JpaRepository<StudentDTO, Long> {

}
