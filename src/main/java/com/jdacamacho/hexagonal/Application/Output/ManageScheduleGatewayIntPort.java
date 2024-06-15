package com.jdacamacho.hexagonal.Application.Output;

import com.jdacamacho.hexagonal.Domain.Objects.Schedule;

public interface ManageScheduleGatewayIntPort {
    Schedule save(Schedule schedule);
    boolean existsById(long id);
    Schedule findById(long id);
}
