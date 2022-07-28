package com.mbti.mindpairing.api.community.service;

import com.mbti.mindpairing.api.common.constant.CommonCode;
import com.mbti.mindpairing.api.common.exception.MBTIException;
import com.mbti.mindpairing.api.login.dto.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static com.mbti.mindpairing.api.login.service.LoginService.LOGIN_USER;

@Service
public class CommunityService {
//
//    public List<User.TermsInfoResponse> getCommunityHomeInfo(HttpServletRequest request) throws MBTIException {
//        HttpSession session = request.getSession(false);
//        if(session == null) {
//            throw new MBTIException(CommonCode.NOT_LOGINED);
//        }
//
//        Long userId = (Long) session.getAttribute(LOGIN_USER);
//
//
//
//    }
}
