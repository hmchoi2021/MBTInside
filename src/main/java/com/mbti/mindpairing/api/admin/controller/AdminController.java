package com.mbti.mindpairing.api.admin.controller;

import com.mbti.mindpairing.api.admin.dto.Admin;
import com.mbti.mindpairing.api.admin.service.AdminService;
import com.mbti.mindpairing.api.common.constant.CommonCode;
import com.mbti.mindpairing.api.common.dto.ApiResponse;
import com.mbti.mindpairing.api.common.exception.MBTIException;
import com.mbti.mindpairing.api.login.dto.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/*
    Author : Min
 */
@Tag(name = "Admin API v1")
@RestController
public class AdminController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private AdminService adminService;

    @Operation(summary = "[Admin] Login", description = "[Admin] 로그인, id와 passwd 받아서 세션 리턴")
    @ResponseBody
    @PostMapping("/v1/admin/login")
    public ApiResponse<Admin.GetAdminLoginResponse> v1AdminLoginPost(
            HttpServletRequest request,
            @RequestBody Admin.GetAdminLoginRequest body) throws MBTIException {
        return new ApiResponse<Admin.GetAdminLoginResponse>(CommonCode.SUCCESS, adminService.loginUsingIdAndPaswd(request, body));
    }

    @Operation(summary = "[Admin] Logout", description = "[Admin] 로그아웃")
    @ResponseBody
    @PostMapping("/v1/admin/logout")
    public ApiResponse<Admin.GetAdminLoginResponse> v1AdminLogoutPost(
            HttpServletRequest request) throws MBTIException {
        return new ApiResponse<Admin.GetAdminLoginResponse>(CommonCode.SUCCESS, adminService.logout(request));
    }

    @Operation(summary = "회원목록 List 조회", description = "[Admin] 회원 목록 조회")
    @ResponseBody
    @GetMapping("/v1/admin/users")
    public ApiResponse<Admin.UserInfoList> v1userInfoListPost(
            HttpServletRequest request,
            @RequestParam(value = "nickname", required = false) String nickname,
            @RequestParam(value = "mbti") Long mbti,
            @RequestParam(value = "blackList") boolean blackList,
            @RequestParam(value = "male") boolean male,
            @RequestParam(value = "female") boolean female,
            @RequestParam(value = "notConnected") boolean notConnected,
            @RequestParam(value = "accountWithdrawal") boolean accountWithdrawal,
            @RequestParam(value = "pageNo", required = false) Integer pageNo,
            @RequestParam(value = "pageSize", required = false) Integer pageSize) throws MBTIException {

        if(pageNo == null) pageNo = 0;
        if(pageSize == null) pageSize = 10;
        return new ApiResponse<Admin.UserInfoList>(CommonCode.SUCCESS, adminService.getUserInfoList(request, nickname, mbti, blackList, male, female, notConnected, accountWithdrawal, pageNo, pageSize));
    }

    @Operation(summary = "회원검색 api", description = "[Admin] 회원 검색")
    @ResponseBody
    @GetMapping("/v1/admin/user/infos")
    public ApiResponse<User.UserInfoList> v1userInfosListPost(
            HttpServletRequest request,
            @RequestParam(value = "nickname", required = false) String nickname,
            @RequestParam(value = "pageNo", required = false) Integer pageNo,
            @RequestParam(value = "pageSize", required = false) Integer pageSize) throws MBTIException {

        if(pageNo == null) pageNo = 0;
        if(pageSize == null) pageSize = 10;
        return new ApiResponse<User.UserInfoList>(CommonCode.SUCCESS, adminService.getUserList(request, nickname, pageNo, pageSize));
    }


}
