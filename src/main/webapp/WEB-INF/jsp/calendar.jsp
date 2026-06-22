<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>カレンダー</title>
    <link rel="stylesheet" href="<c:url value='/css/calendar.css' />">
</head>
<body>
<main class="page">
    <h1>${year}年 ${month}月</h1>

    <form class="calendar-form" action="/" method="get">
        <label>
            年
            <input type="number" name="year" value="${year}" min="1900" max="2100">
        </label>
        <label>
            月
            <input type="number" name="month" value="${month}" min="1" max="12">
        </label>
        <button type="submit">表示</button>
    </form>

    <table class="calendar">
        <thead>
        <tr>
            <th class="weekend">日</th>
            <th>月</th>
            <th>火</th>
            <th>水</th>
            <th>木</th>
            <th>金</th>
            <th class="weekend">土</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="week" items="${calendarWeeks}">
            <tr>
                <c:forEach var="date" items="${week}">
                    <td class="${date.today ? 'today' : ''} ${date.weekend ? 'weekend' : ''}">
                        <c:if test="${date.day != 0}">
                            ${date.day}
                        </c:if>
                    </td>
                </c:forEach>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</main>
</body>
</html>
