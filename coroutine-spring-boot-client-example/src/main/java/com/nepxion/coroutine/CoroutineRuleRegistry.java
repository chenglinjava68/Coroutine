package com.nepxion.coroutine;

/**
 * <p>Title: Nepxion Coroutine</p>
 * <p>Description: Nepxion Coroutine For Distribution</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nepxion.coroutine.common.constant.CoroutineConstant;
import com.nepxion.coroutine.common.property.CoroutineContent;
import com.nepxion.coroutine.common.property.CoroutinePropertiesManager;
import com.nepxion.coroutine.registry.RegistryLauncher;
import com.nepxion.coroutine.registry.zookeeper.ZookeeperRegistryLauncher;

public class CoroutineRuleRegistry {
    private static final Logger LOG = LoggerFactory.getLogger(CoroutineRuleRegistry.class);

    public static void main(String[] args) throws Exception {
        RegistryLauncher launcher = new ZookeeperRegistryLauncher();
        launcher.setProperties(CoroutinePropertiesManager.getProperties());
        launcher.start();

        launcher.getRegistryExecutor().registerRule("Distribution PayRoute", "Distribution Rule");
        launcher.getRegistryExecutor().persistRule("Distribution PayRoute", "Distribution Rule", new CoroutineContent("rule.xml", CoroutineConstant.ENCODING_UTF_8).getContent());

        LOG.info("规则 : \n{}", launcher.getRegistryExecutor().retrieveRule("Distribution PayRoute", "Distribution Rule"));

        System.in.read();
    }
}