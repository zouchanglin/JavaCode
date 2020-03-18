package com.soyanga.httpd.common;


/**
 * 服务器支持的文件类型
 */
public enum SupportedMimeType {

    HTML("html", "text/html"),
    HTM("htm", "text/htm"),
    CSS("css", "text/css"),
    JS("js", "application/javascript"),
    GIF("gif", "image/gif"),
    JPEG("jpeg", "image/jpeg"),
    JPG("jpg", "image/jpeg"),
    PNG("png", "image/png"),
    MP3("mp3", "audio/mpeg"),
    MP4("mp4", "video/mp4");
    /**
     * 扩展名
     */
    private String extend;
    /**
     * 内容类型
     */
    private String mimeType;

    SupportedMimeType(String extend, String mimeType) {
        this.extend = extend;
        this.mimeType = mimeType;
    }

    public static SupportedMimeType lookup(String extend) {
        for (SupportedMimeType supportedMimeType : SupportedMimeType.values()) {
            if (supportedMimeType.getExtend().equalsIgnoreCase(extend)) {
                return supportedMimeType;
            }
        }
        return null;
    }

    public String getExtend() {
        return extend;
    }

    public void setExtend(String extend) {
        this.extend = extend;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }
}

