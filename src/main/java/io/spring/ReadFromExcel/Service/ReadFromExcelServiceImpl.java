package io.spring.ReadFromExcel.Service;


import io.spring.ReadFromExcel.Model.StudentDTO;
import io.spring.ReadFromExcel.Repository.ReadFromExcelRepository;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@Service
@Transactional
public class ReadFromExcelServiceImpl  implements  ReadFromExcelService{
    @Autowired
    ReadFromExcelRepository readFromExcelRepository;

    @Override
    public boolean saveDataFromUploadFile(MultipartFile file) throws IOException {
        boolean isFlag = false;
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        assert extension != null;
        if(extension.equalsIgnoreCase("xlsx")){
            isFlag = readDataFromExcel(file);
        }
        return false;
    }

    private boolean readDataFromExcel(MultipartFile file) throws IOException {
        Workbook workbook = getWorkBook(file);
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row>  rows = sheet.iterator();
        rows.next();
            while(rows.hasNext()){
                Row row = rows.next();
                StudentDTO student = new StudentDTO();
                if(row.getCell(1).getCellType() == Cell.CELL_TYPE_STRING){
                    student.setName(row.getCell(1).getStringCellValue());
                }
                if(row.getCell(2).getCellType() == Cell.CELL_TYPE_STRING){
                    student.setEmailAddress(row.getCell(2).getStringCellValue());
                }
                if(row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING){
                    student.setPurchasedPackage(row.getCell(3).getStringCellValue());
                }
                readFromExcelRepository.save(student);

            }
        return  true;
    }

    private Workbook getWorkBook(MultipartFile file) throws IOException {
        Workbook workbook = null;
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        assert extension != null;
        if(extension.equalsIgnoreCase("xlsx")){
             workbook = new XSSFWorkbook(file.getInputStream());
        }
        return workbook;
    }

    @Override
    public List<StudentDTO> findAll() {
        return (List<StudentDTO>) readFromExcelRepository.findAll();
    }
}
