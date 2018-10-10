package com.bzh.disciplineder.controller;

import com.bzh.disciplineder.service.UserService;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/***
 *
 * @author 段
 *   @Description:
 *   @time 2018/10/9
 */
@RestController
@RequestMapping("img")
public class imgController {
    @Autowired
    private UserService us;
    @PostMapping("/upimg")
    public int Uploadpictures(HttpServletRequest as, String userName){





        return 0;

    }

}
