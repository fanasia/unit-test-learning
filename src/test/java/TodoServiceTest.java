import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import springboot.model.Todo;
import springboot.model.constants.TodoPriority;
import springboot.service.TodoService;
import springboot.repository.TodoRepository;

import java.util.ArrayList;
import java.util.List;

public class TodoServiceTest {

    private TodoService todoService;

    //  dibikinin implementasi palsu. kalo todoRepository nya belum kelar, gapapa.
    @Mock
    private TodoRepository todoRepository;

    @Before
    public void setUp() {
     //   this.todoRepository = new TodoRepository(); changed with mock
        MockitoAnnotations.initMocks(this);
        this.todoService = new TodoService(this.todoRepository);
    }

    @After
    public void tearDown() {
        //dilakukan pada semua mock yang ada
        //verify that its not called any other method. only get all
        Mockito.verifyNoMoreInteractions(todoRepository);
    }

    @Test
    public void getAllTest() throws Exception {
        //given
        //todo must return non empty list when get all called
        ArrayList<Todo> todos = new ArrayList<Todo>();
        todos.add(new Todo("Todo1", TodoPriority.MEDIUM));
        BDDMockito.given(todoRepository.getAll()).willReturn(todos);

        //when
        List<Todo> todoList = todoService.getAll();

        //assert that todo list is not null
        Assert.assertThat(todoList, Matchers.notNullValue());
        //assert that todo list is not empty
        Assert.assertThat(todoList.isEmpty(), Matchers.equalTo(false));
        //verify that get all is called
        BDDMockito.then(todoRepository).should().getAll();
    }

    public void saveTodoTest() throws Exception {
        // given
//        BDDMockito.given(this.todoRepository.getAll()).willReturn(todos);


        //when
        boolean check = this.todoService.saveTodo("Todo2", TodoPriority.MEDIUM);
        Todo todo = new Todo("Todo2", TodoPriority.MEDIUM);
        Assert.assertThat(check, Matchers.equalTo(false));
        BDDMockito.then(todoRepository).should().store(todo);

        //then
    }



//        if(todoList.isEmpty()) {
//            throw new Exception("LIST KOSONG");
//        }
//        else {
//            System.out.println("LIST ADA ISINYA");
//        }
}
