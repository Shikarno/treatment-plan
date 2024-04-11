package com.patients.demo.service;

import com.patients.demo.domain.TreatmentTaskDomain;
import com.patients.demo.entity.TreatmentTask;
import com.patients.demo.repository.TreatmentTaskRepository;
import com.patients.demo.util.DomainToEntityConverter;
import com.patients.demo.util.EntityToDomainConverter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TreatmentTaskServiceTest {

    @Mock
    private EntityToDomainConverter entityToDomainConverter;

    @Mock
    private DomainToEntityConverter domainToEntityConverter;

    @Mock
    private TreatmentTaskRepository treatmentTaskRepository;

    @InjectMocks
    private TreatmentTaskService treatmentTaskService;

    @Test
    public void testCreate() {
        TreatmentTaskDomain taskDto = TreatmentTaskDomain.builder()
                .id(1L)
                .build();

        TreatmentTask taskEntity = TreatmentTask.builder()
                .id(1L)
                .build();

        when(domainToEntityConverter.treatmentTask(any(TreatmentTaskDomain.class))).thenReturn(taskEntity);
        when(treatmentTaskRepository.save(any(TreatmentTask.class))).thenReturn(taskEntity);

        TreatmentTaskDomain expectedDto = TreatmentTaskDomain.builder()
                .id(1L)
                .build();
        when(entityToDomainConverter.treatmentTaskDto(any(TreatmentTask.class))).thenReturn(expectedDto);

        TreatmentTaskDomain result = treatmentTaskService.create(taskDto);

        assertEquals(expectedDto, result);
        verify(domainToEntityConverter, times(1)).treatmentTask(any(TreatmentTaskDomain.class));
        verify(treatmentTaskRepository, times(1)).save(any(TreatmentTask.class));
        verify(entityToDomainConverter, times(1)).treatmentTaskDto(any(TreatmentTask.class));
    }
}
