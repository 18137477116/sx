package com.elltor.greenlandsystem.modules.security.service;

import com.elltor.greenlandcommon.utils.EncryptUtils;
import com.elltor.greenlandcommon.utils.RedisUtils;
import com.elltor.greenlandcommon.utils.StringUtils;
import com.elltor.greenlandsystem.modules.security.config.bean.SecurityProperties;
import com.elltor.greenlandsystem.modules.security.dto.OnlineUserDTO;
import com.elltor.greenlandsystem.modules.security.dto.SecurityUserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class OnlineUserService {

    private final SecurityProperties properties;

    private final RedisUtils redisUtils;

    /**
     * 保存在线用户信息
     * TODO:@RenShiWei 2020/11/18 description:测试是否可以获取手机端的ip地址等信息
     *
     * @param securityUserDTO /
     * @param token      /
     * @param request    /
     */
    public void save(SecurityUserDTO securityUserDTO, String token, HttpServletRequest request) {
        String ip = StringUtils.getIp(request);
        String browser = StringUtils.getBrowser(request);
        String address = StringUtils.getCityInfo(ip);
        OnlineUserDTO onlineUserDTO = new OnlineUserDTO();
        try {
            //token加密策略改为基于HuTool的DES加密策略，并混入逻辑运算  @RenShiWei 2020/11/17
            BeanUtils.copyProperties(securityUserDTO.getEmployeeDTO(),onlineUserDTO);
            onlineUserDTO.setKey(EncryptUtils.desEncrypt(token));
            onlineUserDTO.setLoginTime(new Date());

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        redisUtils.set(properties.getOnlineKey() + token, onlineUserDTO, properties.getTokenValidityInSeconds() / 1000);
    }

    /**
     * 查询全部数据
     * modify @RenShiWei 2020/11/22 description:Pageable -> PageVO  Map<String, Object> -> IPage<OnlineUserDto>
     *
     * @param filter 模糊匹配参数
     * @param pageVO 分页实体
     * @return 分页后的IPage
     */
//    public IPage<OnlineUserDTO> getAll(String filter, PageVO pageVO) {
//        List<OnlineUserDto> onlineUserDtoList = getAll(filter);
//        return PageUtil.toPage(pageVO, onlineUserDtoList);
//    }

    /**
     * 查询全部数据，不分页
     *
     * @param filter /
     * @return /
     */
    public List<OnlineUserDTO> getAll(String filter) {
        List<String> keys = redisUtils.scan(properties.getOnlineKey() + "*");
        Collections.reverse(keys);
        List<OnlineUserDTO> onlineUserDtos = new ArrayList<>();
        for (String key : keys) {
            OnlineUserDTO onlineUserDto = (OnlineUserDTO) redisUtils.get(key);
            if (StringUtils.isNotBlank(filter)) {
                if (onlineUserDto.toString().contains(filter)) {
                    onlineUserDtos.add(onlineUserDto);
                }
            } else {
                onlineUserDtos.add(onlineUserDto);
            }
        }
        onlineUserDtos.sort((o1, o2) -> o2.getLoginTime().compareTo(o1.getLoginTime()));
        return onlineUserDtos;
    }

    /**
     * 踢出用户
     *
     * @param key /
     */
    public void kickOut(String key) {
        key = properties.getOnlineKey() + key;
        redisUtils.del(key);
    }

    /**
     * 退出登录
     *
     * @param token /
     */
    public void logout(String token) {
        String key = properties.getOnlineKey() + token;
        redisUtils.del(key);
    }

    /**
     * 导出
     *
     * @param all      /
     * @param response /
     * @throws IOException /
     */
//    public void download(List<OnlineUserDto> all, HttpServletResponse response) throws IOException {
//        List<Map<String, Object>> list = new ArrayList<>();
//        for (OnlineUserDto user : all) {
//            Map<String, Object> map = new LinkedHashMap<>();
//            map.put("用户名", user.getUserName());
//            map.put("部门", user.getDept());
//            map.put("登录IP", user.getIp());
//            map.put("登录地点", user.getAddress());
//            map.put("浏览器", user.getBrowser());
//            map.put("登录日期", user.getLoginTime());
//            list.add(map);
//        }
//        FileUtils.downloadExcel(list, response);
//    }

    /**
     * 查询用户
     *
     * @param key /
     * @return /
     */
    public OnlineUserDTO getOne(String key) {
        return (OnlineUserDTO) redisUtils.get(key);
    }

    /**
     * 检测用户是否在之前已经登录，已经登录踢下线
     *
     * @param userName 用户名
     */
    public void checkLoginOnUser(String userName, String ignoreToken) {
        List<OnlineUserDTO> onlineUserDtos = getAll(userName);
        if (onlineUserDtos == null || onlineUserDtos.isEmpty()) {
            return;
        }
        for (OnlineUserDTO onlineUserDto : onlineUserDtos) {
            if (onlineUserDto.getUsername().equals(userName)) {
                try {
                    //token加密策略改为基于HuTool的DES加密策略，并混入逻辑运算  @RenShiWei 2020/11/17
                    String token = EncryptUtils.desDecrypt(onlineUserDto.getKey());
                    if (StringUtils.isNotBlank(ignoreToken) && ! ignoreToken.equals(token)) {
                        this.kickOut(token);
                    } else if (StringUtils.isBlank(ignoreToken)) {
                        this.kickOut(token);
                    }
                } catch (Exception e) {
                    log.error("checkUser is error", e);
                }
            }
        }
    }

    /**
     * 根据用户名强退用户
     *
     * @param username /
     */
    @Async
    public void kickOutForUsername(String username) {
        List<OnlineUserDTO> onlineUsers = getAll(username);
        for (OnlineUserDTO onlineUser : onlineUsers) {
            if (onlineUser.getUsername().equals(username)) {
                kickOut(onlineUser.getKey());
            }
        }
    }

    /**
     * 清理特定用户缓存信息<br>
     * 用户信息变更时
     *
     * @param userName /
     */
    public void cleanUserCache(String userName) {
        if (StringUtils.isNotEmpty(userName)) {
            redisUtils.del(userName);
        }
    }
}
