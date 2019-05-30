package com.activemq.local.activemqlocal;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class SampleAutowiredAmqRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("activemq:queue:inbound.queue")
            .to("sql:insert into table foo (c1, c1) values ('#','#')");

    }

}