package com.cfg.BMS.repository;


import com.cfg.BMS.model.Show;
import com.cfg.BMS.model.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat,Long>
{
    List<ShowSeat> findByShowId(Long movieId);



    List<ShowSeat> findByShowIdAndStatus(Long showId,String status);

}