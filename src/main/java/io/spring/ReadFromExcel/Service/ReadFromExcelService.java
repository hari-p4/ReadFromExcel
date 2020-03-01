package io.spring.ReadFromExcel.Service;

import io.spring.ReadFromExcel.Model.StudentDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ReadFromExcelService {
       boolean saveDataFromUploadFile(MultipartFile file) throws IOException;

     List<StudentDTO> findAll();
}
