package com.udaan.model;

import java.util.*;
import java.util.stream.Collectors;

public class BerthConfiguration {
    public Map<BerthType, Map<BerthLocation, List<Berth>>> berthConfig;
    public List<Berth> berthList;

    public BerthConfiguration() {
        berthList = Arrays.stream(BerthType.values()).sequential()
                .map(bt -> Arrays.stream(BerthLocation.values()).sequential()
                        .map(bl -> new Berth(bt,bl))
                        .collect(Collectors.toList()))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        berthConfig = berthList.stream().collect(Collectors.groupingBy(b -> b.berthType,
                        Collectors.groupingBy(b -> b.berthLocation)));
    }
}
