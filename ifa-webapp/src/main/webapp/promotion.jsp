<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Promowane fundusze</title>
</head>
<body>
<form action="/4analysis/promoted" method="get">
    <div>
        Id funduszu:
        <input type="text" name="investFundId" placeholder="np. AGI001" required autofocus/>
    </div>
    <div>
        Priorytet:
        <input type="number" name="priority" min="0" max="100" required>
    </div>
    <div>
        <input type="submit" value="Dodaj do bazy promowanych funduszy"/>
    </div>
</form>
</body>
</html>
