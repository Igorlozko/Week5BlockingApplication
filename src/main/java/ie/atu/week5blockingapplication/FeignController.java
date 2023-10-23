package ie.atu.week5blockingapplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
public class FeignController {

    @Autowired
    private final FeignService feignService;
    public FeignController(FeignService feignService){
        this.feignService = feignService;
    }
    @GetMapping("/todos")
    public List<TodoResponse> getAllTodos() {
        return feignService.getAllTodos();
    }
    @GetMapping("/todos/{id}")
    public TodoResponse getTodoById(@PathVariable("id") int id) {
        return feignService.getTodoById(id);
    }
    @GetMapping("/todos/{id}/json")
    public String getTodoJsonById(@PathVariable("id") int id) {
        return feignService.getTodoJsonById(id);
    }
    @GetMapping("/feign")
    public String getfeignData() throws Exception, InterruptedException{
        long startTime = System.currentTimeMillis();

        List<CompletableFuture<TodoResponse>> futures = new ArrayList<>();
         for(int i =0; i<10;i++){
             CompletableFuture<TodoResponse>future = CompletableFuture.supplyAsync(()-> feignService.fetchData());
             futures.add(future);
         }
         CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

         allOf.get();
         long endTime = System.currentTimeMillis();
         return "Total execution time:"+(endTime-startTime)+"ms";

    }
}
