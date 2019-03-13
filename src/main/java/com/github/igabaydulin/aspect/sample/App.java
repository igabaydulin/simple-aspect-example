package com.github.igabaydulin.aspect.sample;

import org.springframework.boot.Banner.Mode;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class App {

  public static void main(String[] args) {
    new SpringApplicationBuilder(App.class).bannerMode(Mode.OFF).web(WebApplicationType.SERVLET).run(args);
  }
}
