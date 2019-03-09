package com.driverexam.action.student;


import com.driverexam.common.BaseResponse;
import com.driverexam.common.Const;
import com.driverexam.entry.student.ExamEntry;
import com.driverexam.entry.student.OrderExerciseEntry;
import com.driverexam.entry.student.SimulateExamEntry;
import com.driverexam.entry.student.StudentEntry;
import com.driverexam.repository.ExamRepository;
import com.driverexam.repository.OrderExerciseRepository;
import com.driverexam.repository.SimulateExamRepository;
import com.driverexam.repository.StuRepository;
import com.driverexam.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/student")
public class StuLoginActioin {

    @Autowired
    StuRepository stuRepository;
    @Autowired
    ExamRepository examRepository;
    @Autowired
    OrderExerciseRepository orderExerciseRepository;
    @Autowired
    SimulateExamRepository simulateExamRepository;

    @PostMapping("/login")
    public Object login(@RequestParam String account, @RequestParam String password) {
        BaseResponse baseResponse = new BaseResponse();
        StudentEntry studentEntry = stuRepository.findByAccountAndPassword(account, password);
        if (studentEntry == null) {
            baseResponse.setMsg("账户或密码错误");
            baseResponse.setCode(0);
        } else {
            if (studentEntry.getFreeze() == 1) {
                baseResponse.setCode(0);
                baseResponse.setMsg("账号已被冻结");
            } else {
                studentEntry.setToken(UUID.randomUUID().toString());
                stuRepository.save(studentEntry);
                studentEntry.setPassword(null);
                baseResponse.setData(studentEntry);
                baseResponse.setMsg("登陆成功");
            }
        }
        return baseResponse;
    }

    @PostMapping("/logout")
    public Object logout(@RequestHeader String token) {
        BaseResponse baseResponse = new BaseResponse("注销成功");
        StudentEntry studentEntry = stuRepository.findByToken(token);
        studentEntry.setToken(null);
        stuRepository.save(studentEntry);
        return baseResponse;
    }

    @PostMapping("/register")
    public Object register(
            @RequestParam String account,
            @RequestParam String password,
            @RequestParam String password2
    ) {
        BaseResponse baseResponse = new BaseResponse();

        if (StringUtils.isEmpty(account) || StringUtils.isEmpty(password) || StringUtils.isEmpty(password2)) {
            return new BaseResponse("账号密码不能为空", 0);
        }
        if (!password.equals(password2)) {
            return new BaseResponse("两次输入的密码不相同", 0);
        }

        StudentEntry studentEntry = stuRepository.findByAccountAndPassword(account, password);
        if (studentEntry == null) {

            studentEntry = new StudentEntry();
            studentEntry.setAccount(account);
            studentEntry.setPassword(password);
            studentEntry.setBuildTime(new Date().getTime());
            studentEntry.setToken(UUID.randomUUID().toString());
            studentEntry = stuRepository.save(studentEntry);

            OrderExerciseEntry orderExerciseEntry = new OrderExerciseEntry();
            orderExerciseEntry.setUserId(studentEntry.getId());
            orderExerciseRepository.save(orderExerciseEntry);

            SimulateExamEntry simulateExamEntry = new SimulateExamEntry();
            simulateExamEntry.setUserId(studentEntry.getId());
            simulateExamRepository.save(simulateExamEntry);

            ExamEntry examEntry = new ExamEntry();
            examEntry.setUserId(studentEntry.getId());
            examRepository.save(examEntry);

            baseResponse.setMsg("注册成功");
            baseResponse.setData(studentEntry);
        } else {
            baseResponse.setCode(0);
            baseResponse.setMsg("用户已存在");
        }
        return baseResponse;
    }

}
