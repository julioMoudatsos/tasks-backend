package br.ce.wcaquino.taskbackend.datautils.repo;

import br.ce.wcaquino.taskbackend.datautils.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepo extends JpaRepository<Task, Long>{

}
