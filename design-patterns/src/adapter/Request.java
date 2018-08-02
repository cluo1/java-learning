package adapter;

/**
 * Created by Jared on 2018/8/2 11:35.
 * 请求对象
 */
public class Request {

    private Adapter adapter;

    Request(Adapter adapter) {
        this.adapter = adapter;
    }

    void console() {
        System.out.println("初始输入380V电压～");
        adapter.transfer();
    }

}
