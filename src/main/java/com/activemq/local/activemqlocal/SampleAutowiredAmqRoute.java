package com.activemq.local.activemqlocal;

import javax.sql.DataSource;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;
import org.apache.camel.model.ModelCamelContext;
import org.apache.camel.model.RouteDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Component;

@Component
public class SampleAutowiredAmqRoute extends RouteBuilder {


	@Override
	protected ModelCamelContext createContainer() {
		SimpleRegistry registry = new SimpleRegistry();
		registry.put("dataSource", getDataSource());

		ModelCamelContext context = new DefaultCamelContext(registry);
		;
		return context;
	}

	@SuppressWarnings("rawtypes")
	public DataSource getDataSource() { 
	    DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create(); 
	    dataSourceBuilder.driverClassName("org.h2.Driver");
        dataSourceBuilder.url("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");
        dataSourceBuilder.username("SA");
        dataSourceBuilder.password("");
	    return dataSourceBuilder.build(); 
	}

	@Override
	public void configure() throws Exception {
		from("jms:queue:inbound.queue")
		.process(new InsertProcessor()).to("jdbc:dataSource");
//        .to("jms:queue:outbound.queue");

	}

}