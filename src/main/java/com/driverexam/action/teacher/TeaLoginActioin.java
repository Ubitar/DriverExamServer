package com.driverexam.action.teacher;


import com.driverexam.common.BaseResponse;
import com.driverexam.entry.teacher.TeacherEntry;
import com.driverexam.repository.TeaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/teacher")
public class TeaLoginActioin {

    @Autowired
    TeaRepository teaRepository;

    @PostMapping("/login")
    public Object login(@RequestParam String account, @RequestParam String password) {
        BaseResponse baseResponse = new BaseResponse();
        TeacherEntry teacherEntry = teaRepository.findByAccountAndPassword(account, password);
        if (teacherEntry == null) {
            baseResponse.setMsg("账户或密码错误");
            baseResponse.setCode(0);
        } else {
            teacherEntry.setToken(UUID.randomUUID().toString());
            teacherEntry = teaRepository.save(teacherEntry);

            teacherEntry.setAccount(null);
            teacherEntry.setPassword(null);

            baseResponse.setData(teacherEntry);
            baseResponse.setMsg("登陆成功");
        }
        return baseResponse;
    }

    @PostMapping("/logout")
    public Object logout(@RequestHeader String token) {
        BaseResponse baseResponse = new BaseResponse("注销成功");
        TeacherEntry teacherEntry = teaRepository.findByToken(token);
        teacherEntry.setToken(null);
        teaRepository.save(teacherEntry);

        return baseResponse;
    }

}
