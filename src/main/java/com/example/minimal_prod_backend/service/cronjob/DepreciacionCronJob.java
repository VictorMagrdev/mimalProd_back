package com.example.minimal_prod_backend.service.cronjob;

import com.example.minimal_prod_backend.service.DepreciacionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DepreciacionCronJob {

    private static final Logger logger = LoggerFactory.getLogger(DepreciacionCronJob.class);

    @Autowired
    private DepreciacionService depreciacionService;

    // Ejecutar mensualmente el primer día del mes a las 2:00 AM
    @Scheduled(cron = "0 0 2 1 * ?")
    public void generarDepreciacionMensual() {
        logger.info("Iniciando cálculo de depreciación mensual");
        try {
            depreciacionService.calcularDepreciacionMensual();
        } catch (Exception e) {
            logger.error("Error en cálculo de depreciación mensual", e);
        }
    }

    // Ejecutar anualmente el 1 de enero a las 3:00 AM
    @Scheduled(cron = "0 0 3 1 1 ?")
    public void generarDepreciacionAnual() {
        try {
            depreciacionService.calcularDepreciacionAnual();
        } catch (Exception e) {
            logger.error("Error en cálculo de depreciación anual", e);
        }
    }
}
