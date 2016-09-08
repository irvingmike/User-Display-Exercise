<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="head.jsp"%>

<html><body>

<div class="container-fluid">
    <h2>Search Results: </h2>
    <c:forEach items="${users}" var="user">
        <div class="result">
            <span class="bold">User ID:</span> ${user.userid}<br />
            <span class="bold">Name:</span> ${user.lastName}, ${user.firstName}<br />
            <span class="bold">Age:</span> ${user.age}
        </div>
    </c:forEach>
</div>

</body>
</html>
