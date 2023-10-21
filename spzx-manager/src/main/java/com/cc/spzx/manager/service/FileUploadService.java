package com.cc.spzx.manager.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {


    /**
     * desc 上传文件
     * date 2023/10/21
     * @author cc
     * @return String
     **/

    String fileUpload(MultipartFile file);

}
