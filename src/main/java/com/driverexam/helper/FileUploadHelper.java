package com.driverexam.helper;

import com.driverexam.common.Const;
import com.driverexam.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class FileUploadHelper {

    @Autowired
    private AppConfig appConfig;


    public String getNetUrlFromFile(File file) {
        return "http://" + Const.IP_PATH + ":" + appConfig.getPort() + appConfig.getServerPath() + appConfig.getStaticAccessPath().substring(0, appConfig.getStaticAccessPath().length() - 2) + file.getName();
    }

    public AppConfig getAppConfig() {
        return appConfig;
    }

    public void setAppConfig(AppConfig appConfig) {
        this.appConfig = appConfig;
    }
}
