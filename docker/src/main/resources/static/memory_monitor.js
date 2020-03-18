//初始化物理内存监控
physical_memory_monitor(0, 0);
//初始化磁盘监控
disk_monitor(0,0);

//监控物理内存
function physical_memory_monitor(used, free) {
    // 绘制图表
    echarts.init(document.getElementById('physical_memory')).setOption({
        title: {
            text: '物理内存'
        },
        tooltip: {
            trigger: 'item',
            formatter: '{c}GB {d}%'
        },
        series: {
            type: 'pie',
            data: [
                {name: 'Used', value: used},
                {name: 'Free', value: free}
            ]
        }
    });
}

//监控磁盘
function disk_monitor(used, free) {
    // 绘制图表
    echarts.init(document.getElementById('disk_memory')).setOption({
        title: {
            text: '物理磁盘'
        },
        tooltip: {
            trigger: 'item',
            formatter: '{c}GB {d}%'
        },
        series: {
            type: 'pie',
            data: [
                {name: 'Used', value: used},
                {name: 'Free', value: free}
            ]
        }
    });
}

function getDiskInfo(){
    $.ajax({
        url: "/physical/disk/",
        type: "GET",
        dataType: "JSON",
        success:function (data) {
            disk_monitor(data.used, data.free);
        },
        error:function () {
            console.log("error");
        }
    });
}

function getMemoryInfo(){
    $.ajax({
        url: "/physical/memory/",
        type: "GET",
        dataType: "JSON",
        success:function (data) {
            physical_memory_monitor(data.used, data.free);
        },
        error:function () {
            console.log("error");
        }
    });
}


//内存监控单元1s自动更新
setInterval(function () {
    getMemoryInfo();
    getDiskInfo();
}, 2000);