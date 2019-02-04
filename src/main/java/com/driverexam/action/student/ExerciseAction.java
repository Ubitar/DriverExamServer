package com.driverexam.action.student;

import com.driverexam.common.BaseResponse;
import com.driverexam.common.Const;
import com.driverexam.entry.student.ExamEntry;
import com.driverexam.entry.student.OrderExerciseEntry;
import com.driverexam.entry.student.SimulateExamEntry;
import com.driverexam.entry.student.StudentEntry;
import com.driverexam.repository.OrderExerciseRepository;
import com.driverexam.repository.SimulateExamRepository;
import com.driverexam.repository.StuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class ExerciseAction {

    @Autowired
    StuRepository stuRepository;
    @Autowired
    OrderExerciseRepository orderExerciseRepository;
    @Autowired
    SimulateExamRepository simulateExamRepository;

    /**
     * @param token
     * @return
     */
    @GetMapping("/order_exercise/cur")
    public Object getOrderExerciseCurQueId(
            @RequestHeader String token
    ) {
        StudentEntry studentEntry = stuRepository.findByToken(token);
        OrderExerciseEntry orderExerciseEntry = orderExerciseRepository.findByUserId( studentEntry.getId());
        BaseResponse baseResponse = new BaseResponse("查询成功");
        baseResponse.setData(orderExerciseEntry);
        return baseResponse;
    }

    /**
     * @param token
     * @param type  驾照类型 A1_A3_B1、B2_A2、C1_C2_C3、E_F_D
     * @param id    完成的题目的id
     * @return
     */
    @PostMapping("/order_exercise/finish")
    public Object finishOrderExerciseQueId(
            @RequestHeader String token,
            @RequestParam String type,
            @RequestParam int level,
            @RequestParam int id) {
        StudentEntry studentEntry = stuRepository.findByToken(token);
        OrderExerciseEntry orderExerciseEntry = orderExerciseRepository.findByUserId( studentEntry.getId());
        if(level==1){
            if (type.equals(Const.A1_A3_B1)) {
                orderExerciseEntry.setA1_A3_B1_1_id(id);
            } else if (type.equals(Const.B2_A2)) {
                orderExerciseEntry.setB2_A2_1_id(id);
            } else if (type.equals(Const.C1_C2_C3)) {
                orderExerciseEntry.setC1_C2_C3_1_id(id);
            } else if (type.equals(Const.E_F_D)) {
                orderExerciseEntry.setE_F_D_1_id(id);
            }
        }else if(level==4){
            if (type.equals(Const.A1_A3_B1)) {
                orderExerciseEntry.setA1_A3_B1_4_id(id);
            } else if (type.equals(Const.B2_A2)) {
                orderExerciseEntry.setB2_A2_4_id(id);
            } else if (type.equals(Const.C1_C2_C3)) {
                orderExerciseEntry.setC1_C2_C3_4_id(id);
            } else if (type.equals(Const.E_F_D)) {
                orderExerciseEntry.setE_F_D_4_id(id);
            }
        }
        
        orderExerciseRepository.save(orderExerciseEntry);
        BaseResponse baseResponse = new BaseResponse("更新成功");
        baseResponse.setData(orderExerciseEntry);
        return baseResponse;
    }

    /**
     * @param token
     * @param type  驾照类型 A1_A3_B1、B2_A2、C1_C2_C3、E_F_D
     * @param level 考试科目几    1为科目一   4为科目四
     * @param score 分数
     * @return
     */
    @PostMapping("/simulate_exam/finish")
    public Object finishSimulateExam(
            @RequestHeader String token,
            @RequestParam String type,
            @RequestParam int level,
            @RequestParam int score) {
        StudentEntry studentEntry = stuRepository.findByToken(token);
        SimulateExamEntry simulateExamEntry = simulateExamRepository.findByUserId( studentEntry.getId());

        if(level==1){
            if (type.equals(Const.A1_A3_B1)) {
                simulateExamEntry.setA1_A3_B1_1_score(score);
            } else if (type.equals(Const.B2_A2)) {
                simulateExamEntry.setB2_A2_1_score(score);
            } else if (type.equals(Const.C1_C2_C3)) {
                simulateExamEntry.setC1_C2_C3_1_score(score);
            } else if (type.equals(Const.E_F_D)) {
                simulateExamEntry.setE_F_D_1_score(score);
            }
        }else if(level==4){
            if (type.equals(Const.A1_A3_B1)) {
                simulateExamEntry.setA1_A3_B1_4_score(score);
            } else if (type.equals(Const.B2_A2)) {
                simulateExamEntry.setB2_A2_4_score(score);
            } else if (type.equals(Const.C1_C2_C3)) {
                simulateExamEntry.setC1_C2_C3_4_score(score);
            } else if (type.equals(Const.E_F_D)) {
                simulateExamEntry.setE_F_D_4_score(score);
            }
        }
        
        simulateExamEntry.setUserId(studentEntry.getId());
        simulateExamRepository.save(simulateExamEntry);

        BaseResponse baseResponse = new BaseResponse("更新成功");
        return baseResponse;
    }
}
