package com.example.be.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PagingFilterCameraResponse {
    List<CameraDisplay> cameras = new ArrayList<>();;
    Integer totalPages;
    Long totalRecords;
}
