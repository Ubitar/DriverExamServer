package com.driverexam.action.teacher;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.driverexam.common.Const;
import com.driverexam.entry.question.*;
import com.driverexam.repository.*;
import com.driverexam.common.BaseResponse;
import com.driverexam.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/teacher/question")
public class QuestionAction {

    @Autowired
    A1_A3_B1_1_Repository a1_a3_b1_1_repository;
    @Autowired
    A1_A3_B1_1_Selection_Repository a1_a3_b1_1_selection_repository;
    @Autowired
    A1_A3_B1_1_Analysis_Repository a1_a3_b1_1_analysis_repository;
    @Autowired
    A1_A3_B1_4_Selection_Repository a1_a3_b1_4_selection_repository;
    @Autowired
    A1_A3_B1_4_Analysis_Repository a1_a3_b1_4_analysis_repository;
    @Autowired
    A1_A3_B1_4_Repository a1_a3_b1_4_repository;
    @Autowired
    B2_A2_1_Repository b2_a2_1_repository;
    @Autowired
    B2_A2_1_Selection_Repository b2_a2_1_selection_repository;
    @Autowired
    B2_A2_1_Analysis_Repository b2_a2_1_analysis_repository;
    @Autowired
    B2_A2_4_Repository b2_a2_4_repository;
    @Autowired
    B2_A2_4_Selection_Repository b2_a2_4_selection_repository;
    @Autowired
    B2_A2_4_Analysis_Repository b2_a2_4_analysis_repository;
    @Autowired
    C1_C2_C3_1_Repository c1_c2_c3_1_repository;
    @Autowired
    C1_C2_C3_1_Selection_Repository c1_c2_c3_1_selection_repository;
    @Autowired
    C1_C2_C3_1_Analysis_Repository c1_c2_c3_1_analysis_repository;
    @Autowired
    C1_C2_C3_4_Repository c1_c2_c3_4_repository;
    @Autowired
    C1_C2_C3_4_Selection_Repository c1_c2_c3_4_selection_repository;
    @Autowired
    C1_C2_C3_4_Analysis_Repository c1_c2_c3_4_analysis_repository;
    @Autowired
    E_F_D_1_Repository e_f_d_1_repository;
    @Autowired
    E_F_D_1_Selection_Repository e_f_d_1_selection_repository;
    @Autowired
    E_F_D_1_Analysis_Repository e_f_d_1_analysis_repository;
    @Autowired
    E_F_D_4_Repository e_f_d_4_repository;
    @Autowired
    E_F_D_4_Selection_Repository e_f_d_4_selection_repository;
    @Autowired
    E_F_D_4_Analysis_Repository e_f_d_4_analysis_repository;


    /**
     * @param type  驾照类型 A1_A3_B1、B2_A2、C1_C2_C3、E_F_D
     * @param level 考试科目几    1为科目一   4为科目四
     * @param data  数据json字符串  对应A1_A3_B1等的Entry
     * @return
     */
    @PostMapping("/update")
    public Object update(
            @RequestParam String type,
            @RequestParam int level,
            @RequestParam String data) {
        JSONObject jsonObject= (JSONObject) JSONObject.parse(data);
        if(StringUtils.isSpace(jsonObject.getString("question"))) return new BaseResponse("请输入问题",0);
        if(StringUtils.isSpace(jsonObject.getString("questionPic"))) jsonObject.put("questionPic",null);
        if(jsonObject.getInteger("type")!=1){
            JSONArray jsonArray=jsonObject.getJSONArray("selects");
            for(int i =0,size=jsonArray.size();i<size;i++){
                if(StringUtils.isSpace(((JSONObject)jsonArray.get(i)).getString("options")))
                    return new BaseResponse("选项内容不能为空",0);
            }
        }
        if(StringUtils.isSpace(jsonObject.getString("answer")))
            return new BaseResponse("请输入问题的答案",0);

        BaseResponse baseResponse = new BaseResponse("更新成功");
        switch (type) {
            case Const.A1_A3_B1:
                if (level == 1) {
                    A1_A3_B1_1_Entry a1_a3_b1_1_entry = JSONObject.parseObject(data, A1_A3_B1_1_Entry.class);
                    a1_a3_b1_1_repository.save(a1_a3_b1_1_entry);
                } else if (level == 4) {
                    A1_A3_B1_4_Entry a1_a3_b1_4_entry = JSONObject.parseObject(data, A1_A3_B1_4_Entry.class);
                    a1_a3_b1_4_repository.save(a1_a3_b1_4_entry);
                }
                break;
            case Const.B2_A2:
                if (level == 1) {
                    B2_A2_1_Entry b2_a2_1_entry = JSONObject.parseObject(data, B2_A2_1_Entry.class);
                    b2_a2_1_repository.save(b2_a2_1_entry);
                } else if (level == 4) {
                    B2_A2_4_Entry b2_a2_4_entry = JSONObject.parseObject(data, B2_A2_4_Entry.class);
                    b2_a2_4_repository.save(b2_a2_4_entry);
                }
                break;
            case Const.C1_C2_C3:
                if (level == 1) {
                    C1_C2_C3_1_Entry c1_c2_c3_1_entry = JSONObject.parseObject(data, C1_C2_C3_1_Entry.class);
                    c1_c2_c3_1_repository.save(c1_c2_c3_1_entry);
                } else if (level == 4) {
                    C1_C2_C3_4_Entry c1_c2_c3_4_entry = JSONObject.parseObject(data, C1_C2_C3_4_Entry.class);
                    c1_c2_c3_4_repository.save(c1_c2_c3_4_entry);
                }
                break;
            case Const.E_F_D:
                if (level == 1) {
                    E_F_D_1_Entry e_f_d_1_entry = JSONObject.parseObject(data, E_F_D_1_Entry.class);
                    e_f_d_1_repository.save(e_f_d_1_entry);
                } else if (level == 4) {
                    E_F_D_4_Entry e_f_d_4_entry = JSONObject.parseObject(data, E_F_D_4_Entry.class);
                    e_f_d_4_repository.save(e_f_d_4_entry);
                }
                break;
            default:
                baseResponse.setMsg("更新失败");
                baseResponse.setCode(0);
                break;
        }
        return baseResponse;
    }

    /**
     * @param type  驾照类型 A1_A3_B1、B2_A2、C1_C2_C3、E_F_D
     * @param level 考试科目几    1为科目一   4为科目四
     * @param ids   ids
     * @return
     */
    @PostMapping("/del")
    public Object delete(
            @RequestParam String type,
            @RequestParam int level,
            @RequestParam String ids) {
        BaseResponse baseResponse = new BaseResponse("删除成功");
        List<Integer> idList = JSON.parseObject(ids, List.class);
        switch (type) {
            case Const.A1_A3_B1:
                if (level == 1) {
                    for (int id : idList) {
                        A1_A3_B1_1_Entry entry = a1_a3_b1_1_repository.findOne(id);
                        for (A1_A3_B1_1_SelectionEntry selection : entry.getSelects())
                            a1_a3_b1_1_selection_repository.delete(selection.getId());
                        for (A1_A3_B1_1_AnalysisEntry analysis : entry.getAnalyses())
                            a1_a3_b1_1_analysis_repository.delete(analysis.getId());
                        a1_a3_b1_1_repository.delete(id);
                    }
                } else {
                    for (int id : idList) {
                        A1_A3_B1_4_Entry entry = a1_a3_b1_4_repository.findOne(id);
                        for (A1_A3_B1_4_SelectionEntry selection : entry.getSelects())
                            a1_a3_b1_4_selection_repository.delete(selection.getId());
                        for (A1_A3_B1_4_AnalysisEntry analysis : entry.getAnalyses())
                            a1_a3_b1_4_analysis_repository.delete(analysis.getId());
                        a1_a3_b1_4_repository.delete(id);
                    }
                }
                break;
            case Const.B2_A2:
                if (level == 1) {
                    for (int id : idList) {
                        B2_A2_1_Entry entry = b2_a2_1_repository.findOne(id);
                        for (B2_A2_1_SelectionEntry selection : entry.getSelects())
                            b2_a2_1_selection_repository.delete(selection.getId());
                        for (B2_A2_1_AnalysisEntry analysis : entry.getAnalyses())
                            b2_a2_1_analysis_repository.delete(analysis.getId());
                        b2_a2_1_repository.delete(id);
                    }
                } else {
                    for (int id : idList) {
                        B2_A2_4_Entry entry = b2_a2_4_repository.findOne(id);
                        for (B2_A2_4_SelectionEntry selection : entry.getSelects())
                            b2_a2_4_selection_repository.delete(selection.getId());
                        for (B2_A2_4_AnalysisEntry analysis : entry.getAnalyses())
                            b2_a2_4_analysis_repository.delete(analysis.getId());
                        b2_a2_4_repository.delete(id);
                    }
                }
                break;
            case Const.C1_C2_C3:
                if (level == 1) {
                    for (int id : idList) {
                        C1_C2_C3_1_Entry entry = c1_c2_c3_1_repository.findOne(id);
                        for (C1_C2_C3_1_SelectionEntry selection : entry.getSelects())
                            c1_c2_c3_1_selection_repository.delete(selection.getId());
                        for (C1_C2_C3_1_AnalysisEntry analysis : entry.getAnalyses())
                            c1_c2_c3_1_analysis_repository.delete(analysis.getId());
                        c1_c2_c3_1_repository.delete(id);
                    }
                } else {
                    for (int id : idList) {
                        C1_C2_C3_4_Entry entry = c1_c2_c3_4_repository.findOne(id);
                        for (C1_C2_C3_4_SelectionEntry selection : entry.getSelects())
                            c1_c2_c3_4_selection_repository.delete(selection.getId());
                        for (C1_C2_C3_4_AnalysisEntry analysis : entry.getAnalyses())
                            c1_c2_c3_4_analysis_repository.delete(analysis.getId());
                        c1_c2_c3_4_repository.delete(id);
                    }
                }
                break;
            case Const.E_F_D:
                if (level == 1) {
                    for (int id : idList) {
                        E_F_D_1_Entry entry = e_f_d_1_repository.findOne(id);
                        for (E_F_D_1_SelectionEntry selection : entry.getSelects())
                            e_f_d_1_selection_repository.delete(selection.getId());
                        for (E_F_D_1_AnalysisEntry analysis : entry.getAnalyses())
                            e_f_d_1_analysis_repository.delete(analysis.getId());
                        e_f_d_1_repository.delete(id);
                    }
                } else {
                    for (int id : idList) {
                        E_F_D_4_Entry entry = e_f_d_4_repository.findOne(id);
                        for (E_F_D_4_SelectionEntry selection : entry.getSelects())
                            e_f_d_4_selection_repository.delete(selection.getId());
                        for (E_F_D_4_AnalysisEntry analysis : entry.getAnalyses())
                            e_f_d_4_analysis_repository.delete(analysis.getId());
                        e_f_d_4_repository.delete(id);
                    }
                }
                break;
            default:
                baseResponse.setMsg("删除失败");
                baseResponse.setCode(0);
                break;
        }
        return baseResponse;
    }
}
