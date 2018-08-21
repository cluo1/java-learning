package cn.mariojd.nearjob.document;

import cn.mariojd.nearjob.base.BaseDocument;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author Jared
 * @date 2018/8/21 9:16
 */
@Document(indexName = "near_python", type = "python")
public class NearPython extends BaseDocument {
}
