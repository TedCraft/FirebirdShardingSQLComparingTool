/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.zaxxer.hikari.HikariDataSource;
import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.infra.config.props.ConfigurationPropertyKey;
import org.apache.shardingsphere.infra.config.rule.RuleConfiguration;
import org.apache.shardingsphere.infra.config.mode.ModeConfiguration;
import org.apache.shardingsphere.mode.repository.standalone.StandalonePersistRepositoryConfiguration;
import org.apache.shardingsphere.infra.config.algorithm.AlgorithmConfiguration;
import org.apache.shardingsphere.sharding.api.config.ShardingRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.rule.ShardingTableRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.audit.ShardingAuditStrategyConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.keygen.KeyGenerateStrategyConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.sharding.StandardShardingStrategyConfiguration;
import org.apache.shardingsphere.broadcast.api.config.BroadcastRuleConfiguration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Properties;

public final class Configuration {

    private static final String HOST = "localhost";

    private static final int PORT = 3050;

    public static final String USER_NAME = "sysdba";

    public static final String PASSWORD = "masterkey";

    public DataSource createDataSource() throws SQLException {
        return ShardingSphereDataSourceFactory.createDataSource(createModeConfiguration(), createDataSourceMap(), createRuleConfiguration(), createProperties());
    }

    private static ModeConfiguration createModeConfiguration() {
        return new ModeConfiguration("Standalone", new StandalonePersistRepositoryConfiguration("JDBC", new Properties()));
    }

    private Map<String, DataSource> createDataSourceMap() {
        Map<String, DataSource> result = new LinkedHashMap<>();
        result.put("ds_0", createDataSource(String.format("%s/src/main/resources/database/shard.FDB", System.getProperty("user.dir"))));
        return result;
    }

    private DataSource createDataSource(final String dataSourceName) {
        HikariDataSource result = new HikariDataSource();
        result.setDriverClassName("org.firebirdsql.jdbc.FBDriver");
        result.setJdbcUrl(String.format("jdbc:firebirdsql://%s:%s/%s", HOST, PORT, dataSourceName));
        result.setUsername(USER_NAME);
        result.setPassword(PASSWORD);
        return result;
    }

    private Collection<RuleConfiguration> createRuleConfiguration() {
        Collection<RuleConfiguration> result = new LinkedList<>();
//        result.add(createShardingRuleConfiguration());
//        result.add(createBroadcastRuleConfiguration());
        return result;
    }

    private ShardingRuleConfiguration createShardingRuleConfiguration() {
        ShardingRuleConfiguration result = new ShardingRuleConfiguration();
        result.getTables().add(getOrderTableRuleConfiguration());
        result.getTables().add(getOrderItemTableRuleConfiguration());
        result.setDefaultDatabaseShardingStrategy(new StandardShardingStrategyConfiguration("user_id", "inline"));
        Properties props = new Properties();
        props.setProperty("algorithm-expression", "ds_${user_id % 2}");
        result.getShardingAlgorithms() .put("inline", new AlgorithmConfiguration("INLINE", props));
        result.getKeyGenerators().put("snowflake", new AlgorithmConfiguration("SNOWFLAKE", new Properties()));
        result.getAuditors().put("sharding_key_required_auditor", new AlgorithmConfiguration("DML_SHARDING_CONDITIONS", new Properties()));
        return result;
    }

    private BroadcastRuleConfiguration createBroadcastRuleConfiguration() {
        return new BroadcastRuleConfiguration(Collections.singleton("t_address"));
    }

    private static ShardingTableRuleConfiguration getOrderTableRuleConfiguration() {
        ShardingTableRuleConfiguration result = new ShardingTableRuleConfiguration("t_order", null);
        result.setKeyGenerateStrategy(new KeyGenerateStrategyConfiguration("order_id", "snowflake"));
        // result.setAuditStrategy(new ShardingAuditStrategyConfiguration(Arrays.asList("sharding_key_required_auditor"), true));
        return result;
    }

    private static ShardingTableRuleConfiguration getOrderItemTableRuleConfiguration() {
        ShardingTableRuleConfiguration result = new ShardingTableRuleConfiguration("t_order_item", null);
        result.setKeyGenerateStrategy(new KeyGenerateStrategyConfiguration("order_item_id", "snowflake"));
        return result;
    }

    private Properties createProperties() {
        Properties result = new Properties();
        result.setProperty(ConfigurationPropertyKey.SQL_SHOW.getKey(), "false");
        return result;
    }
}
