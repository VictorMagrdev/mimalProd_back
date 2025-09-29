package com.example.minimal_prod_backend.repository;

import com.example.minimal_prod_backend.entity.HojaTimesheet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HojaTimesheetRepository extends JpaRepository<HojaTimesheet,Long> {
}
