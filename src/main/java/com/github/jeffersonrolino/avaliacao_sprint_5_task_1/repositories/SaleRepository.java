package com.github.jeffersonrolino.avaliacao_sprint_5_task_1.repositories;

import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
}
