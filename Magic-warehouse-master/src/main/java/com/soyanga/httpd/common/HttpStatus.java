package com.soyanga.httpd.common;

/**
 * @program: webServer-httpd
 * @Description: 服务器的响应状态描述符
 * @Author: SOYANGA
 * @Create: 2019-01-24 22:32
 * @Version 1.0
 */
public enum HttpStatus {
    SWITCH_PROTOCOL(101, "Switching Protocols"),
    OK(200, "OK"),
    CREATED(201, "Created"),
    ACCEPTED(202, "Accepted"),
    NO_CONTENT(204, "NO Content"),
    PARTIAL_CONTENT(206, "Partial Content"),
    MULTI_STATUS(207, "Multi-Status"),

    REDIRECT(301, "Moved Permanently"),

    FOUND(302, "Found"),
    REDIRECT_SEE_OTHER(303, "See Other"),
    NOT_MODIFIED(304, "Not Modifity"),
    TEMPORARY_REDIRECT(307, "Temporary Redirect"),

    BAD_REQUEST(400, "Bad Request"),
    UNAUTHORIZED(401, "Unauthorized"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "NOT FOUND"),
    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
    NoT_ACCEPTABLE(406, "Not Acceptable"),
    REQUEST_TIMEOUT(408, "Request Timeout"),
    CONFLICT(409, "Conflict"),
    GONE(410, "Gone"),
    LENGTH_REQUIRED(411, "Length Required"),
    PRECONDITION_FAILED(412, "Precondiction Failed"),
    PAYLOAD_TOO_LARGE(413, "Request Entity Too Large"),
    Unsupported_Media_Type(415, "Unsupported Media Type"),
    REQUESTED_RANGE_NOT_SATISFIABLE(416, "Requested range not satisfiable"),
    EXPECTATION_FAILED(417, "Expectation Failed"),
    TOO_MANY_REQUESTS(429, "Too Many Requests"),

    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    NOT_IMPLEMENTED(501, "Not Implemented"),
    SERVICE_UNAVAILABLE(503, "Service Unavailable"),
    HTTP_VERSION_NOT_SUPPORTED(505, "HTTP Version not supported");


    private final int requestStatus;

    private final String description;

    HttpStatus(int requestStatus, String description) {
        this.requestStatus = requestStatus;
        this.description = description;
    }

    public static HttpStatus lookup(int requestStatus) {
        for (HttpStatus status : HttpStatus.values()) {
            if (status.getRequestStatus() == requestStatus) {
                return status;
            }
        }
        return null;
    }

    public int getRequestStatus() {
        return requestStatus;
    }

    public String getDescription() {
        return "" + this.requestStatus + " " + this.description;
    }

}
