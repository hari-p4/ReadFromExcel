package io.spring.ReadFromExcel.Controller;

import io.spring.ReadFromExcel.Model.StudentDTO;
import io.spring.ReadFromExcel.Service.ReadFromExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
public class ReadFromExcelController {


    @Autowired
    ReadFromExcelService readFromExcelService;
    @GetMapping(value = "/")
    public String home(Model model){
        model.addAttribute("student", new StudentDTO());
        List<StudentDTO> studentDTOList = readFromExcelService.findAll();

        model.addAttribute("students", studentDTOList);
        return "view/students";
    }
    @PostMapping(value = "/fileupload")
    public String uploadFile(@ModelAttribute StudentDTO student , RedirectAttributes redirectAttributes) throws IOException {
        boolean isFlag = readFromExcelService.saveDataFromUploadFile(student.getFile());

        return "redirect:/";
    }

}
