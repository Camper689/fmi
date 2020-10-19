<#import "parts/common.ftl" as common><@common.html 'Галерея'>
    <ul id="breadcrumbs" class="breadcrumbs">
        <li><a href="/"><i class="fa fa-home"></i></a></li>
        <li><a href="/gallery">Галерея</a></li>
        <li class="current">${album.name?html}</li>
    </ul>

    <div class="block block-margin">
        <#include "parts/messages.ftl" />

        <button class="btn btn-success mb-3" onclick="modal('#addPhotoModal', null);">Додати фото...</button>

        <div class="zoom-gallery album-images">
            <#list album.images as image>
                <div class="image-block">
                    <button type="button" href="javascript:void(0)" onclick="modal('#deletePhotoModal', {id: ${image.id?c}})" class="btn btn-default btn-block">
                        Видалити фото
                    </button>

                    <a href="/image/${image.getFullName()}">
                        <img title="${album.name}" src="/image/${image.getFullName()}" alt="${image.getAlt()}" height="100%">
                    </a>
                </div>
            </#list>
        </div>
        <script>
            $(document).ready(function() {
              $('.zoom-gallery').magnificPopup({
                delegate: 'a',
                type: 'image',
                closeOnContentClick: false,
                closeBtnInside: false,
                mainClass: 'mfp-with-zoom mfp-img-mobile',
                gallery: {
                  enabled: true
                },
                zoom: {
                  enabled: true,
                  duration: 300,
                  opener: function(element) {
                    return element.find('img');
                  }
                }

              });
            });
        </script>
    </div>

    <@common.createModal 'addPhotoModal' 'Додати фотографії' 'Додати'>
        <@common.postForm '/album/loadPhoto' '' 'multipart/form-data'>
            <input type="hidden" name="id" value="${album.id?c}" />
            <div class="form-row">
                <input class="form-control" type="file" name="files[]" placeholder="Оберіть файл(и).." multiple="multiple" accept="image/*" />
            </div>
        </@common.postForm>
    </@common.createModal>

    <@common.createModal 'deletePhotoModal' 'Видалити фотографію' 'Видалити'>
        <@common.postForm '/album/deletePhoto'>
            <input type="hidden" name="albumId" value="${album.id?c}" />
            <input type="hidden" name="id" value="" />
            Ви точно хочете видалити це фото?
        </@common.postForm>
    </@common.createModal>
</@common.html>