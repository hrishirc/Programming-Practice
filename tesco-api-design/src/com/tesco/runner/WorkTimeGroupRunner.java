package com.tesco.runner;

import com.tesco.api.WorkTimeGroupController;
import com.tesco.model.*;

public class WorkTimeGroupRunner {

    public static void main(String[] args) {
        WorkTimeGroupWrapper wrapper = new WorkTimeGroupWrapper();
        wrapper.getGroups().add(new WorkTimeGroup(10,12));
        wrapper.getGroups().add(new WorkTimeGroup(8,11));
        wrapper.getGroups().add(new WorkTimeGroup(14,18));

        WorkTimeGroupWrapper wrapper1 = new WorkTimeGroupController().mergeWorkTimeGroups(wrapper);
        System.out.println(wrapper1.getGroups());
    }
}
