package com.cfg.BMS.repository;


import com.cfg.BMS.model.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TheaterRepository extends JpaRepository<Theater,Long>
{
    Theater findById(long id);

    List<Theater> findAll ();
    List<Theater> findByCity (String city);
}
