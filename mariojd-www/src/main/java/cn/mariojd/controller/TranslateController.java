package cn.mariojd.controller;

import cn.mariojd.base.BaseController;
import cn.mariojd.util.BaiduUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Mario
 */
@Controller
@RequestMapping("/translation")
public class TranslateController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TranslateController.class);

    @GetMapping
    public String translate() {
        return "translate/translate";
    }

    @PostMapping(produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object translation(String sourceText, String source, String target) {
        try {
            if (target.equals("auto")) {
                //若sourceText全为英文字母则选择英译中，否则默认为中译英
                if (sourceText.replaceAll("[\\p{Punct}\\p{Space}]+", "").trim().matches("[a-zA-Z]+")) {
                    target = "zh";
                    return BaiduUtil.translate(sourceText, source, target);
                } else {
                    target = "en";
                    return BaiduUtil.translate(sourceText, source, target);
                }
            }
            return BaiduUtil.translate(sourceText, source, target);
        } catch (Exception e) {
            LOGGER.error("TranslateController Error : " + e.getMessage());
            return "系统错误";
        }
    }
}
