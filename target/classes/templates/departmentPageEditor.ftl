<#import "parts/common.ftl" as common><@common.html 'Редагувати сторінку' true>
    <ul id="breadcrumbs" class="breadcrumbs">
        <li><a href="/"><i class="fa fa-home"></i></a></li>
        <li class="current">Редагувати сторінку</li>
    </ul>

    <div class="block sidebar-margin">
        <#include "parts/messages.ftl" />

        <@common.postForm "/departments/updatePage">
            <h3 class="ml-3">
                <#if page??>
                    Редагувати пост "${page.title?html}"
                <#elseif section??>
                    Додати пост в розділ "${section.name?html}"
                <#else>
                    Додати пост на сторінку кафедри "${department.name?html}"
                </#if>
            </h3>

            <#if page??><input type="hidden" class="form-control" name="id" value="${page.id?c}" /></#if>
            <#if department??><input type="hidden" class="form-control" name="departmentId" value="${department.id?c}" /></#if>
            <#if section??><input type="hidden" class="form-control" name="sectionId" value="${section.id?c}" /></#if>

            <#if department?? || (page?? && page.department??)>
                <label>
                    <input type="checkbox" name="fromChief"<#if fromChief?? && fromChief> checked="checked"</#if>/>
                    Пост від імені зав. кафедри
                </label>
            </#if>

            <input placeholder="Введіть назву сторінки сюди" class="form-control" name="title" value="<#if title??>${title?html}<#elseif page??>${page.title?html}</#if>" />

            <textarea name="body" style="display: none"></textarea>
            <div data-quill-editor="departmentPage"><#if body??>${body}<#elseif page??>${page.body?if_exists}</#if></div>

            <button class="btn btn-default">Зберегти</button>
        </@common.postForm>
    </div>
</@common.html>