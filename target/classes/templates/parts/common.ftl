<#macro html title='' quill=false>
<!DOCTYPE html>
<html>
<head>
    <title><#if title != ''>${title} | </#if>Факультет математики та інформатики</title>
    <meta content="Сайт факультету математики та інформатики РДГУ" name="description"/>

    <link rel="stylesheet" href="/css/bootstrap.css">

    <script src="/js/jquery.js"></script>
    <script src="/js/popper.js"></script>
    <script src="/js/bootstrap.js"></script>

    <#if quill>
    <link rel="stylesheet" href="//cdn.quilljs.com/1.3.6/quill.snow.css">
    <script src="/js/quill.js"></script>
    </#if>

    <link rel="stylesheet" href="/css/magnific-popup.css" />
    <script src="/js/jquery.magnific-popup.min.js"></script>

    <script src="/js/particles-js.js"></script>
</head>
<body>
    <#include "header.ftl">

    <div class="wrapper">
        <#nested />
    </div>

    <#include "footer.ftl" />
</body>
</html>
</#macro>

<#macro createModal id="" title="" submitButton="">
<div class="modal fade" id="${id}" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog" role="document">
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

<#macro postForm action id = '' enctype=''>
    <form method="post" action="${action}"<#if id != ''> id="${id}"</#if><#if enctype != ''> enctype="${enctype}"</#if>>
        <#nested>
    </form>
</#macro>