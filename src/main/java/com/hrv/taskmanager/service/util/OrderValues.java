package com.hrv.taskmanager.service.util;

import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class OrderValues {

    public static final List<String> taskOrderBy = List.of("title","description","status","createdBy",
            "assignedTo","createdAt","dueDate");
}
