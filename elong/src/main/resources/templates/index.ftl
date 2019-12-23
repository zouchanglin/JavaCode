<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>根据手机号查询考好</title>
</head>
<body>
<table style="border: black">
    <thead>
        <tr>
            <th>编号</th>
            <th>卡号</th>
            <th>用户ID</th>
            <th>用户名</th>
            <th>Email</th>
        </tr>
    </thead>
    <tbody>
    <#list memberData as member>
        <tr>
            <td>${member.id}</td>
            <td>${member.cardNo}</td>
            <td>${member.userId}</td>
            <td>${member.nickName}</td>
            <td>${member.email}</td>
        </tr>
    </#list>

    </tbody>
</table>
</body>
</html>