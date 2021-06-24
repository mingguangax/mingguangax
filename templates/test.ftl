<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
${name!'SB'}
    <#list list as l>
        <td>${l}</td>
    </#list>
<#if boo>
    ${name!'SB'}
</#if>
</body>
</html>