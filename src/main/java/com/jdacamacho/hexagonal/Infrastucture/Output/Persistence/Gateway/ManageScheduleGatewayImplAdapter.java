package com.jdacamacho.hexagonal.Infrastucture.Output.Persistence.Gateway;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.jdacamacho.hexagonal.Application.Output.ManageScheduleGatewayIntPort;
import com.jdacamacho.hexagonal.Domain.Objects.Schedule;
import com.jdacamacho.hexagonal.Infrastucture.Output.Persistence.Entities.ScheduleEntity;
import com.jdacamacho.hexagonal.Infrastucture.Output.Persistence.Repositories.ScheduleRepository;

@Service
public class ManageScheduleGatewayImplAdapter implements ManageScheduleGatewayIntPort{
    private final ScheduleRepository serviceBD;
    private final ModelMapper mapper;

    public ManageScheduleGatewayImplAdapter(ScheduleRepository serviceBD,
                                    ModelMapper mapper){
        this.serviceBD = serviceBD;
        this.mapper = mapper;
    }

    @Override
    public Schedule save(Schedule schedule) {
        ScheduleEntity scheduleToSave = this.mapper.map(schedule, ScheduleEntity.class);
        ScheduleEntity scheduleSaved = this.serviceBD.save(scheduleToSave);
        Schedule response = this.mapper.map(scheduleSaved, Schedule.class);
        return response;
    }

    @Override
    public boolean existsById(long id) {
        return this.serviceBD.existsById(id);
    }

    @Override
    public Schedule findById(long id) {
        ScheduleEntity data = this.serviceBD.findById(id).get();
        return this.mapper.map(data, Schedule.class);
    }

}
