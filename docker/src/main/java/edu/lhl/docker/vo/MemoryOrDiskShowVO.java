package edu.lhl.docker.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemoryOrDiskShowVO {
    public Double used;
    public Double free;
}