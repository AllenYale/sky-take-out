package com.sky.controller.admin;

import com.sky.annotation.AutoFill;
import com.sky.constant.MessageConstant;
import com.sky.result.Result;
import com.sky.utils.AliOssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * ClassName: CommonController
 * PackageName: com.sky.controller.admin
 * Description:
 *
 * @Author: Hanyu
 * @Date: 23/12/22 - 14:01
 * @Version: v1.0
 */
@Slf4j
@Api("通用控制器")
@RestController
@RequestMapping("admin/common")
public class CommonController {
    @Autowired
    AliOssUtil aliOssUtil;

    @PostMapping("upload")
    @ApiOperation("文件上传")
    public Result uploadFile(MultipartFile file) {
        log.info("文件上传：{}", file);

        String originalFilename = file.getOriginalFilename();
        String fileType = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFileName = UUID.randomUUID() + fileType;
        try {
            String upload = aliOssUtil.upload(file.getBytes(), newFileName);
            return Result.success(upload);
        } catch (IOException e) {
//            throw new RuntimeException(e);
            log.error("文件上传失败");
            return Result.error(MessageConstant.UPLOAD_FAILED);
        }
    }
}




