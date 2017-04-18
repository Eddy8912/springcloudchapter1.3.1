package com.zhangtao.springcloudlearn.controller;

import com.zhangtao.springcloudlearn.consumeinterface.ConsumeClient;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by hzzhangtao1 on 2017/4/5.
 */
@RestController
@RequestMapping(value = "/consume")
public class ConsumeController {

    private final Logger logger = Logger.getLogger(getClass());
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ConsumeClient consumeClient;

    @Autowired
    private DiscoveryClient discoveryClient;
    // discoveryClient获取服务列表中，应用名为provide-service一个服务注册信息
    public String serviceUrl() {
        List<ServiceInstance> list = discoveryClient
                .getInstances("provide-service");
        if (list != null && list.size() > 0) {
            return String.valueOf(list.get(0).getUri());
        }
        return null;
    }
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public Integer add() {
        //String providerServiceUrl = serviceUrl();
        //logger.info("service url:"+providerServiceUrl);
        return consumeClient.add(20,30);
    }
}
