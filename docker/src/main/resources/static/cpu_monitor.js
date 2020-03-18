function updateCPUMonitor(){
    $.ajax({
        url: "/physical/cpu/",
        type: "GET",
        dataType: "JSON",
        success:function (data) {
            document.getElementById('cpu_os_used').innerText = data[0];
            document.getElementById('cpu_os_used2').style="width: " + data[0];

            document.getElementById('cpu_user_used').innerText = data[1];
            document.getElementById('cpu_user_used2').style="width: " + data[1];

            document.getElementById('cpu_io_wait').innerText = data[2];
            document.getElementById('cpu_io_wait2').style="width: " + data[2];

            document.getElementById('cpu_kx').innerText = data[3];
            document.getElementById('cpu_kx2').style="width: " + data[3];

            document.getElementById('cpu_load').innerText = data[4];
            document.getElementById('cpu_load2').style="width: " + data[4];
        },
        error:function () {
            console.log("error");
        }
    });
}
setInterval(function () {
    updateCPUMonitor();
}, 1500);