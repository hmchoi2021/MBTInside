package com.mbti.mindpairing.api.admin.service;

import com.mbti.mindpairing.api.admin.dto.Admin;
import com.mbti.mindpairing.api.admin.entity.AdminUserEntity;
import com.mbti.mindpairing.api.admin.repository.LoginRepository;
import com.mbti.mindpairing.api.common.constant.CommonCode;
import com.mbti.mindpairing.api.common.exception.MBTIException;
import com.mbti.mindpairing.api.login.dto.User;
import com.mbti.mindpairing.api.login.entity.UserEntity;
import com.mbti.mindpairing.api.login.repository.MbtiRepository;
import com.mbti.mindpairing.api.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.mbti.mindpairing.api.login.service.LoginService.LOGIN_USER;

@Service
public class AdminService {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MbtiRepository mbtiRepository;

    public Admin.GetAdminLoginResponse loginUsingIdAndPaswd(HttpServletRequest request, Admin.GetAdminLoginRequest body) throws MBTIException {
        HttpSession session = request.getSession(false);
        if(session != null) throw new MBTIException(CommonCode.ALREADY_LOGINED);

        AdminUserEntity adminUserEntity = loginRepository.getIdAndPasswdById(body.getId());
        if(adminUserEntity == null || adminUserEntity.getPassword() == null || !adminUserEntity.getPassword().equals(body.getHashed_passwd()))
            throw new MBTIException(CommonCode.USER_IS_NOT_VALID);

        request.getSession(true);
        return new Admin.GetAdminLoginResponse(User.LoginStatus.LOGIN);
    }

    public Admin.GetAdminLoginResponse logout(HttpServletRequest request) throws MBTIException {
        HttpSession session = request.getSession(false);
        if(session == null) throw new MBTIException(CommonCode.NOT_LOGINED);
        session.invalidate();
        return new Admin.GetAdminLoginResponse(User.LoginStatus.LOGOUT);
    }

    public Admin.UserInfoList getUserInfoList(HttpServletRequest request,
                                              String nickname,
                                              Long mbti,
                                              boolean blackList,
                                              boolean male,
                                              boolean female,
                                              boolean notConnected,
                                              boolean accountWithdrawal,
                                              Integer pageNo,
                                              Integer pageSize) throws MBTIException {

        HttpSession session = request.getSession(false);
        if(session == null) throw new MBTIException(CommonCode.NOT_LOGINED);

        if(mbtiRepository.findById(mbti).isEmpty()) throw new MBTIException(CommonCode.MBTI_NOT_EXISTS);

        Long[] userIdList = {11L, 10L, 12L, 13L, 14L,
                             21L, 20L, 22L, 113L, 214L,
                             31L, 30L, 32L, 123L, 314L,
                             41L, 40L, 42L, 133L, 414L,
                             51L, 50L, 52L, 143L, 514L,
                             61L, 60L, 62L, 153L, 614L};

        String[] nickNameList = {"넘어지는바람", "흔들리는자세", "인팁찾는남자", "너구리와오리", "신라면",
                                 "진라면큰사발", "인공지능여자", "머신러닝학습", "초코라떼라지", "감바스와감자",
                                 "달콤한사나이", "인팁엔팁꿀팁", "머신러닝추론", "초코라떼스몰", "고구마",
                                 "칙촉열두박스", "연봉이억도전", "네이버카카오", "삼성전자떡상", "사이다한그릇",
                                 "아웃백과빕스", "소나타그랜져", "컵라면두그릇", "스프링부트2", "스프링부트3",
                                 "스프링부트4", "스프링부트5", "스프링부트6", "스프링부트7", "스프링부트8"};


        String[] mbtiList = {"INTP", "ENTP", "ENTJ", "ENFP", "INFP",
                                "INTP", "ENTP", "ENTJ", "ENFP", "INFP",
                                "INTP", "ENTP", "ENTJ", "ENFP", "INFP",
                                "INTP", "ENTP", "ENTJ", "ENFP", "INFP",
                                "INTP", "ENTP", "ENTJ", "ENFP", "INFP"};

        int[] numOfPostsList = {3,2,1,0,5,
                                1,2,55,41,3,
                                13,41,23,33,51,
                                92,0,1,2,3,
                                33,22,11,41,3,
                                0,1,2,5,4};

        int[] numOfCommentsList = {13,41,23,33,51,
                                    1,2,55,41,3,
                                    92,0,1,2,3,
                                    33,22,11,41,3,
                                    1,66,1,51,3,
                                    0,1,2,5,4};

        int[] periodOfBlackListS = {0,0,0,10,10,
                                    0,0,0,10,10,
                                    0,0,0,10,10,
                                    0,0,0,10,10,
                                    0,0,0,10,10,
                                    0,0,0,10,10
        };
        List<Admin.UserInfo> userInfos = new ArrayList<>();
        if(pageNo*pageSize < 30) {
            for(int i = 0; i < pageSize; i++) {
                userInfos.add(new Admin.UserInfo(
                        userIdList[i + pageSize * pageNo],
                        nickNameList[i + pageSize * pageNo],
                        mbtiList[i + pageSize * pageNo],
                        numOfPostsList[i + pageSize * pageNo],
                        numOfCommentsList[i + pageSize * pageNo],
                        periodOfBlackListS[i + pageSize * pageNo]
                ));
            }
        }
        return new Admin.UserInfoList(userInfos, 30L, 100L, 32L, 30L, pageSize, pageNo);
    }

    public User.UserInfoList getUserList(HttpServletRequest request, String nickname, Integer pageNo, Integer pageSize) throws MBTIException {
        HttpSession session = request.getSession(false);
        if(session == null) throw new MBTIException(CommonCode.NOT_LOGINED);

//        Long id = (Long) session.getAttribute(LOGIN_USER);
//        System.err.println(id);
//        Optional<AdminUserEntity> userEntity = loginRepository.findById(id);
//        if(!userEntity.isPresent()) {
//            throw new MBTIException(CommonCode.USER_IS_NOT_EXISTED);
//        }

        User.UserInfoList userInfoList = null;
        if(nickname == null) {
            if(pageSize > 0) {
                int fetchCount = userRepository.countActiveUser();
                int total = fetchCount > 0 ? fetchCount / pageSize : 0;
                total += fetchCount % pageSize > 0 ? 1 : 0;
                List<UserEntity> userEntities = userRepository.findAllUser(pageNo, pageSize);
                userInfoList = new User.UserInfoList(userEntities, total, pageNo, pageSize);
            }
        }else {
            UserEntity user = userRepository.findByNickName(nickname);
            List<UserEntity> userEntities = new ArrayList<>();
            userEntities.add(user);
            userInfoList = new User.UserInfoList(userEntities, 0, pageNo, pageSize);
        }

        return userInfoList;
    }
}
