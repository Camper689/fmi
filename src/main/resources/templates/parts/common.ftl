<#macro html title=''>
<!DOCTYPE html>
<html>
<head>
    <title><#if title != ''>${title} | </#if>Факультет математики та інформатики</title>
    <meta content="Сайт факультету математики та інформатики РДГУ" name="description"/>
    <link rel="stylesheet" media="all" href="/css/bootstrap.css" data-turbolinks-track="true"/>
    <script src="/js/init.js" data-turbolinks-track="true"></script>
    <script src="/js/ckeditor.js"></script>
</head>
<body>
<#include "header.ftl">
<div class="wrapper">
    <ul id="breadcrumbs" class="breadcrumbs">
        <li><a href="/"><i class="fa fa-home"></i></a></li>
        <li class="current">Галерея</li>
    </ul>

    <#nested />
</div>
<#include "footer.ftl" />
</body>
</html>
</#macro>

<#macro createModal id="" title="" submitButton="">
<div class="modal fade" id="${id}" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">${title}</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Закрити">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <#nested>
            </div>
            <div class="modal-footer">
                <#if submitButton != ""><button type="submit" class="btn btn-primary" onclick="$(this).parent().parent().find('form').submit();">${submitButton}</button></#if>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Назад</button>
            </div>
        </div>
    </div>
</div>
</#macro>

<#macro postForm action id = ''>
    <form method="post" action="${action}" <#if id != ''>id="${id}"</#if>>
        <#nested>
    </form>
</#macro>