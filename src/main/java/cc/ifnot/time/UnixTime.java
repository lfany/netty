package cc.ifnot.time;

import java.util.Date;

public class UnixTime {

    private final long value;

    @SuppressWarnings("unused")
    public UnixTime() {
        this(System.currentTimeMillis() / 1000L + 2208988800L);
    }

    UnixTime(long value) {
        this.value = value;
    }

    private long value() {
        return value;
    }

    @Override
    public String toString() {
//        return "UnixTime{" +
//                "value=" + value +
//                '}';
        return new Date((value() - 2208988800L) * 1000L).toString();
    }
}
