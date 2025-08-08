package com.configclient.config_cloud_client;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@AllArgsConstructor
@RefreshScope
public class BuildInfoController {

    @Value("${build.id:default}")
    private String buildId;

    @Value("${build.version:default}")
    private String buildVersion;

    @Value("${build.name:default}")
    private String buildName;

    @GetMapping("/build-info")
    public String getBuildInfo() {
        return "Build ID: " + buildId + ", Version: " + buildVersion + ", Name: " + buildName;
    }

//  this when using the BuildInfo class ADDED
//    private BuildInfo buildInfo;

//    @GetMapping("/build-info")
//    public String getBuildInfo() {
//        return "Build ID: " + buildInfo.getId() + ", Version: " + buildInfo.getVersion() + ", Name: " + buildInfo.getName();
//    }
}
