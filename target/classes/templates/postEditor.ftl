<#import "parts/common.ftl" as common><@common.html 'Редагувати пост' true>
    <ul id="breadcrumbs" class="breadcrumbs">
        <li><a href="/"><i class="fa fa-home"></i></a></li>
        <li class="current">Новини</li>
    </ul>

    <#include "parts/messages.ftl" />

    <a href="/posts/${post.id?c}">Показати пост</a>

    <div class="block block-margin">
        <@common.postForm "/posts/setVisible">
            <input type="hidden" name="id" value="${post.id?c}" />
            <#if post.isVisible()>Цей пост зараз видний всім.<#else>Цей пост зараз прихований.</#if>
            <button class="btn btn-default">${post.isVisible()?string('Приховати', 'Зробити видимим')}</button>
        </@common.postForm>

        <div class="my-5">
        <@common.postForm "/posts/update">
            <input name="id" type="hidden" value="${post.id?c}" />
            <select name="category" class="form-control">
                <option value="" <#if !post.category??>selected</#if>>Оберіть категорію</option>
                <#list allCategories as category><option <#if post.category?? && post.category.id == category.id>selected</#if> value="${category.id?c}">${category.name?html}</option></#list>
            </select>

            <select name="album" class="form-control">
                <option value="" <#if !post.album??>selected</#if>>Оберіть альбом</option>
                <#list allAlbums as album><option <#if post.album?? && post.album.id == album.id>selected</#if> value="${album.id?c}">${album.name?html}</option></#list>
            </select>

            <input class="form-control" name="title" placeholder="Введіть назву новини" value="<#if title??>${title?html}<#else>${post.title?if_exists}</#if>" />
            <textarea name="body" style="display: none"></textarea>
            <div data-quill-editor="regular"><#if body??>${body}<#elseif post.body??>${post.body}</#if></div>
            <button class="btn btn-default">Зберегти зміни</button>
        </@common.postForm>
        </div>

        <div class="my-5">
        <@common.postForm "/posts/setImage" "" 'multipart/form-data'>
            <input name="id" type="hidden" value="${post.id?c}" />
            <#if post.image??>
                Головне зображення:
                <img class="img-fluid" src="/image/${post.image.getFullName()}">
            <#else>
                Поки що нема зображення
            </#if>
            <input placeholder="Завантажити зображення.." type="file" name="image" class="form-control" />
            <button class="btn btn-default">Зберегти зображення</button>
        </@common.postForm>
        </div>
    </div>
</@common.html>