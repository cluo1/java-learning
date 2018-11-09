package cn.mariojd.fantasy.mp.model.request;

import lombok.Data;

/**
 * @author Jared
 * @date 2018/11/9 15:26
 */
@Data
public class ArticleSearchVO {

    /**
     * 公众号Id
     */
    private Integer mpsId;

    /**
     * 搜索关键字
     */
    private String keyword;

}
