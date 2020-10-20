<#import "parts/common.ftl" as common><@common.html 'Редагувати вчені звання'>
    <ul id="breadcrumbs" class="breadcrumbs">
        <li><a href="/"><i class="fa fa-home"></i></a></li>
        <li class="current">Вчені звання</li>
    </ul>

    <#include "parts/messages.ftl" />

    <div class="block block-margin">
        <div class="row">
            <@common.postForm "/teacherStatus/add">
                <div class="col-6"><input class="form-control" name="name" placeholder="Введіть назву нового вченого звання" /></div>
                <div class="col-auto"><button class="btn btn-default">Зберегти</button></div>
            </@common.postForm>
        </div>

        <#list allTeacherStatuses as status>
            <div class="row">
                <div class="col-12 col-sm-auto">
                    <@common.postForm "/teacherStatus/edit">
                        <input type="hidden" name="id" value="${status.id?c}" />
                        <input name="name" value="${status.name?html}" placeholder="Введіть назву вченого звання" />
                        <button class="btn btn-success">Змінити назву</button>
                    </@common.postForm>
                </div>
                <div class="col-12 col-sm-auto">
                    <button class="btn btn-danger" onclick="modal('#deleteTeacherStatusModal', {id: ${status.id?c}, name: '${status.name?html}'});">Видалити</button>
                </div>
            </div>
        </#list>
    </div>

    <@common.createModal 'deleteTeacherStatusModal' 'Видалити вчене звання' 'Видалити'>
        <@common.postForm '/teacherStatus/delete'>
            <input name="id" type="hidden">
            Ви точно хочете видалити вчене звання &quot;<span name="name"></span>&quot;?
        </@common.postForm>
    </@common.createModal>
</@common.html>