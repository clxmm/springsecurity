package org.clxmm.web.controller;


import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.clxmm.dto.FileInfo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author clx
 * @date 2020-06-25 16:31
 */
@RestController
@RequestMapping("/file")
public class FileController {
    private final String path = "D:\\resourceStudy\\imooc\\springsecurity\\security\\security-demo\\src\\main\\java\\org\\clxmm\\web\\controller";


    @PostMapping
    public FileInfo upload(MultipartFile file) throws IOException {
        String name = file.getName();
        System.out.println(name);
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());


        File file1 = new File(path, System.currentTimeMillis() + ".txt");
        file.transferTo(file1);


        return new FileInfo(file1.getAbsolutePath());
    }


    @GetMapping("/{id}")
    public void download(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) {


        try (
                InputStream inputStream = new FileInputStream(new File(path, id + ".txt"));
                OutputStream outputStream = response.getOutputStream();
        ) {
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;filename=test.txt");

            IOUtils.copy(inputStream, outputStream);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
