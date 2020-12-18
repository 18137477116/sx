package com.elltor.greenlandsystem.modules.security.controller;

import cn.hutool.core.util.ObjectUtil;
import com.elltor.greenlandcommon.config.bean.RsaProperties;
import com.elltor.greenlandcommon.enums.ResultEnum;
import com.elltor.greenlandcommon.exception.BadRequestException;
import com.elltor.greenlandcommon.response.Result;
import com.elltor.greenlandcommon.utils.RedisUtils;
import com.elltor.greenlandcommon.utils.RsaUtils;
import com.elltor.greenlandcommon.utils.SecurityUtils;
import com.elltor.greenlandsystem.modules.biz.service.IEmployeeService;
import com.elltor.greenlandsystem.modules.security.config.bean.SecurityProperties;
import com.elltor.greenlandsystem.modules.security.config.token.TokenProvider;
import com.elltor.greenlandsystem.modules.security.dto.AuthUserDTO;
import com.elltor.greenlandsystem.modules.security.dto.SecurityUserDTO;
import com.elltor.greenlandsystem.modules.security.service.OnlineUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Wangmingcan
 * @date 2020-08-23
 * 授权、根据token获取用户详细信息
 */
@Slf4j
@Controller
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Api(tags = "系统：系统授权接口")
public class AuthorizationController {

    private final SecurityProperties properties;
    private final RedisUtils redisUtils;
    private final OnlineUserService onlineUserService;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final IEmployeeService userService;

    @ApiOperation("登录授权")
    @PostMapping(value = "/login")
    public void login(@Validated @RequestBody AuthUserDTO authUser,
                                             HttpServletRequest request, HttpServletResponse response) throws Exception {

        /*加密!!, 正常也不会这么出现*/
        authUser.setPassword(RsaUtils.encryptByPublicKey(RsaProperties.publicKey,authUser.getPassword()));

        // 正常密码解密
        String password = RsaUtils.decryptByPrivateKey(RsaProperties.privateKey, authUser.getPassword());

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(authUser.getUsername(), password);

        Authentication authentication = null;
        try {
            authentication = authenticationManagerBuilder.getOrBuild().authenticate(authenticationToken);
        } catch (BadCredentialsException e) {
            //账号或密码错误
            throw new BadRequestException(ResultEnum.LOGIN_FAIL);
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 生成令牌 获取实现UserDetails的对象,此处即SecurityUserDTO, getPrincipal()会根据不同的认证获取不同对象
        final SecurityUserDTO securityUserDTO = (SecurityUserDTO) authentication.getPrincipal();
        String token = tokenProvider.createToken(authentication, securityUserDTO.getEmployeeDTO().getUsername());
        // 保存在线信息
        onlineUserService.save(securityUserDTO, token, request);
        // 返回 token 与 用户信息
        Map<String, Object> authInfo = new HashMap<String, Object>(2) {{
            put("token", properties.getTokenStartWith() + token);
            put("user", securityUserDTO);
        }};
        if (properties.isSingleLogin()) {
            //踢掉之前已经登录的token
            onlineUserService.checkLoginOnUser(authUser.getUsername(), token);
        }
        response.setHeader("Authorization",properties.getTokenStartWith() + token);
        response.sendRedirect("index.jsp");
    }

    @ApiOperation("获取用户信息")
    @GetMapping(value = "/info")
    public Result<UserDetails> getUserInfo() {
        UserDetails currentUser = SecurityUtils.getCurrentUser();
        if (ObjectUtil.isEmpty(currentUser)) {
            throw new BadRequestException(ResultEnum.LOGIN_USER_INFO_NOT_FOUND);
        }
        return Result.success(currentUser);
    }

    /*@ApiOperation("获取验证码")
    @AnonymousGetMapping(value = "/code")
    public Result<Map<String, Object>> getCode() {
        // 获取运算的结果
        Captcha captcha = loginProperties.getCaptcha();
        String uuid = properties.getCodeKey() + IdUtil.simpleUUID();
        //当验证码类型为 arithmetic时且长度 >= 2 时，captcha.text()的结果有几率为浮点型
        String captchaValue = captcha.text();
        if (captcha.getCharType() - 1 == LoginCodeEnum.arithmetic.ordinal() & captchaValue.contains(".")) {
            captchaValue = captchaValue.split("\\.")[0];
        }
        // 保存
        redisUtils.set(uuid, captchaValue, loginProperties.getLoginCode().getExpiration(), TimeUnit.MINUTES);
        log.info("登录图片验证码结果:" + captchaValue);
        // 验证码信息
        Map<String, Object> imgResult = new HashMap<String, Object>(2) {{
            put("img", captcha.toBase64());
            put("uuid", uuid);
        }};
        return Result.success(imgResult);
    }*/

    @ApiOperation("退出登录")
    @PostMapping(value = "/logout")
    public Result<Void> logout(HttpServletRequest request) {
        onlineUserService.logout(tokenProvider.getToken(request));
        return Result.success();
    }

    @ApiOperation("测试登录授权获取token，（传账号密码即可）")
    @PostMapping(value = "/testLogin")
    public Result<Map<String, Object>> testLogin(@Validated @RequestBody AuthUserDTO authUser,
                                                 HttpServletRequest request) throws Exception {
        String password = authUser.getPassword();
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(authUser.getUsername(), password);
        Authentication authentication = null;
        try {
            log.debug("authenticationManagerBuilder "+authenticationManagerBuilder);
            authentication = authenticationManagerBuilder.getOrBuild().authenticate(authenticationToken);
        } catch (BadCredentialsException e) {
            throw new BadRequestException(ResultEnum.LOGIN_FAIL);
        }
        log.debug("authentication "+authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 生成令牌
        final SecurityUserDTO securityUserDTO = (SecurityUserDTO) authentication.getPrincipal();
        String token = tokenProvider.createToken(authentication, securityUserDTO.getEmployeeDTO().getUsername());
        // 保存在线信息
        onlineUserService.save(securityUserDTO, token, request);
        // 返回 token 与 用户信息
        Map<String, Object> authInfo = new HashMap<String, Object>(2) {{
            put("token", properties.getTokenStartWith() + token);
            put("user", securityUserDTO);
        }};
        if (properties.isSingleLogin()) {
            //踢掉之前已经登录的token
            onlineUserService.checkLoginOnUser(authUser.getUsername(), token);
        }
        return Result.success(authInfo);
    }

}
