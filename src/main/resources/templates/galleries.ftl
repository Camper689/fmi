<#import "parts/common.ftl" as common><@common.html 'Галерея'>
    <div class="block block-margin">
        <#if successMessage??>
        <#elseif !errorMessage??>
        hey
        </#if>

        <div class="album-list">
            <ul>
                <li>
                    <a href="javascript:void(0)" onclick="$('#addAlbumModal').modal()">
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
                                <img src="${album.lastImage}" alt="${album.getAlt(lastImage)}" />
                            </#if>
                            <div class="album-cover-title">${album.name?html}</div>
                        </div>
                    </a>
                    <a href="javascript:void(0)">
                        <i class="fas fa-pen"></i>
                        Змінити назву
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
            <input class="form-control" name="name" placeholder="Введіть назву альбома">
        </@common.postForm>
    </@common.createModal>
</@common.html>