package cn.mariojd.spi.service;

import java.util.Iterator;
import java.util.ServiceLoader;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Jared
 * @date 2018/12/1 16:28
 */
public class DriverManager {

    private static CopyOnWriteArrayList<ConnectInfo> registeredParsers = new CopyOnWriteArrayList<>();

    static {
        loadInitialParsers();
        System.out.println("SongParser initialized");
    }

    private static void loadInitialParsers() {
        ServiceLoader<IConnect> loadedParsers = ServiceLoader.load(IConnect.class);
        Iterator<IConnect> driversIterator = loadedParsers.iterator();
        try {
            while (driversIterator.hasNext()) {
                driversIterator.next();
            }
        } catch (Throwable t) {
            // Do nothing
        }
    }

    public static synchronized void registerParser(IConnect parser) {
        registeredParsers.add(new ConnectInfo(parser));
    }

//    public static Song getSong(byte[] data) {
//        for (ConnectInfo parserInfo : registeredParsers) {
//            try {
//                Song song = parserInfo.parser.parse(data);
//                if (song != null) {
//                    return song;
//                }
//            } catch (Exception e) {
//                //wrong parser, ignored it.
//            }
//        }
//        throw new RuntimeException("10001");
//    }

}
