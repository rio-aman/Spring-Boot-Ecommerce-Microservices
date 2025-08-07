package com.info.configdemo;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class BuildInfoController {

//    //    @Value("${OS:default}")
//    @Value("${build.id:default}")
//    private String buildId;
//
//    //    @Value("${PROCESSOR_LEVEL:default}")
//    @Value("${build.version:default}")
//    private String buildVersion;
//
//    //    @Value("${JAVA_HOME:default}")
//    @Value("${build.name:default}")
//    private String buildName;

    private BuildInfo buildInfo;

    @GetMapping("/build-info")
    public String getBuildInfo() {
        return "Build ID: " + buildInfo.getId() + ", Version: " + buildInfo.getVersion() + ", Name: " + buildInfo.getName();
    }
}
