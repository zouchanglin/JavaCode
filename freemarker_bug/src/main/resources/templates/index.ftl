<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<h1>${person.name}</h1>
<label>
    <input value="${person.age?c}" type="number">
    <input value="${person.name?c}" type="text">
</label>
</body>
</html>