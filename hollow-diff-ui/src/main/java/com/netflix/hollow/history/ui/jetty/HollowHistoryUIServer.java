/*
 *  Copyright 2016-2019 Netflix, Inc.
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 *
 */
package com.netflix.hollow.history.ui.jetty;

import com.netflix.hollow.api.consumer.HollowConsumer;
import com.netflix.hollow.history.ui.HollowHistoryUI;
import com.netflix.hollow.tools.history.HollowHistory;
import java.util.TimeZone;

/**
 * @deprecated
 * This class is replaced by {@link com.netflix.hollow.history.ui.webserver.HollowHistoryUIServer}
 */
@Deprecated
public class HollowHistoryUIServer {

    private final com.netflix.hollow.history.ui.webserver.HollowHistoryUIServer server;

    public HollowHistoryUIServer(HollowConsumer consumer, int port, TimeZone timeZone) {
        server = new com.netflix.hollow.history.ui.webserver.HollowHistoryUIServer(consumer, port, timeZone);
    }

    public HollowHistoryUIServer(HollowConsumer consumer, int port) {
        server = new com.netflix.hollow.history.ui.webserver.HollowHistoryUIServer(consumer, port);
    }

    public HollowHistoryUIServer(HollowConsumer consumer, int numStatesToTrack, int port, TimeZone timeZone) {
        server = new com.netflix.hollow.history.ui.webserver.HollowHistoryUIServer( consumer, numStatesToTrack, port, timeZone);
    }

    public HollowHistoryUIServer(HollowConsumer consumer, int numStatesToTrack, int port) {
        server = new com.netflix.hollow.history.ui.webserver.HollowHistoryUIServer( consumer, numStatesToTrack, port);
    }

    public HollowHistoryUIServer(HollowHistory history, int port) {
        server = new com.netflix.hollow.history.ui.webserver.HollowHistoryUIServer( history, port);
    }

    public HollowHistoryUIServer(HollowHistoryUI ui, int port) {
       server = new com.netflix.hollow.history.ui.webserver.HollowHistoryUIServer(ui, port);
    }

    public HollowHistoryUIServer start() throws Exception {
        server.start();
        return this;
    }

    public HollowHistoryUIServer join() throws InterruptedException {
        server.join();
        return this;
    }

    public void stop() throws Exception {
        server.stop();
    }

    public HollowHistoryUI getUI() {
        return server.getUI();
    }

}
