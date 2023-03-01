package com.example.demo;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/api")
public class HelloApplication extends Application {
    public HelloApplication() {
        final ResourceConfig config = new ResourceConfig();
        config.register(HelloResource.class);
    }
}