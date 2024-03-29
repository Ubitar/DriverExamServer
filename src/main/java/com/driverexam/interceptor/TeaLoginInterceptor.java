package com.driverexam.interceptor;

import com.alibaba.fastjson.JSON;
import com.driverexam.common.BaseResponse;
import com.driverexam.common.Const;
import com.driverexam.entry.teacher.TeacherEntry;
import com.driverexam.repository.TeaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

@Controller
@Component
public class TeaLoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    TeaRepository teaRepository;

    private static final Set<String> notLoginPaths = new HashSet<>(1);

    static {
        notLoginPaths.add("/teacher/login");
        notLoginPaths.add("/teacher/register");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String basePath = request.getContextPath();
        String path = request.getRequestURI();
        if (!doLoginInterceptor(path, basePath)) {//是否进行登陆拦截
            return true;
        }

        String token = request.getHeader(Const.TOKEN);

        if (token==null) {
            BaseResponse baseResponse = new BaseResponse("权限不足",1);
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(baseResponse));
            return false;
        } else {
            TeacherEntry teacherEntry= teaRepository.findByToken(token);
            if(teacherEntry==null){
                BaseResponse baseResponse = new BaseResponse("请先登录",1);
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().write(JSON.toJSONString(baseResponse));
                return false;
            }else{
                return true;
            }
        }
    }

    /**
     * 是否进行登陆过滤
     *
     * @param path
     * @param basePath
     * @return
     */
    private boolean doLoginInterceptor(String path, String basePath) {
        path = path.substring(basePath.length());
        if (notLoginPaths.contains(path)) return false;
        return true;
    }

}
