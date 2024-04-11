package com.patients.demo.service;

import com.patients.demo.domain.TreatmentPlanDomain;
import com.patients.demo.entity.TreatmentPlan;
import com.patients.demo.repository.TreatmentPlanRepository;
import com.patients.demo.util.EntityToDomainConverter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TreatmentPlanServiceTest {

    @Mock
    private TreatmentPlanRepository treatmentPlanRepository;

    @Mock
    private EntityToDomainConverter entityToDomainConverter;

    @InjectMocks
    private TreatmentPlanService treatmentPlanService;

    @Test
    public void testGetAllActivePlans() {
        LocalDateTime now = LocalDateTime.now();

        TreatmentPlan activePlan = TreatmentPlan.builder()
                .id(1L)
                .endDateTime(now.plusDays(1))
                .build();
        TreatmentPlan notActivePlan = TreatmentPlan.builder()
                .id(1L)
                .endDateTime(now.minusDays(1))
                .build();
        List<TreatmentPlan> plans = List.of(activePlan, notActivePlan);

        when(treatmentPlanRepository.findAll()).thenReturn(plans);

        TreatmentPlanDomain activePlanDto = TreatmentPlanDomain.builder()
                .id(1L)
                .endDateTime(now.plusDays(1))
                .build();
        List<TreatmentPlanDomain> expected = List.of(activePlanDto);

        when(entityToDomainConverter.treatmentPlanDto(activePlan)).thenReturn(activePlanDto);

        List<TreatmentPlanDomain> result = treatmentPlanService.getAllActivePlans();

        verify(treatmentPlanRepository, times(1)).findAll();
        verify(entityToDomainConverter, times(1)).treatmentPlanDto(activePlan);

        assertEquals(1, result.size());
        assertEquals(expected.getFirst(), result.getFirst());
    }
}
