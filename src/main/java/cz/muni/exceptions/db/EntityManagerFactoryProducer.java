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

package cz.muni.exceptions.db;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Jan Ferko
 * @sa.date 2014-05-20T16:27:36+0100
 */
public class EntityManagerFactoryProducer {

    @Produces
    @ApplicationScoped
    public EntityManagerFactory create() {
        return Persistence.createEntityManagerFactory("exceptionsWebPU");
    }
    
    public void destroy(@Disposes EntityManagerFactory emf) {
        emf.close();
    }
}
