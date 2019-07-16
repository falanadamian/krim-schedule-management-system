package com.falanadamian.krim.schedule.domain.mapper;

import com.falanadamian.krim.schedule.domain.dto.*;
import com.falanadamian.krim.schedule.domain.model.*;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {})
public interface LimitConfigMapper extends EntityMapper<LimitConfigDTO, LimitConfig> {

}
