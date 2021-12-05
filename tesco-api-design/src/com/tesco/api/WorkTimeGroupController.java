package com.tesco.api;

import com.tesco.model.WorkTimeGroupWrapper;
import com.tesco.service.WorkTimeGroupService;

public class WorkTimeGroupController {

    WorkTimeGroupService workTimeGroupService = new WorkTimeGroupService();

    public WorkTimeGroupWrapper mergeWorkTimeGroups(WorkTimeGroupWrapper workTimeGroupWrapper)
    {
        if (workTimeGroupWrapper == null)
        {
            throw new RuntimeException("Invalid Input!!");
        }

        return workTimeGroupService.mergeWorkTimeGroups(workTimeGroupWrapper);
    }

}
