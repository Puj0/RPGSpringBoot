package com.Puj0.RPGSpringBoot.mapper;

import com.Puj0.RPGSpringBoot.domain.acters.Acter;
import com.Puj0.RPGSpringBoot.view.ActerView;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ActerMapper {

    ActerView mapActerView(Acter acter);
}
