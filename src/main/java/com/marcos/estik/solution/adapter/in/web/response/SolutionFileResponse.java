package com.marcos.estik.solution.adapter.in.web.response;

import java.util.UUID;

public record SolutionFileResponse(
    UUID id,
    String fileUrl,
    String fileType,
    Long fileSize,
    String fileName
) {

}
