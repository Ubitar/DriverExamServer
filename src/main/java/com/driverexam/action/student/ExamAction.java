package com.driverexam.action.student;

import com.driverexam.common.BaseResponse;
import com.driverexam.common.Const;
import com.driverexam.entry.student.ExamEntry;
import com.driverexam.entry.student.StudentEntry;
import com.driverexam.repository.ExamRepository;
import com.driverexam.repository.StuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class ExamAction {

    @Autowired
    StuRepository stuRepository;
    @Autowired
    ExamRepository examRepository;

    /**
     * @param token
     * @param type  驾照类型 A1_A3_B1、B2_A2、C1_C2_C3、E_F_D
     * @param level 考试科目几    1为科目一   4为科目四
     * @param score 分数
     * @return
     */
    @PostMapping("/exam/finish")
    public Object finishSimulateExam(
            @RequestHeader String token,
            @RequestParam String type,
            @RequestParam int level,
            @RequestParam int score) {
        StudentEntry studentEntry = stuRepository.findByToken(token);
        ExamEntry examEntry = examRepository.findByUserId(studentEntry.getId());
        examEntry.setUserId(studentEntry.getId());
        if (type.equals(Const.A1_A3_B1)) {
            if (level == 1) examEntry.setA1_A3_B1_1_score(score);
            else if(level==4) examEntry.setA1_A3_B1_4_score(score);
        } else if (type.equals(Const.B2_A2)) {
            if (level == 1) examEntry.setB2_A2_1_score(score);
            else if(level==4) examEntry.setB2_A2_4_score(score);
        } else if (type.equals(Const.C1_C2_C3)) {
            if (level == 1) examEntry.setC1_C2_C3_1_score(score);
            else if(level==4) examEntry.setC1_C2_C3_4_score(score);
        } else if (type.equals(Const.E_F_D)) {
            if (level == 1) examEntry.setE_F_D_1_score(score);
            else if(level==4) examEntry.setE_F_D_4_score(score);
        } else {
            return new BaseResponse("type参数错误", 0);
        }
        examRepository.save(examEntry);

        return new BaseResponse("更新成功");
    }
}
