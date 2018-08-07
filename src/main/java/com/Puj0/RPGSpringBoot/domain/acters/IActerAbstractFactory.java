package com.Puj0.RPGSpringBoot.domain.acters;

import com.Puj0.RPGSpringBoot.view.ActerRequest;

public interface IActerAbstractFactory {

    Acter createActer(ActerRequest acterRequest);
}
