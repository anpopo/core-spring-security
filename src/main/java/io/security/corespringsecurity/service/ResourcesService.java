package io.security.corespringsecurity.service;

import io.security.corespringsecurity.domain.entity.Resources;

import java.util.List;

public interface ResourcesService {
    List<Resources> getResources();

    Resources getResources(Long id);

    void createResources(Resources resources);

    void deleteResources(Long id);
}
