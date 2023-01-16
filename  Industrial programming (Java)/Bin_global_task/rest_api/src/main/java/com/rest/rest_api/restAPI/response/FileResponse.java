package com.rest.rest_api.restAPI.response;

import lombok.Getter;
import lombok.Builder;

@Builder
@Getter
public class FileResponse {
    private String fileName;
    private String downloadUri;
    private long fileSize;
}
