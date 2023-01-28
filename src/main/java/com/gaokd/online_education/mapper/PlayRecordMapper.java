package com.gaokd.online_education.mapper;

import com.gaokd.online_education.model.entity.PlayRecord;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayRecordMapper {

    int saveRecode(PlayRecord playRecord);
}
