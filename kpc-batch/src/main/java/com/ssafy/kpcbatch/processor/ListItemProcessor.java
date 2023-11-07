package com.ssafy.kpcbatch.processor;

import com.ssafy.kpcbatch.dto.RegionDto;
import com.ssafy.kpcbatch.entity.Region;
import org.springframework.batch.item.ItemProcessor;

import java.util.ArrayList;
import java.util.List;

public class ListItemProcessor implements ItemProcessor<List<RegionDto>, List<Region>> {
    @Override
    public List<Region> process(List<RegionDto> item) throws Exception {
        List<Region> ls = new ArrayList<>();
        for (RegionDto regionDto: item) {
            Region region = Region
                    .builder()
                    .cortarNo(regionDto.getCortarNo())
                    .centerLat(regionDto.getCenterLat())
                    .centerLon(regionDto.getCenterLon())
                    .cortarName(regionDto.getCortarName())
                    .cortarType(regionDto.getCortarType())
                    .build();
            ls.add(region);
        }
        return ls;
    }
}
