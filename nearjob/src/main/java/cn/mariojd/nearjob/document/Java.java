package cn.mariojd.nearjob.document;

import cn.mariojd.nearjob.base.BaseDocument;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author Jared
 * @date 2018/8/21 9:01
 */
@Document(indexName = "nearjob_java", type = "java")
public class Java extends BaseDocument {


}
