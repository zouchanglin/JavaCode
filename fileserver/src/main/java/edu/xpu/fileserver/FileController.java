package edu.xpu.fileserver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

/**
 * 图片上传控制器
 */
@Slf4j
@Controller
@RequestMapping("/file")
public class FileController {
    //private final static String imgPath = "C:\\Users\\15291\\Desktop\\img";
    //private final static String projectUrl = "http://127.0.0.1:8080/fileserver";

    private final static String imgPath = "/root/important/img";
    private final static String projectUrl = "http://lslm.live:8090/fileserver";

    @GetMapping("/fileDownload")
    public ResponseEntity<FileSystemResource> file(@RequestParam("fileUrl") String fileName) {
        return export(new File(imgPath + File.separator + fileName));
    }

    @ResponseBody
    @PostMapping(value = "/fileUpload")
    public ResultVO fileUpload(@RequestParam(value = "file") MultipartFile file) {

            if (file == null) {
                log.error("[FileController] 文件为空");
            }
            assert file != null;
            String fileName = file.getOriginalFilename();  // 文件名
            assert fileName != null;
            String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
            fileName = UUID.randomUUID() + suffixName; // 新文件名
            File dest = new File(imgPath + File.separator + fileName);
            if (!dest.getParentFile().exists()) {
                boolean mkdirs = dest.getParentFile().mkdirs();
                assert mkdirs;
            }
            try {
                file.transferTo(dest);
            } catch (IOException e) {
                return ResultVOUtil.error(1, "文件过大");
            }
            //String imgUrl = "" + "/file/fileDownload?fileUrl="+fileName;
            String imgUrl = String.format("%s/file/fileDownload?fileUrl=%s", projectUrl, fileName);
            return ResultVOUtil.success(imgUrl);

    }


    private ResponseEntity<FileSystemResource> export(File file) {
        if (file == null) {
            return null;
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("accept-ranges", "bytes");
        headers.add("cache-control", "max-age=2592000");
        headers.add("expires", new Date().toString());
        headers.add("last-modified", new Date().toString());
        headers.add("etag", String.valueOf(System.currentTimeMillis()));
        return ResponseEntity.ok().headers(headers).contentLength(file.length()).contentType(MediaType.parseMediaType("image/png")) .body(new FileSystemResource(file));
    }
}