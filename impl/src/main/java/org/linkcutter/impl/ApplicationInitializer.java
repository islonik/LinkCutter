package org.linkcutter.impl;

import com.google.inject.Inject;
import com.google.inject.persist.PersistService;

/**
 * Initialize services.
 */
public class ApplicationInitializer {

    @Inject
    ApplicationInitializer(PersistService persistenceService) {
        // start JPA
        persistenceService.start();
    }

}
