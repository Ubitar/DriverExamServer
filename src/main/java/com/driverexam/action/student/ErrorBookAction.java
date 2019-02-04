package com.driverexam.action.student;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.driverexam.common.BaseResponse;
import com.driverexam.common.Const;
import com.driverexam.entry.student.ErrorBookEntry;
import com.driverexam.entry.student.StudentEntry;
import com.driverexam.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/student/book")
public class ErrorBookAction {

    @Autowired
    StuRepository stuRepository;
    @Autowired
    ErrorBookRepository errorBookRepository;
    @Autowired
    A1_A3_B1_1_Repository a1_a3_b1_1_repository;
    @Autowired
    A1_A3_B1_4_Repository a1_a3_b1_4_repository;
    @Autowired
    B2_A2_1_Repository b2_a2_1_repository;
    @Autowired
    B2_A2_4_Repository b2_a2_4_repository;
    @Autowired
    C1_C2_C3_1_Repository c1_c2_c3_1_repository;
    @Autowired
    C1_C2_C3_4_Repository c1_c2_c3_4_repository;
    @Autowired
    E_F_D_1_Repository e_f_d_1_repository;
    @Autowired
    E_F_D_4_Repository e_f_d_4_repository;

    @GetMapping("/get")
    public Object getAll(
            @RequestHeader String token,
            @RequestParam(required = false) String type,
            @RequestParam(required = false, defaultValue = "0") int level,
            @RequestParam(required = false, defaultValue = "0") int page) {
        StudentEntry studentEntry = stuRepository.findByToken(token);

        Pageable pageable = new PageRequest(page, 10, Sort.Direction.DESC, "id");
        Page<ErrorBookEntry> errorBookEntryPage = errorBookRepository.findAll(new Specification<ErrorBookEntry>() {
            @Override
            public Predicate toPredicate(Root<ErrorBookEntry> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (type != null)
                    predicates.add(criteriaBuilder.equal(root.get(ErrorBookEntry.TYPE).as(String.class), type));
                if (level == 1 || level == 4)
                    predicates.add(criteriaBuilder.equal(root.get(ErrorBookEntry.LEVEL).as(Integer.class), level));
                if (studentEntry != null)
                    predicates.add(criteriaBuilder.equal(root.get(ErrorBookEntry.USER_ID).as(Integer.class), studentEntry.getId()));

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        }, pageable);

        JSONArray jsonArray = new JSONArray();
        List<ErrorBookEntry> list = errorBookEntryPage.getContent();
        for (int i = 0; i < list.size(); i++) {
            ErrorBookEntry errorBookEntry = list.get(i);
            String errorType = errorBookEntry.getType();
            if (errorType.equals(Const.A1_A3_B1)) {
                if (level == 1) {
                    JSONObject jsonObject = (JSONObject) JSON.toJSON(a1_a3_b1_1_repository.findOne(errorBookEntry.getQuestionId()));
                    jsonObject.put("bookId", errorBookEntry.getId());
                    jsonObject.put("level", 1);
                    jsonArray.add(jsonObject);
                } else {
                    JSONObject jsonObject = (JSONObject) JSON.toJSON(a1_a3_b1_4_repository.findOne(errorBookEntry.getQuestionId()));
                    jsonObject.put("bookId", errorBookEntry.getId());
                    jsonObject.put("level", 4);
                    jsonArray.add(jsonObject);
                }
            } else if (errorType.equals(Const.B2_A2)) {
                if (level == 1) {
                    JSONObject jsonObject = (JSONObject) JSON.toJSON(b2_a2_1_repository.findOne(errorBookEntry.getQuestionId()));
                    jsonObject.put("bookId", errorBookEntry.getId());
                    jsonObject.put("level", 1);
                    jsonArray.add(jsonObject);
                } else {
                    JSONObject jsonObject = (JSONObject) JSON.toJSON(b2_a2_4_repository.findOne(errorBookEntry.getQuestionId()));
                    jsonObject.put("bookId", errorBookEntry.getId());
                    jsonObject.put("level", 4);
                    jsonArray.add(jsonObject);
                }
            } else if (errorType.equals(Const.C1_C2_C3)) {
                if (level == 1) {
                    JSONObject jsonObject = (JSONObject) JSON.toJSON(c1_c2_c3_1_repository.findOne(errorBookEntry.getQuestionId()));
                    jsonObject.put("bookId", errorBookEntry.getId());
                    jsonObject.put("level", 1);
                    jsonArray.add(jsonObject);
                } else {
                    JSONObject jsonObject = (JSONObject) JSON.toJSON(c1_c2_c3_4_repository.findOne(errorBookEntry.getQuestionId()));
                    jsonObject.put("bookId", errorBookEntry.getId());
                    jsonObject.put("level", 4);
                    jsonArray.add(jsonObject);
                }
            } else if (errorType.equals(Const.E_F_D)) {
                if (level == 1) {
                    JSONObject jsonObject = (JSONObject) JSON.toJSON(e_f_d_1_repository.findOne(errorBookEntry.getQuestionId()));
                    jsonObject.put("bookId", errorBookEntry.getId());
                    jsonObject.put("level", 1);
                    jsonArray.add(jsonObject);
                } else {
                    JSONObject jsonObject = (JSONObject) JSON.toJSON(e_f_d_4_repository.findOne(errorBookEntry.getQuestionId()));
                    jsonObject.put("bookId", errorBookEntry.getId());
                    jsonObject.put("level", 4);
                    jsonArray.add(jsonObject);
                }
            }
        }
        JSONObject data = new JSONObject();
        data.put("books", jsonArray);
        data.put("pageCount", errorBookEntryPage.getTotalPages());

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMsg("查询成功");
        baseResponse.setData(data);
        return baseResponse;
    }

    @PostMapping("/update")
    public Object update(
            @RequestHeader String token,
            @RequestParam String type,
            @RequestParam int level,
            @RequestParam int id
    ) {
        if (level != 1 && level != 4) return new BaseResponse("请输入正确的level", 0);
        BaseResponse baseResponse = new BaseResponse("加入错题本成功");
        StudentEntry studentEntry = stuRepository.findByToken(token);
        ErrorBookEntry errorBookEntry = new ErrorBookEntry();
        errorBookEntry.setLevel(level);
        errorBookEntry.setQuestionId(id);
        errorBookEntry.setType(type);
        errorBookEntry.setUserId(studentEntry.getId());
        baseResponse.setData(errorBookRepository.save(errorBookEntry));
        return baseResponse;
    }

    @PostMapping("/del")
    public Object delete(
            @RequestParam int id
    ) {
        errorBookRepository.delete(id);
        return new BaseResponse("删除成功");
    }

}
