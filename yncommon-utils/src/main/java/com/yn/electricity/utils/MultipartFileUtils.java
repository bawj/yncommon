package com.yn.electricity.utils;

import cn.hutool.Hutool;
import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.SecureUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author admin
 * Date 2021/11/17 10:21
 * Description
 **/
@Slf4j
public class MultipartFileUtils {


    public static String saveImg(MultipartFile multipartFile, String path) {
        String year = String.valueOf(DateUtil.thisYear());
        String month = String.valueOf(DateUtil.thisMonth() + 1);
        String day = String.valueOf(DateUtil.thisDayOfMonth());

        String fileFullPath = path + "/" + year + "/" + month + "/" + day + "/";
        File upload = new File(fileFullPath);
        if (!upload.exists()) {
            boolean mkdirs = upload.mkdirs();
        }
        //图片保存路径
        String absolutePath = upload.getAbsolutePath();
        //获取文件名
        String fileName = multipartFile.getOriginalFilename();
        if (fileName != null) {
            //获取文件后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            //图片重命名
            String s = UUID.randomUUID() + "-" + System.currentTimeMillis();
            fileName = absolutePath + "\\" + s + suffixName;
            log.info("#MultipartFileUtils.saveImg# 图片重命名名称 = " + fileName);
            //保存文件
            File saveFile = new File(fileName);
            try {
                multipartFile.transferTo(saveFile);
                return fileFullPath + s + suffixName;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public static String getUserDir(){
        String property = System.getProperty("user.dir");
        log.info("property = {}", property);
        if (property.contains(":")) {
            String[] split = property.split(":");
            return split[0] + ":/";
        }
        return null;
    }

}
