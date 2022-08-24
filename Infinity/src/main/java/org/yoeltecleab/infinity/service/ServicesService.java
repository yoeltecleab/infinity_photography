package org.yoeltecleab.infinity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yoeltecleab.infinity.model.Services;
import org.yoeltecleab.infinity.repository.ServiceRepository;

@Service
public class ServicesService {

    @Autowired
    private ServiceRepository serviceRepository;

    /**
     * @return the service with occasion matching the argument
     */
    public Services findServiceByOccasion(String occasion) {
        return serviceRepository.findByOccasion(occasion);
    }
}
