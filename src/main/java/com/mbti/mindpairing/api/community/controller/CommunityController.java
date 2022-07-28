package com.mbti.mindpairing.api.community.controller;

import com.mbti.mindpairing.api.common.constant.CommonCode;
import com.mbti.mindpairing.api.common.dto.ApiResponse;
import com.mbti.mindpairing.api.common.exception.MBTIException;
import com.mbti.mindpairing.api.community.service.CommunityService;
import com.mbti.mindpairing.api.login.dto.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/*
    Author : Min
 */
@Tag(name = "Login API v1")
@RestController
public class CommunityController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommunityController.class);
//
//    @Autowired
//    private CommunityService communityService;
//
//    @Operation(summary = "커뮤니티 home 조회", description = "커뮤니티 home 리스트 반환")
//    @ResponseBody
//    @GetMapping("/v1/community/home")
//    public ApiResponse<List<User.TermsInfoResponse>> v1CommuityHomeGet(
//            HttpServletRequest request)
//    ) throws MBTIException {
//        return new ApiResponse<List<User.TermsInfoResponse>>(CommonCode.SUCCESS, communityService.getCommunityHomeInfo(request));
//    }
}
