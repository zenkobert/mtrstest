package com.zezenk.mtrstest.dto;

import com.zezenk.mtrstest.model.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.Map;

@Setter
@Getter
@NoArgsConstructor
public class GenderMapDTO {
    private Map<String, Gender> maps = new LinkedHashMap<>();
}
