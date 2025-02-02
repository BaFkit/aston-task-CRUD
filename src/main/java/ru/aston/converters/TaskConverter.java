package ru.aston.converters;

import ru.aston.dto.TaskDto;
import ru.aston.entity.Task;

public class TaskConverter {

    public TaskDto entityToDto(Task task){
        return TaskDto.builder().
                title(task.getTitle())
                .status(task.getStatus())
                .description(task.getDescription())
                .time_end(task.getTime_end())
                .build();
    }
}
