package ru.aston.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

import java.util.Objects;


@Getter
@Setter
@AllArgsConstructor
public class Task {

    private Long id;
    private String title;
    private String status; // По-моему хотели с помощью enum сделать
    private String description;
    private String time_end;
    private Long executor_id;
    private Long author_id;
    private Long project_id;

    @Override
    public int hashCode() {
        return Objects.hash(id, title, status, description, time_end, executor_id, author_id,project_id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Task t = (Task) obj;
        return Objects.equals(id,t.id) && Objects.equals(title,t.title) && Objects.equals(status,t.status) &&
               Objects.equals(description,t.description) && Objects.equals(time_end,t.time_end) &&
               Objects.equals(executor_id,t.executor_id) && Objects.equals(author_id,t.author_id) &&
               Objects.equals(project_id, t.project_id);
    }
}
