package com.danjinae.web.user.Controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.danjinae.web.HttpRequest.HttpSender;
import com.danjinae.web.notice.RequestDTO.Notice;
import com.danjinae.web.user.DTO.NewUser;
import com.danjinae.web.user.DTO.NewUserRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    HttpSender hSender;

    private Integer mgrId = 1;

    @GetMapping(path = "/register")
    public String NoticeIndex(Model model) {
        return "registration1";
    }

    @PostMapping(path = "/registerResult")
    public String AddNewNotice(Model model, NewUser newUser) {
        newUser.setMgrId(mgrId);
        try {

            SimpleDateFormat dtFormat = new SimpleDateFormat("yyMMdd");
            SimpleDateFormat newDtFormat = new SimpleDateFormat("yyyy-MM-dd");
            // String 타입을 Date 타입으로 변환
            Date formatDate = dtFormat.parse(newUser.getBirth());
            // Date타입의 변수를 새롭게 지정한 포맷으로 변환
            newUser.setBirth(newDtFormat.format(formatDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        var result = hSender.defHttpRequest("http://101.101.219.69:8080/user/add", newUser, HttpMethod.POST);
        model.addAttribute("result", result.getData());
        if (!(Boolean) result.getData())
            return "redirect:/err/report?message=" + result.getMessage();
        else
            return "registrationcp";

    }
}
