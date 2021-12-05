package com.tesco.service;

import com.tesco.model.*;

import java.util.ArrayList;
import java.util.List;

public class WorkTimeGroupService {

    public WorkTimeGroupWrapper mergeWorkTimeGroups(WorkTimeGroupWrapper workTimeGroupWrapper) {

        int[] startHrs = new int[24];
        int[] endHrs = new int[24];

        List<WorkTimeGroup> groups = workTimeGroupWrapper.getGroups();

        for (WorkTimeGroup group : groups)
        {
            startHrs[(int) group.startHour] += 1;
            endHrs[(int) group.endHour - 1] += 1;
        }

        List<WorkTimeGroup> mergedGroups = new ArrayList<>();

        for (int i = 0; i < startHrs.length; i++)
        {
            while (startHrs[i] > 0)
            {
                // need to convert to 2-pointers instead of loop inside loop
                for (int j = 0; j < endHrs.length; j++)
                {
                    while (endHrs[j] > 0)
                    {
                        WorkTimeGroup workTimeGroup = new WorkTimeGroup(i, j + 1 == 24 ? 0 : j + 1);

                        startHrs[i] -= 1;
                        endHrs[j] -= 1;

                        mergedGroups.add(workTimeGroup);
                    }
                }
            }
        }

        WorkTimeGroupWrapper retWrapper = new WorkTimeGroupWrapper();
        retWrapper.getGroups().addAll(mergedGroups);

        return retWrapper;
    }

}
