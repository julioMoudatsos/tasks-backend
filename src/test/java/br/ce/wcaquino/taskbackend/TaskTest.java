package br.ce.wcaquino.taskbackend;

import br.ce.wcaquino.taskbackend.datautils.controller.TaskController;
import br.ce.wcaquino.taskbackend.datautils.model.Task;
import br.ce.wcaquino.taskbackend.datautils.repo.TaskRepo;
import br.ce.wcaquino.taskbackend.datautils.utils.ValidationException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

public class TaskTest {
    @Mock
    private TaskRepo todoRepo;

    @InjectMocks
    public TaskController controller = new TaskController();

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testandoTaskDataPassada() {
        String erro = null;
        Task tarefa = new Task();
        tarefa.setDueDate(LocalDate.of(2010,9,1));
        tarefa.setTask("UMA TASK");
        try {
            controller.save(tarefa);
        } catch (ValidationException e) {
            erro = e.toString();
        }

        Assert.assertNotNull(erro);
    }
    @Test
    public void testandoTaskSemData(){
        String erro = null;
        Task tarefa = new Task();
        tarefa.setTask("UMA TASK");
        try {
            controller.save(tarefa);
        } catch (ValidationException e) {
            erro = e.toString();
        }

        Assert.assertNotNull(erro);
    }

    @Test
    public void testandoTaskSemDesc(){
        String erro = null;
        Task tarefa = new Task();
        tarefa.setDueDate(LocalDate.now());
        try {
            controller.save(tarefa);
        } catch (ValidationException e) {
            erro = e.toString();
        }

        Assert.assertNull(erro);
    }

    @Test
    public void testandoTaskS(){
        Task tarefa = new Task();
        tarefa.setDueDate(LocalDate.now());
        tarefa.setTask("UMA TASK");
        try {
            controller.save(tarefa);
        } catch (ValidationException e) {
            Assert.fail();
        }
        Mockito.verify(todoRepo).save(tarefa);
    }

}
