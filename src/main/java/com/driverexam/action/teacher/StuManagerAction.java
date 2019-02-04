package com.driverexam.action.teacher;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.driverexam.common.BaseResponse;
import com.driverexam.common.Const;
import com.driverexam.entry.student.ExamEntry;
import com.driverexam.entry.student.StudentEntry;
import com.driverexam.repository.ExamRepository;
import com.driverexam.repository.StuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

@RestController
@RequestMapping("/teacher/studentmg")
public class StuManagerAction {
    @Autowired
    ExamRepository examRepository;
    @Autowired
    StuRepository stuRepository;

    @RequestMapping("/get")
    public Object get(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String name,
            @RequestParam(required = false, defaultValue = "0") int level, //-1未通过   1科目一  1~4科目一~科目四    4科目四
            @RequestParam(required = false, defaultValue = "0") int page) {
        BaseResponse baseResponse = new BaseResponse("查询成功");
        Pageable pageable = new PageRequest(page, 10);

        List<StudentEntry> studentEntries = null;
        if (name != null) {
            studentEntries = stuRepository.findAllByNameLike("%" + name + "%");
        }

        List<StudentEntry> finalStudentEntries = studentEntries;
        Page<ExamEntry> pages = examRepository.findAll(new Specification<ExamEntry>() {
            @Override
            public Predicate toPredicate(Root<ExamEntry> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (type != null && level < 0) {
                    if (type.equals(Const.A1_A3_B1)) {
                        predicates.add(criteriaBuilder.lessThan(root.get(ExamEntry.A1_A3_B1_1_SCORE).as(Integer.class), 90));
                    } else if (type.equals(Const.B2_A2)) {
                        predicates.add(criteriaBuilder.lessThan(root.get(ExamEntry.B2_A2_1_SCORE).as(Integer.class), 90));
                    } else if (type.equals(Const.C1_C2_C3)) {
                        predicates.add(criteriaBuilder.lessThan(root.get(ExamEntry.C1_C2_C3_1_SCORE).as(Integer.class), 90));
                    } else if (type.equals(Const.E_F_D)) {
                        predicates.add(criteriaBuilder.lessThan(root.get(ExamEntry.E_F_D_1_SCORE).as(Integer.class), 90));
                    }
                } else if (type != null && level == 1) {
                    if (type.equals(Const.A1_A3_B1)) {
                        predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(ExamEntry.A1_A3_B1_1_SCORE).as(Integer.class), 90));
                    } else if (type.equals(Const.B2_A2)) {
                        predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(ExamEntry.B2_A2_1_SCORE).as(Integer.class), 90));
                    } else if (type.equals(Const.C1_C2_C3)) {
                        predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(ExamEntry.C1_C2_C3_1_SCORE).as(Integer.class), 90));
                    } else if (type.equals(Const.E_F_D)) {
                        predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(ExamEntry.E_F_D_1_SCORE).as(Integer.class), 90));
                    }
                } else if (type != null && level > 1 && level < 4) {
                    if (type.equals(Const.A1_A3_B1)) {
                        predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(ExamEntry.A1_A3_B1_1_SCORE).as(Integer.class), 90));
                        predicates.add(criteriaBuilder.lessThan(root.get(ExamEntry.A1_A3_B1_4_SCORE).as(Integer.class), 90));
                    } else if (type.equals(Const.B2_A2)) {
                        predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(ExamEntry.B2_A2_1_SCORE).as(Integer.class), 90));
                        predicates.add(criteriaBuilder.lessThan(root.get(ExamEntry.B2_A2_4_SCORE).as(Integer.class), 90));
                    } else if (type.equals(Const.C1_C2_C3)) {
                        predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(ExamEntry.C1_C2_C3_1_SCORE).as(Integer.class), 90));
                        predicates.add(criteriaBuilder.lessThan(root.get(ExamEntry.C1_C2_C3_4_SCORE).as(Integer.class), 90));
                    } else if (type.equals(Const.E_F_D)) {
                        predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(ExamEntry.E_F_D_1_SCORE).as(Integer.class), 90));
                        predicates.add(criteriaBuilder.lessThan(root.get(ExamEntry.E_F_D_4_SCORE).as(Integer.class), 90));
                    }
                } else if (type != null && level == 4) {
                    if (type.equals(Const.A1_A3_B1)) {
                        predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(ExamEntry.A1_A3_B1_4_SCORE).as(Integer.class), 90));
                    } else if (type.equals(Const.B2_A2)) {
                        predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(ExamEntry.B2_A2_4_SCORE).as(Integer.class), 90));
                    } else if (type.equals(Const.C1_C2_C3)) {
                        predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(ExamEntry.C1_C2_C3_4_SCORE).as(Integer.class), 90));
                    } else if (type.equals(Const.E_F_D)) {
                        predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(ExamEntry.E_F_D_4_SCORE).as(Integer.class), 90));
                    }
                }

                if (finalStudentEntries != null) {
                    if (finalStudentEntries.size() > 0) {
                        CriteriaBuilder.In<Object> in = criteriaBuilder.in(root.get(ExamEntry.USER_ID));
                        for (StudentEntry studentEntry : finalStudentEntries)
                            in.value(studentEntry.getId());
                        predicates.add(criteriaBuilder.and(in));
                    } else {
                        CriteriaBuilder.In<Object> in = criteriaBuilder.in(root.get(ExamEntry.USER_ID));
                        in.value(-1);
                        predicates.add(criteriaBuilder.and(in));
                    }
                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        }, pageable);


        List<ExamEntry> list = pages.getContent();
        HashMap<Integer, JSONObject> hashMap = new HashMap<>(list.size());
        for (ExamEntry examEntry : list) {
            StudentEntry studentEntry = stuRepository.findOne(examEntry.getUserId());
            if (!hashMap.containsKey(studentEntry.getId())) {
                JSONObject jsonObject = (JSONObject) JSON.toJSON(studentEntry);
                jsonObject.put("exam", new ArrayList<>());
                hashMap.put(studentEntry.getId(), jsonObject);
            }
            JSONObject jsonObject = hashMap.get(studentEntry.getId());
            List exams = jsonObject.getObject("exam", List.class);
            exams.add(examEntry);
            jsonObject.put("exam", exams);
        }

        JSONObject data = new JSONObject();
        data.put("count", stuRepository.count());
        data.put("pageCount", pages.getTotalPages());
        JSONArray students = new JSONArray();
        Iterator iter = hashMap.entrySet().iterator();
        while (iter.hasNext())
            students.add(((Map.Entry) iter.next()).getValue());
        data.put("students", students);

        baseResponse.setData(data);
        return baseResponse;
    }


    @GetMapping("/get_stu_msg")
    public Object getStudentMsg(
            @RequestParam int id
    ) {
        StudentEntry studentEntry = stuRepository.findOne(id);
        ExamEntry examEntry = examRepository.findByUserId(id);
        JSONObject data = new JSONObject();
        data.put("exams", examEntry);
        data.put("student", studentEntry);
        BaseResponse baseResponse = new BaseResponse("查询成功");
        baseResponse.setData(data);
        return baseResponse;
    }

    @RequestMapping("/freeze")
    public Object freeze(
            @RequestParam int id,
            @RequestParam int freeze
    ) {
        StudentEntry studentEntry = stuRepository.findOne(id);
        if (freeze == 0) studentEntry.setFreeze(0);
        else studentEntry.setFreeze(1);
        stuRepository.save(studentEntry);
        return new BaseResponse("操作成功");
    }

}
