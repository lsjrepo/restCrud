<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <%--
        1.
        使用form标签的原因:
        可以更快速的开发出表单页面,
        而且可以更方便的进行表单值的回显
        2.注意:
        可以通过modelAttribute属性指定绑定的模型属性
        若没有指定该属性,则默认从request域中读取command的表单bean
        如果该属性值也不存在,则会发生错误
        SpringMvc默认表单一定要进行回显,即便是第一次操作,
        都会去请求域中找寻对应的bean来匹配
        当前的表单值
    --%>
    <%--如果修改的时候会发生错误,实际上开发的时候都推荐使用绝对路径--%>
    <form:form action="${pageContext.request.contextPath}/emp" method="POST"
               modelAttribute="employee">
        <%--path属性对应html表单标签的name属性--%>
        <c:if test="${employee.id==null}">
            LastName:<form:input path="lastName"/><%--input表示文本框--%>
        </c:if>
        <c:if test="${employee.id!=null}">
            <form:hidden path="id"/>
            <input type="hidden" name="_method" value="PUT"><%--表示这个方法是一个PUT请求--%>
            <%--对于method不能使用form:hidden标签,因为modelAttrbiute对应的bean中没有_method这个属性--%>
            <%--<form:hidden path="_method" value="PUT"/>--%>
        </c:if>
        <br>
        Email:<form:input path="email"/>
        <br>
        <%----%>
        <%
            Map<String,String> genders=new HashMap<>();
            genders.put("1","Male");
            genders.put("0","Female");
            request.setAttribute("genders",genders);//需要放在域对象中
        %>
        <%--radio Button--%>
        Gender:<form:radiobuttons path="gender" items="${genders }"/>
        <br>
        <%--下拉框--%>
        Department:<form:select path="department.id"
                                items="${departments}"
                                itemLabel="departmentName"
                                itemValue="id">
        </form:select>
        <br>
        <input type="submit" value="Submit">
    </form:form>
</body>
</html>
