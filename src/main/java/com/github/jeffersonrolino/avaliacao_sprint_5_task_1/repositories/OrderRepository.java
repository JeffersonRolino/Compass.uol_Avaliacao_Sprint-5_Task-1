package com.github.jeffersonrolino.avaliacao_sprint_5_task_1.repositories;

import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
