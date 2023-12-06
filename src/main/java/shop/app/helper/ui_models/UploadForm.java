package shop.app.helper.ui_models;

import lombok.Data;

import org.springframework.web.multipart.MultipartFile;


@Data
public class UploadForm {

    private String description;

    private MultipartFile file;

}
