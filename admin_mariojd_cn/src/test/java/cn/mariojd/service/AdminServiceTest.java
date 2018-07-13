package cn.mariojd.service;

import cn.mariojd.entity.User;
import cn.mariojd.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Mario
 */
@RunWith(SpringRunner.class)
public class AdminServiceTest {

    @Autowired
    private UserRepository userRepository;

}