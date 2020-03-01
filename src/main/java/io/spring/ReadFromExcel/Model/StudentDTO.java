package io.spring.ReadFromExcel.Model;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity
@Table(name = "Student")
public class StudentDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable=true)
    private  Long id;

    private String emailAddress;
    private String name;
    private String purchasedPackage;
    @Transient
    private MultipartFile file;

    public StudentDTO(String emailAddress, String name, String purchasedPackage) {
        this.emailAddress = emailAddress;
        this.name = name;
        this.purchasedPackage = purchasedPackage;
    }

    public StudentDTO() { }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPurchasedPackage() {
        return purchasedPackage;
    }

    public void setPurchasedPackage(String purchasedPackage) {
        this.purchasedPackage = purchasedPackage;
    }
    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}

