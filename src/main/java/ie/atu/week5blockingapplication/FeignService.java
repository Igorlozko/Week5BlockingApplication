package ie.atu.week5blockingapplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeignService {
    private final TodoClient todoClient;
    @Autowired
    public FeignService(TodoClient todoClient){
        this.todoClient = todoClient;
    }
    public List<TodoResponse> getAllTodos() {
        return todoClient.getAllTodos();
    }
    public TodoResponse getTodoById(int id) {
        return todoClient.getTodoById(id);
    }
    public String getTodoJsonById(int id) {
        return todoClient.getTodoJsonById(id);
    }
    public TodoResponse fetchData(){
        TodoResponse td = todoClient.fetchData();
        System.out.println(td);
        try{
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return td;
    }

}
