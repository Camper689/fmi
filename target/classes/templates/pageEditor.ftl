<#import "parts/common.ftl" as common><@common.html 'Редагувати сторінку' true>
    <ul id="breadcrumbs" class="breadcrumbs">
        <li><a href="/"><i class="fa fa-home"></i></a></li>
        <li class="current">Редагувати "${page.name?html}"</li>
    </ul>

    <#include "parts/messages.ftl" />
    <div class="wrapper">
        <div class="block block-margin">
            <@common.postForm "/page/setVisible">
                <input type="hidden" name="id" value="${page.id?c}" />
                <h3><#if page.isVisible()>Ця сторінка зараз видна всім<#else>Ця сторінка зараз прихована</#if></h3>
                <button class="btn btn-default">${page.isVisible()?string('Приховати', 'Зробити видимою')}</button>
            </@common.postForm>
        </div>
    </div>

    <div class="margin">
        <div class="block block-margin">
            <button onclick="modal('#addPageModal', null)" class="btn btn-default">Додати дочірню сторінку</button>

            <table class="table table-stripped">
                <thead><tr><th>Дочірні сторінки:</th></tr></thead>
                <tbody>
                    <#list page.subPages as subPage>
                    <tr>
                        <td>${subPage.name?if_exists}</td>
                        <td><a href="/page/${subPage.id?c}">Редагувати</a></td>
                        <td><a href="/pages/${subPage.id?c}">Переглянути</a></td>
                    </tr>
                    </#list>
                </tbody>
            </table>
        </div>
    </div>

    <div class="wrapper">
        <div class="block block-margin">
            <@common.postForm "/page/edit">
                <input name="id" type="hidden" value="${page.id?c}" />
                <input class="form-control" name="name" placeholder="Введіть назву сторінки" value="<#if name??>${name?html}<#else>${page.name?if_exists}</#if>" />
                <textarea name="body" style="display: none"></textarea>
                <div data-quill-editor="regular"><#if body??>${body}<#elseif page.body??>${page.body}</#if></div>
                <button class="btn btn-default">Зберегти зміни</button>
            </@common.postForm>
        </div>
    </div>

    <@common.createModal 'addPageModal' 'Додати дочірню сторінку' 'Додати'>
        <@common.postForm "/page/addSubPage">
            <input name="id" type="hidden" value="${page.id?c}" />
            <input class="form-control" name="name" placeholder="Введіть назву нової сторінки" />
        </@common.postForm>
    </@common.createModal>
</@common.html>