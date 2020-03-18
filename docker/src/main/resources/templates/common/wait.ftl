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
    <div class="row clearfix">
        <div class="col-md-12 column">
            <br><br><br><br><br>
            <div class="progress progress-striped active">
                <div id="my-wait-progress" class="progress-bar progress-bar-success" role="progressbar"
                     aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"
                     style="width: 1%">
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    let wait = document.getElementById("my-wait-progress");
    let num = 2;
    setInterval(function () {
        wait.setAttribute("style", "width: " + num + "%");
        if(num > 50){
            num++
        }
        num++
    }, 100);
</script>
</body>
</html>