package com.danjinae.web.notice.Controller;

import com.danjinae.web.HttpRequest.HttpSender;
import com.danjinae.web.notice.RequestDTO.Notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/notice")
public class NoticeController {
    @Autowired
    HttpSender hSender;

    @GetMapping(path = "/")
    public String NoticeIndex(Model model) {
        return "notice_temp";
    }

    @PostMapping(path = "/newnotice")
    public String AddNewNotice(Model model, Notice newNotice) {
        var result = hSender.defHttpRequest("http://101.101.219.69:8080/notice/add", newNotice, HttpMethod.POST);
        model.addAttribute("result", result);
        return "helloWorld";
    }
}