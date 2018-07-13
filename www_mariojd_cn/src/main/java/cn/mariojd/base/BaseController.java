package cn.mariojd.base;

import cn.mariojd.service.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Mario
 */
public class BaseController {

    @Autowired
    protected UserService userService;

    @Autowired
    protected MessageService messageService;

    @Autowired
    protected SeckillService seckillService;

    @Autowired
    protected NoticeService noticeService;

    @Autowired
    protected ReadService readService;

}
