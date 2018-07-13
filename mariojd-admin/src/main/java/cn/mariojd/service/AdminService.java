package cn.mariojd.service;

import cn.mariojd.base.BaseService;
import cn.mariojd.dto.MessageResult;
import cn.mariojd.entity.Admin;
import cn.mariojd.entity.User;
import cn.mariojd.enums.MessageEnum;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Mario
 */
@Service
@Transactional(readOnly = true)
public class AdminService extends BaseService {

    @CachePut(value = "admin", key = "#aid")
    @Transactional
    public MessageResult update(Admin admin, Admin sessionAdmin) {
        Admin admin1 = adminRepository.findOne(admin.getAid());
        if (null != admin1) {
            if (!admin.getPassword().equals("")) {
                admin1.setPassword(admin.getPassword());
            }
            admin1.setNickname(admin.getNickname());
            sessionAdmin.setNickname(admin.getNickname());
            adminRepository.saveAndFlush(admin1);
            return new MessageResult(MessageEnum.ADMIN_UPDATESUCCESS);
        } else {
            return new MessageResult(MessageEnum.ADMIN_UPDATEFAILURE);
        }
    }

    @Cacheable(value = "admin", key = "#aid")
    public Admin findAdmin(Integer aid) {
        return adminRepository.findOne(aid);
    }

    @Cacheable(value = "sessionAdmin")
    public Admin login(String account, String password) {
        return adminRepository.findByAccountAndPassword(account, password);
    }
}
