package cn.mariojd.spi.service;

/**
 * @author Jared
 * @date 2018/12/1 16:30
 */
public class ConnectInfo {

    private IConnect connect;

    public ConnectInfo(IConnect connect) {
        this.connect = connect;
    }

    public IConnect getConnect() {
        return connect;
    }

    public void setConnect(IConnect connect) {
        this.connect = connect;
    }
}
