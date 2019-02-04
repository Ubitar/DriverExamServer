package com.driverexam.action;

import com.alibaba.fastjson.JSONObject;
import com.driverexam.common.BaseResponse;
import com.driverexam.common.Const;
import com.driverexam.entry.question.*;
import com.driverexam.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/question")
public class GetQuestionAction {

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

    /**
     * @param type  驾照类型 A1_A3_B1、B2_A2、C1_C2_C3、E_F_D
     * @param level 考试科目几    1为科目一   4为科目四
     * @param exam  0为练习模式  1为考试模式
     * @return
     */
    @GetMapping("/get_ids")
    public Object getQuesitonIds(
            @RequestParam String type,
            @RequestParam int level,
            @RequestParam int exam) {
        BaseResponse baseResponse = new BaseResponse();
        switch (type) {
            case Const.A1_A3_B1:
                if (level == 1) {
                    if (exam == 1) baseResponse.setData(a1_a3_b1_1_repository.findAllIdWithExam());
                    else baseResponse.setData(a1_a3_b1_1_repository.findAllId());
                } else {
                    if (exam == 1) baseResponse.setData(a1_a3_b1_4_repository.findAllIdWithExam());
                    else baseResponse.setData(a1_a3_b1_4_repository.findAllId());
                }
                baseResponse.setMsg("查找成功");
                break;
            case Const.B2_A2:
                if (level == 1) {
                    if (exam == 1) baseResponse.setData(b2_a2_1_repository.findAllIdWithExam());
                    else baseResponse.setData(b2_a2_1_repository.findAllId());
                } else {
                    if (exam == 1) baseResponse.setData(b2_a2_4_repository.findAllIdWithExam());
                    else baseResponse.setData(b2_a2_4_repository.findAllId());
                }
                baseResponse.setMsg("查找成功");
                break;
            case Const.C1_C2_C3:
                if (level == 1) {
                    if (exam == 1) baseResponse.setData(c1_c2_c3_1_repository.findAllIdWithExam());
                    else baseResponse.setData(c1_c2_c3_1_repository.findAllId());
                } else {
                    if (exam == 1) baseResponse.setData(c1_c2_c3_4_repository.findAllIdWithExam());
                    else baseResponse.setData(c1_c2_c3_4_repository.findAllId());
                }
                baseResponse.setMsg("查找成功");
                break;
            case Const.E_F_D:
                if (level == 1) {
                    if (exam == 1) baseResponse.setData(e_f_d_1_repository.findAllIdWithExam());
                    else baseResponse.setData(e_f_d_1_repository.findAllId());
                } else {
                    if (exam == 1) baseResponse.setData(e_f_d_4_repository.findAllIdWithExam());
                    else baseResponse.setData(e_f_d_4_repository.findAllId());
                }
                baseResponse.setMsg("查找成功");
                break;
            default:
                baseResponse.setMsg("请输入正确的驾考类型");
                baseResponse.setCode(0);
                break;
        }
        return baseResponse;
    }

    @RequestMapping("/get_questions")
    public Object getQuestionByPage(
            @RequestParam(required = false) String question,
            @RequestParam String type,
            @RequestParam int level,  //1或4
            @RequestParam(required = false, defaultValue = "0") int page
    ) {
        BaseResponse baseResponse = new BaseResponse("查询成功");
        Pageable pageable = new PageRequest(page, 15);
        switch (type) {
            case Const.A1_A3_B1:
                if (level == 1) {
                    Page<A1_A3_B1_1_Entry> pages=null;
                    if(question==null) pages = a1_a3_b1_1_repository.findAll(pageable);
                    else pages=a1_a3_b1_1_repository.findAllByQuestionLike("%"+question+"%",pageable);
                    List<A1_A3_B1_1_Entry> list = pages.getContent();
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("count",a1_a3_b1_1_repository.count());
                    jsonObject.put("questions", list);
                    jsonObject.put("pageCount", pages.getTotalPages());
                    baseResponse.setData(jsonObject);
                } else if (level == 4) {
                    Page<A1_A3_B1_4_Entry> pages2=null;
                    if(question==null) pages2 = a1_a3_b1_4_repository.findAll(pageable);
                    else pages2=a1_a3_b1_4_repository.findAllByQuestionLike("%"+question+"%",pageable);
                    List<A1_A3_B1_4_Entry> list = pages2.getContent();
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("count",a1_a3_b1_4_repository.count());
                    jsonObject.put("questions", list);
                    jsonObject.put("pageCount", pages2.getTotalPages());
                    baseResponse.setData(jsonObject);
                }
                break;
            case Const.B2_A2:
                if (level == 1){
                    Page<B2_A2_1_Entry> pages3=null;
                    if(question==null) pages3 = b2_a2_1_repository.findAll(pageable);
                    else pages3=b2_a2_1_repository.findAllByQuestionLike("%"+question+"%",pageable);
                    List<B2_A2_1_Entry> list=pages3.getContent();
                    JSONObject jsonObject=new JSONObject();
                    jsonObject.put("count",b2_a2_1_repository.count());
                    jsonObject.put("questions",list);
                    jsonObject.put("pageCount",pages3.getTotalPages());
                    baseResponse.setData(jsonObject);
                }
                else if (level == 4){
                    Page<B2_A2_4_Entry> pages4=null;
                    if(question==null) pages4 = b2_a2_4_repository.findAll(pageable);
                    else pages4=b2_a2_4_repository.findAllByQuestionLike("%"+question+"%",pageable);
                    List<B2_A2_4_Entry> list=pages4.getContent();
                    JSONObject jsonObject=new JSONObject();
                    jsonObject.put("count",b2_a2_4_repository.count());
                    jsonObject.put("questions",list);
                    jsonObject.put("pageCount",pages4.getTotalPages());
                    baseResponse.setData(jsonObject);
                }
                break;
            case Const.C1_C2_C3:
                if (level == 1){
                    Page<C1_C2_C3_1_Entry> pages5=null;
                    if(question==null) pages5 = c1_c2_c3_1_repository.findAll(pageable);
                    else pages5=c1_c2_c3_1_repository.findAllByQuestionLike("%"+question+"%",pageable);
                    List<C1_C2_C3_1_Entry> list=pages5.getContent();
                    JSONObject jsonObject=new JSONObject();
                    jsonObject.put("count",c1_c2_c3_1_repository.count());
                    jsonObject.put("questions",list);
                    jsonObject.put("pageCount",pages5.getTotalPages());
                    baseResponse.setData(jsonObject);
                }
                else if (level == 4) {
                    Page<C1_C2_C3_4_Entry> pages6=null;
                    if(question==null) pages6 = c1_c2_c3_4_repository.findAll(pageable);
                    else pages6=c1_c2_c3_4_repository.findAllByQuestionLike("%"+question+"%",pageable);
                    List<C1_C2_C3_4_Entry> list=pages6.getContent();
                    JSONObject jsonObject=new JSONObject();
                    jsonObject.put("count",c1_c2_c3_4_repository.count());
                    jsonObject.put("questions",list);
                    jsonObject.put("pageCount",pages6.getTotalPages());
                    baseResponse.setData(jsonObject);
                }
                break;
            case Const.E_F_D:
                if (level == 1){
                    Page<E_F_D_1_Entry> pages7=null;
                    if(question==null) pages7 = e_f_d_1_repository.findAll(pageable);
                    else pages7=e_f_d_1_repository.findAllByQuestionLike("%"+question+"%",pageable);
                    List<E_F_D_1_Entry> list=pages7.getContent();
                    JSONObject jsonObject=new JSONObject();
                    jsonObject.put("count",e_f_d_1_repository.count());
                    jsonObject.put("questions",list);
                    jsonObject.put("pageCount",pages7.getTotalPages());
                    baseResponse.setData(jsonObject);
                }
                else if (level == 4) {
                    Page<E_F_D_4_Entry> pages8=null;
                    if(question==null) pages8 = e_f_d_4_repository.findAll(pageable);
                    else pages8=e_f_d_4_repository.findAllByQuestionLike("%"+question+"%",pageable);
                    List<E_F_D_4_Entry> list=pages8.getContent();
                    JSONObject jsonObject=new JSONObject();
                    jsonObject.put("count",e_f_d_4_repository.count());
                    jsonObject.put("questions",list);
                    jsonObject.put("pageCount",pages8.getTotalPages());
                    baseResponse.setData(jsonObject);
                }
                break;
            default:
                baseResponse.setMsg("请输入正确的驾考类型");
                baseResponse.setCode(0);
                break;
        }
        return baseResponse;
    }

    /**
     * @param type  驾照类型 A1_A3_B1   B2_A2   C1_C2_C3   E_F_D
     * @param level 考试科目几    1为科目一   4为科目四
     * @param id    题目id
     * @return
     */
    @GetMapping("/get")
    public Object getQuestionById(
            @RequestParam String type,
            @RequestParam int level,
            @RequestParam int id) {
        BaseResponse baseResponse = new BaseResponse("查询成功");
        switch (type) {
            case Const.A1_A3_B1:
                if (level == 1) baseResponse.setData(a1_a3_b1_1_repository.findOne(id));
                else if(level==4) baseResponse.setData(a1_a3_b1_4_repository.findOne(id));
                break;
            case Const.B2_A2:
                if (level == 1) baseResponse.setData(b2_a2_1_repository.findOne(id));
                else if(level==4)  baseResponse.setData(b2_a2_4_repository.findOne(id));
                break;
            case Const.C1_C2_C3:
                if (level == 1) baseResponse.setData(c1_c2_c3_1_repository.findOne(id));
                else if(level==4)  baseResponse.setData(c1_c2_c3_4_repository.findOne(id));
                break;
            case Const.E_F_D:
                if (level == 1) baseResponse.setData(e_f_d_1_repository.findOne(id));
                else if(level==4)  baseResponse.setData(e_f_d_4_repository.findOne(id));
                break;
            default:
                baseResponse.setMsg("请输入正确的驾考类型");
                baseResponse.setCode(0);
                break;
        }
        return baseResponse;
    }


}
