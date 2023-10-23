package ie.atu.week5blockingapplication;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "jsonplaceholder", url = "https://jsonplaceholder.typicode.com")
public interface TodoClient {
    @GetMapping("/todos/1")
    TodoResponse fetchData();
    @GetMapping("/todos")
    List<TodoResponse> getAllTodos();

    @GetMapping("/todos/{id}")
    TodoResponse getTodoById(@PathVariable("id") int id);

    @GetMapping("/todos/{id}")
    String getTodoJsonById(@PathVariable("id") int id);
}
