<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.0/css/bootstrap.css" rel="stylesheet">
    <script type="text/javascript" src="http://cdn.staticfile.org/jquery/2.0.0/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/twitter-bootstrap/3.0.0/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <#--边栏sidebar-->
    <#include "./common/nav.ftl">
    <script type="text/javascript">
        document.getElementById("virtual-machine").setAttribute("class", "active")
    </script>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>

                    </th>
                    <th>
                        容器ID
                    </th>
                    <th>
                        容器名称
                    </th>
                    <th>
                        容器状态
                    </th>
                    <th>
                        映射端口
                    </th>
                    <th>
                        镜像ID
                    </th>
                    <th>
                        镜像名称
                    </th>
                    <th>
                        创建时间
                    </th>
                    <th>

                    </th>
                </tr>
                </thead>
                <tbody id="stop_table">
                <#list stop as container>
                    <tr id="tr${container.containerId}">
                        <td>
                            <input type="checkbox"/>
                        </td>
                        <td>
                            ${container.containerId?substring(0, 12)}
                        </td>
                        <td>
                            ${container.containerName}
                        </td>
                        <td>
                            ${container.status}
                        </td>
                        <td>
                            <#list container.ports as port>
                                ${port}<br>
                            </#list>
                        </td>
                        <td>
                            ${container.imageId?substring(0, 12)}
                        </td>
                        <td>
                            ${container.imageName}
                        </td>
                        <td>
                            ${container.created}
                        </td>
                        <td>
                            <#--                                        <a href="/container/start?containerId=${container.containerId}">启动</a>-->
                            <a id="${container.containerId}" class="btn btn-default btn-link" onclick="startContainer(this.id)">启动</a>
                            <a href="#">删除</a>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
</div>
<script type="text/javascript">
    function startContainer(id){
        let htmlElement = document.getElementById(id);
        htmlElement.setAttribute("class", "btn disabled");
        htmlElement.innerText="启动中...";
        let task;
        $.ajax({
            url: "/container/start/",
            type: "GET",
            data: {"containerId": id},
            success:function (data) {
                task = setInterval(xxx, 1000);
            },
            error:function () {
                console.log("error1");
            }
        });
        function xxx() {
            $.ajax({
                url: "/container/status/",
                type: "GET",
                data: {"containerId": id},
                dataType: "JSON",
                success:function (data) {
                    if(data){
                        console.log("Success");
                        document.getElementById("stop_table").removeChild(document.getElementById("tr"+id));
                        clearInterval(task);
                    }
                },
                error:function () {
                    console.log("error2");
                }
            });
        }
    }
</script>
</body>
</html>