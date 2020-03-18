package edu.lhl.docker.core.model;

import lombok.Data;

@Data
public class DockerContainer {
    /** 容器Id */
    private String containerId;

    /** 容器名称 */
    private String containerName;

    /** 命令 */
    private String command;

    /** 创建时间 */
    private String created;

    /** 镜像Id */
    private String imageId;

    /** 镜像名称 */
    private String imageName;

    /** 端口占用 */
    private String[] ports;

    /** 状态 */
    private String status;
}