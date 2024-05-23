package ru.ssau.simd.pojo;

import lombok.Data;
import ru.ssau.simd.entity.Log;

@Data
public class LogPojo {
    private Long id;
    private String filepath;

    public static LogPojo fromEntity(Log log) {
        LogPojo pojo = new LogPojo();

        pojo.setId(log.getId());
        pojo.setFilepath(log.getFilepath());

        return pojo;
    }

    public static Log toEntity(LogPojo pojo) {
        Log log = new Log();

        log.setId(pojo.getId());
        log.setFilepath(pojo.getFilepath());

        return log;
    }
}
