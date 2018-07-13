package cn.mariojd.repository;

import cn.mariojd.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Mario
 */
public interface AdminRepository extends JpaRepository<Admin, Integer> {

    Admin findByAccountAndPassword(String account, String password);
}
