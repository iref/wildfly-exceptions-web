/*
 * Copyright 2014 johnny.
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

package cz.muni.exceptions.web.pages;

import de.agilecoders.wicket.core.markup.html.bootstrap.common.NotificationPanel;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * Abstract page, that loads common resources and provides common layout.
 * 
 * @author Jan Ferko
 */
public abstract class AbstractExceptionsPage extends WebPage {        

    public AbstractExceptionsPage() {
    }

    public AbstractExceptionsPage(IModel<?> model) {
        super(model);
    }

    public AbstractExceptionsPage(PageParameters parameters) {
        super(parameters);
    }        

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);
        // load common assets for all implementations
        response.render(CssHeaderItem.forUrl(ExternalResources.BOOTSTRAP_CSS));
        response.render(JavaScriptHeaderItem.forUrl(ExternalResources.JQUERY));
        response.render(JavaScriptHeaderItem.forUrl(ExternalResources.BOOTSTRAP_JS));
    }    
        
    @Override
    protected void onInitialize() {
        super.onInitialize();
        // add notification panel
        NotificationPanel notifier = new NotificationPanel(ComponentIds.NOTIFIER);
        add(notifier);
        
        add(new BookmarkablePageLink(ComponentIds.BRAND, ExceptionsPage.class));
        add(new BookmarkablePageLink(ComponentIds.OVERVIEW, ExceptionsPage.class));
    }
    
    private static class ExternalResources {
        /** CDN url of Twitter Bootstrap Javascript. */
        private static final String BOOTSTRAP_JS = "//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js";
    
        /** CDN url of JQuery. */
        private static final String JQUERY = "//code.jquery.com/jquery-1.11.0.min.js";

        /** CDN url of Twitter Bootstrap CSS. */
        private static final String BOOTSTRAP_CSS = "//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css";
    }
        
    /**
     * Page components ids.
     */
    private static class ComponentIds {
        public static final String NOTIFIER = "notifier";
        public static final String BRAND = "brandLink";
        public static final String OVERVIEW = "overviewLink";
    }
    
    /**
     * Names of page parameters supported by this page.
     */
    protected static class RequestParameters {
        public static final String TICKET_ID = "ticketId";
    }
}
