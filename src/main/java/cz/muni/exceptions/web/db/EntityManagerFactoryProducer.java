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

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Producer of EntityManagerFactory.
 *
 * @author Jan Ferko
 */
public class EntityManagerFactoryProducer {

    /**
     * Produces new instance of EntityManagerFactory.
     * @return instance of EntityManagerFactory
     */
    @Produces
    @ApplicationScoped
    public EntityManagerFactory create() {
        return Persistence.createEntityManagerFactory("exceptionsWebPU");
    }

    /**
     * Destroys given instance of EntityManagerFactory.
     *
     * @param emf entity manager factory, that should be destroyed.
     */
    public void destroy(@Disposes EntityManagerFactory emf) {
        emf.close();
    }
}
