package com.talk2tail.ownerdashboard.model.repo;

import com.talk2tail.common.model.event.TalkToTailEvent;
import com.talk2tail.ownerdashboard.presenter.dto.DogItemDTO;

import java.util.List;

public interface IOwnerDashboardRepo {

    List<DogItemDTO> getGoodDoggies(int count);

    List<TalkToTailEvent> getTestEvents();

}
