package com.xcbeyond.springboot.grpc.client.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.xcbeyond.springboot.grpc.client.config.JWTSecurityConfiguration;
import com.xcbeyond.springboot.grpc.client.service.GrpcClientService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Auther: xcbeyond
 * @Date: 2019/3/7 11:44
 */
@RestController
@RefreshScope
public class GrpcClientController {
    //@Value("${priterValue}")
    //private String priterValue;
    @Resource
    private GrpcClientService grpcClientService;

    @RequestMapping("/")
    public String printMessage(@RequestParam(defaultValue = "jack") String name) {
        return grpcClientService.sendMessage(name);
    }

    @RequestMapping("/auth/realms/TestRealm/protocol/openid-connect/certs")
    public String printMessage() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("keys",JWTSecurityConfiguration.token);
        return jsonObject.toString();
    }

}
