package com.example.miniempiresbe;

import org.springframework.boot.SpringApplication;

public class TestMiniEmpiresBeApplication {

    public static void main(String[] args) {
        SpringApplication.from(MiniEmpiresBeApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
