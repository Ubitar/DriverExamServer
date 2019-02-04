package com.driverexam.action.student;

import com.driverexam.common.BaseResponse;
import com.driverexam.entry.student.ExamEntry;
import com.driverexam.entry.student.SimulateExamEntry;
import com.driverexam.entry.student.StudentEntry;
import com.driverexam.repository.ExamRepository;
import com.driverexam.repository.SimulateExamRepository;
import com.driverexam.repository.StuRepository;
import com.driverexam.utils.IDCardUtils;
import com.driverexam.utils.PhoneUtils;
import com.driverexam.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/student/person")
public class StuMsgAction {

    @Autowired
    StuRepository stuRepository;

    @Autowired
    SimulateExamRepository simulateExamRepository;

    @Autowired
    ExamRepository examRepository;

    @GetMapping("/get_msg")
    public Object getPersonMsg(@RequestHeader String token) {
        BaseResponse baseResponse = new BaseResponse("获取成功");
        StudentEntry studentEntry = stuRepository.findByToken(token);
        studentEntry.setAccount(null);
        studentEntry.setPassword(null);
        baseResponse.setData(studentEntry);
        return baseResponse;
    }

    @PostMapping("/input")
    public Object inputPersonMsg(
            @RequestHeader String token,
            @RequestParam String headview,
            @RequestParam String name,
            @RequestParam String mobile,
            @RequestParam String idCard,
            @RequestParam long birthday,
            @RequestParam int gender,
            @RequestParam String province,
            @RequestParam String city) {
        BaseResponse baseResponse = new BaseResponse();
        if(StringUtils.isEmpty(headview)){
            baseResponse.setCode(0);
            baseResponse.setMsg("请选择头像");
            return baseResponse;
        } else if(StringUtils.isEmpty(name)){
            baseResponse.setCode(0);
            baseResponse.setMsg("请输入真实姓名");
            return baseResponse;
        } else if(!PhoneUtils.isPhoneNum(mobile)){
            baseResponse.setCode(0);
            baseResponse.setMsg("请输入正确的手机号");
            return baseResponse;
        } else if(!IDCardUtils.validate(idCard)){
            baseResponse.setCode(0);
            baseResponse.setMsg("请输入正确的身份证");
            return baseResponse;
        } else if(birthday<=0){
            baseResponse.setCode(0);
            baseResponse.setMsg("请选择生日");
            return baseResponse;
        }else if(gender!=1&&gender!=2){
            baseResponse.setCode(0);
            baseResponse.setMsg("请选择性别");
            return baseResponse;
        }else if(StringUtils.isEmpty(province)){
            baseResponse.setCode(0);
            baseResponse.setMsg("请选择省份");
            return baseResponse;
        }else if(StringUtils.isEmpty(city)){
            baseResponse.setCode(0);
            baseResponse.setMsg("请选择城市");
            return baseResponse;
        }else if(province.equals("省")||city.equals("市")){
            baseResponse.setCode(0);
            baseResponse.setMsg("请选择正确的省份和城市");
            return baseResponse;
        }

        StudentEntry entry = stuRepository.findByToken(token);
        entry.setHeadview(headview);
        entry.setName(name);
        entry.setMobile(mobile);
        entry.setIdCard(idCard);
        entry.setBirthday(birthday);
        entry.setGender(gender);
        entry.setProvince(province);
        entry.setCity(city);
        stuRepository.save(entry);
        baseResponse.setMsg("修改成功");
        return baseResponse;
    }

    @PostMapping("/change_pwd")
    public Object changePwd(
            @RequestHeader String token,
            @RequestParam String password,
            @RequestParam String password2) {
        BaseResponse baseResponse = new BaseResponse();
        if (StringUtils.isEmpty(password) || StringUtils.isEmpty(password2)) {
            baseResponse.setCode(0);
            baseResponse.setMsg("输入的密码不能为空");
        } else if (!password.equals(password2)) {
            baseResponse.setCode(0);
            baseResponse.setMsg("两次输入的密码不一样");
        } else {
            StudentEntry entry = stuRepository.findByToken(token);
            entry.setPassword(password);
            stuRepository.save(entry);
            baseResponse.setMsg("修改成功");
        }
        return baseResponse;
    }

    @GetMapping("/get_simulate_exam")
    public Object getAllSimulateExam(
            @RequestHeader String token) {
        StudentEntry studentEntry = stuRepository.findByToken(token);
        SimulateExamEntry simulateExamEntry = simulateExamRepository.findByUserId(studentEntry.getId());
        BaseResponse baseResponse = new BaseResponse("查询成功");
        baseResponse.setData(simulateExamEntry);
        return baseResponse;
    }

    @GetMapping("/get_exam")
    public Object getAllOfficalExam(
            @RequestHeader String token) {
        StudentEntry studentEntry = stuRepository.findByToken(token);
        ExamEntry examEntry = examRepository.findByUserId(studentEntry.getId());
        BaseResponse baseResponse = new BaseResponse("查询成功");
        baseResponse.setData(examEntry);
        return baseResponse;
    }
}
