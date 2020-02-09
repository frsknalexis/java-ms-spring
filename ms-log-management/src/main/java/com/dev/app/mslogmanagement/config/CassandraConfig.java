package com.dev.app.mslogmanagement.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.DropKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.KeyspaceOption;

@Configuration
public class CassandraConfig extends AbstractCassandraConfiguration {

	@Value("${spring.data.cassandra.keyspace-name}")
	private String KEY_SPACE;
	
	@Override
	protected String getKeyspaceName() {
		return KEY_SPACE;
	}
	
	@Override
	public SchemaAction getSchemaAction() {
		return SchemaAction.CREATE_IF_NOT_EXISTS;
	}
	
	@Override
	public String[] getEntityBasePackages() {
		return new String[] {"com.dev.app.mslogmanagement.entity"};
	}
	
	@Override
	protected boolean getMetricsEnabled() {
		return false;
	}
	
	@Override
	protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
		CreateKeyspaceSpecification specification = CreateKeyspaceSpecification.createKeyspace(KEY_SPACE)
					.ifNotExists().with(KeyspaceOption.DURABLE_WRITES, true)
					.withSimpleReplication();
		return Arrays.asList(specification);
	}
	
	@Override
	protected List<DropKeyspaceSpecification> getKeyspaceDrops() {
		return Arrays.asList(DropKeyspaceSpecification.dropKeyspace(KEY_SPACE));
	}
}
