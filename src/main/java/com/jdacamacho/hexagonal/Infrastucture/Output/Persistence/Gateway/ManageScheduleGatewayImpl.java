package com.jdacamacho.hexagonal.Infrastucture.Output.Persistence.Gateway;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.jdacamacho.hexagonal.Application.Output.ManageScheduleGatewayIntPort;
import com.jdacamacho.hexagonal.Domain.Objects.Schedule;
import com.jdacamacho.hexagonal.Infrastucture.Output.Persistence.Entities.ScheduleEntity;
import com.jdacamacho.hexagonal.Infrastucture.Output.Persistence.Repositories.ScheduleRepository;

@Service
public class ManageScheduleGatewayImpl implements ManageScheduleGatewayIntPort{
    private final ScheduleRepository serviceBD;
    private final ModelMapper mapper;

    public ManageScheduleGatewayImpl(ScheduleRepository serviceBD,
                                    ModelMapper mapper){
        this.serviceBD = serviceBD;
        this.mapper = mapper;
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
