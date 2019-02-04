package com.driverexam.action;

import com.driverexam.common.BaseResponse;
import com.driverexam.common.Const;
import com.driverexam.helper.FileUploadHelper;
import com.driverexam.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/file")
public class FileUploadAction {

    @Autowired
    FileUploadHelper fileUploadHelper;

    @PostMapping("/upload")
    public Object upload(@RequestParam MultipartFile file) {
        BaseResponse baseResponse = new BaseResponse();
        try {
            File newFile = new File(Const.FILE_PATH + "/" + System.currentTimeMillis()+"." + FileUtils.getFileExtension(file.getOriginalFilename()));
            FileUtils.createOrExistsDir(newFile.getParentFile());
            file.transferTo(newFile);
            baseResponse.setData(fileUploadHelper.getNetUrlFromFile(newFile));
            baseResponse.setMsg("上传成功");
        } catch (IOException e) {
            baseResponse.setMsg("上传失败");
            baseResponse.setCode(0);
        }
        return baseResponse;
    }
}
