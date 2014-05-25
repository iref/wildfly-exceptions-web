/*
 * Copyright 2014 Jan Ferko.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cz.muni.exceptions.web.db;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * Producer, that creates new instances of EntityManager.
 *
 * @author Jan Ferko
 */
public class EntityManagerProducer {

    /** Entity Manager Factory, that is used to create new Entity Manager */
    @Inject
    private EntityManagerFactory emf;

    /**
     * Produces new instance of Entity Manager
     * @return entity manager
     */
    @Produces
    @RequestScoped
    public EntityManager create() {
        return emf.createEntityManager();
    }

    /**
     * Destroys given instance of entity manager
     * @param em entity manager, that should be destroyed.
     */
    public void destroy(@Disposes EntityManager em) {
        em.close();
    }

}
