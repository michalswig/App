package com.crud.tasks.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class AdminConfig {
    @Value("${info.app.owner.name}")
    private String adminName;

    @Value("${info.app.administrator.email}")
    private String adminMail;

    @Value("${info.company.name}")
    private String companyName;

    @Value("${info.company.email}")
    private String companyEmail;

    @Value("${info.company.phone}")
    private String companyPhone;

    @Value("${info.company.goal}")
    private String companyGoal;
}

