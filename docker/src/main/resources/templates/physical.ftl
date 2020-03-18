<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.0/css/bootstrap.css" rel="stylesheet">
    <script type="text/javascript" src="http://cdn.staticfile.org/jquery/2.0.0/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/twitter-bootstrap/3.0.0/js/bootstrap.min.js"></script>
    <script src="/echarts.min.js"></script>
</head>
<body>
<div class="container">
    <#--边栏sidebar-->
    <#include "./common/nav.ftl">
    <script type="text/javascript">
        document.getElementById("physical-machine").setAttribute("class", "active");
    </script>
    <div class="row clearfix">
        <div class="col-md-6 column">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        操作系统  ${osName}&emsp;&emsp;&emsp;|&emsp;&emsp;&emsp;CPU核心数 ${osCPUNumber}
                    </h3>
                </div>
                <div class="panel-body">
                    <div id="myChart" style="width: 100%;height:320px;"></div>
                </div>
            </div>

            <script type="text/javascript">
                let myChart = echarts.init(document.getElementById("myChart"));
                function randomData() {
                    now = new Date(+now + oneDay);
                    value = value + Math.random() * 21 - 10;
                    return {
                        name: now.toString(),
                        value: [
                            [now.getFullYear(), now.getMonth() + 1, now.getDate()].join('/'),
                            Math.round(value)%100
                        ]
                    };
                }

                var data = [];
                var data2 = [];
                var now = +new Date(1997, 9, 3);
                var oneDay = 24 * 3600 * 1000;
                var value = Math.random() * 1000;
                for (var i = 0; i < 100; i++) {
                    data.push(randomData());
                }
                for (i = 0; i < 100; i++) {
                    data2.push(randomData());
                }

                option = {
                    // title: {
                    //     text: '动态数据 + 时间坐标轴'
                    // },
                    tooltip: {
                        trigger: 'axis',
                        formatter: function (params) {
                            params = params[0];
                            var date = new Date(params.name);
                            return date.getDate() + '/' + (date.getMonth() + 1) + '/' + date.getFullYear() + ' : ' + params.value[1];
                        },
                        axisPointer: {
                            animation: false
                        }
                    },
                    legend: {
                        data: ['邮件营销', '联盟广告']
                    },
                    xAxis: {
                        type: 'time',
                        splitLine: {
                            show: false
                        }
                    },
                    yAxis: {
                        type: 'value',
                        boundaryGap: [0, '1%'],
                        splitLine: {
                            show: true
                        }
                    },
                    series: [{
                        name: '模拟数据',
                        type: 'line',
                        showSymbol: false,
                        hoverAnimation: false,
                        data: data
                    }, {
                        name: '模拟数据2',
                        type: 'line',
                        showSymbol: false,
                        hoverAnimation: false,
                        data: data2
                    }]
                };

                setInterval(function () {
                    for (var i = 0; i < 5; i++) {
                        data.shift();
                        data.push(randomData());
                    }
                    for (i = 0; i < 5; i++) {
                        data2.shift();
                        data2.push(randomData());
                    }
                    myChart.setOption({
                        series: [{
                            data: data,
                            type: 'line',
                        },
                        {
                            data: data2,
                            type: 'line',
                        }]
                    });
                }, 1500);
                myChart.setOption(option);
            </script>
        </div>
        <div class="col-md-6 column">
            <table class="table">
                <tbody>
                <tr>
                    <td style="width: 30%">
                        CPU系统使用率 <span id="cpu_os_used"></span>
                    </td>
                    <td>
                        <div class="progress progress-striped active">
                            <div id="cpu_os_used2" class="progress-bar progress-bar-success" role="progressbar"
                                 aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"
                                 style="width: 40%">
                            </div>
                        </div>
                    </td>
                </tr>

                <tr>
                    <td style="width: 30%">
                        CPU用户使用率 <span id="cpu_user_used"></span>
                    </td>
                    <td>
                        <div class="progress progress-striped active">
                            <div id="cpu_user_used2" class="progress-bar progress-bar-success" role="progressbar"
                                 aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"
                                 style="width: 40%">
                            </div>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td style="width: 30%">
                        CPU当前等待率 <span id="cpu_io_wait"></span>
                    </td>
                    <td>
                        <div class="progress progress-striped active">
                            <div id="cpu_io_wait2" class="progress-bar progress-bar-success" role="progressbar"
                                 aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"
                                 style="width: 40%">
                            </div>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td style="width: 35%">
                        CPU当前空闲率 <span id="cpu_kx"></span>
                    </td>
                    <td>
                        <div class="progress progress-striped active">
                            <div id="cpu_kx2" class="progress-bar progress-bar-success" role="progressbar"
                                 aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"
                                 style="width: 40%">
                            </div>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td style="width: 30%">
                        CPU平均负载 <span id="cpu_load"></span>
                    </td>
                    <td>
                        <div class="progress progress-striped active">
                            <div id="cpu_load2" class="progress-bar progress-bar-success" role="progressbar"
                                 aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"
                                 style="width: 40%">
                            </div>
                        </div>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
    <hr>
    <div class="row clearfix">
        <div class="col-md-6 column">
            <div id="physical_memory" style="width: 400px;height:270px;"></div>
        </div>
        <div class="col-md-6 column">
            <div id="disk_memory" style="width: 400px;height:270px;"></div>
        </div>
    </div>
</div>
<script type="text/javascript" src="/memory_monitor.js"></script>
<script type="text/javascript" src="/cpu_monitor.js"></script>
</body>
</html>