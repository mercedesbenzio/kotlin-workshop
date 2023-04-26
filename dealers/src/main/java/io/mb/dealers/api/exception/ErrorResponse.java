package io.mb.dealers.api.exception;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;

@Schema(requiredProperties = {"timestamp", "status", "error", "path"})
public record ErrorResponse(
        Instant timestamp,
        int status,
        String error,
        String message,
        String path) {
}
