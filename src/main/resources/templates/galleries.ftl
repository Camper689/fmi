<#import "parts/common.ftl" as common><@common.html 'Галерея'>
    <ul id="breadcrumbs" class="breadcrumbs">
        <li><a href="/"><i class="fa fa-home"></i></a></li>
        <li class="current">Галерея</li>
    </ul>

    <div class="block block-margin">
        <#include "parts/messages.ftl" />

        <div class="album-list">
            <ul>
                <li>
                    <a href="javascript:void(0)" onclick="modal('#addAlbumModal', null)">
                        <div class="album-cover">
                            <img src="/img/add-album.png" alt="Додати альбом" />
                            <div class="album-cover-title">Додати альбом</div>
                        </div>
                    </a>
                </li>
                <#list allAlbums as album>
                <li>
                    <a href="/gallery/${album.id?c}">
                        <div class="album-cover">
                            <#if !album.isEmpty()>
                                <img src="/image/${album.lastImage.getFullName()}" alt="${album.lastImage.getAlt()}" />
                            </#if>
                            <div class="album-cover-title">${album.name?html}</div>
                        </div>
                    </a>
                    <a href="javascript:void(0)" onclick="modal('#editAlbumModal', {name: '', id: '${album.id?c}'})">
                        Змінити назву
                    </a>
                    <a href="javascript:void(0)" class="text-danger" onclick="modal('#deleteAlbumModal', {name: '${album.name?html}', id: '${album.id?c}'})">
                        Видалити альбом
                    </a>
                </li>
                </#list>
            </ul>
        </div>
        <div class="text-center"></div>
    </div>

    <@common.createModal 'addAlbumModal' 'Додати альбом' 'Додати'>
        <@common.postForm '/album/add'>
            <input class="form-control" name="name" placeholder="Введіть назву альбома">
        </@common.postForm>
    </@common.createModal>

    <@common.createModal 'editAlbumModal' 'Редагувати альбом' 'Зберегти'>
        <@common.postForm '/album/edit'>
            <input name="id" type="hidden">
            <input class="form-control" name="name" placeholder="Введіть назву альбома">
        </@common.postForm>
    </@common.createModal>

    <@common.createModal 'deleteAlbumModal' 'Видалити альбом' 'Видалити'>
        <@common.postForm '/album/delete'>
            <input name="id" type="hidden">
            Ви точно хочете видалити альбом &quot;<span name="name"></span>&quot;?
        </@common.postForm>
    </@common.createModal>
</@common.html>