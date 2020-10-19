<#import "parts/common.ftl" as common><@common.html 'Список занять'>
    <ul id="breadcrumbs" class="breadcrumbs">
        <li><a href="/"><i class="fa fa-home"></i></a></li>
        <li class="current">Список занять</li>
    </ul>

    <#include "parts/messages.ftl" />

    <div class="block block-margin">
        <button class="btn btn-default" onclick="modal('#addLessonModal', null)">Додати заняття</button>

        <table class="table mt-2">
            <thead>
                <th>Назва</th>
                <th>Коротка назва</th>
                <th></th>
            </thead>
            <tbody>
                <#list allLessons as lesson>
                    <tr>
                        <th>${lesson.name?html}</th>
                        <th>${lesson.shortName?html}</th>
                        <th><button class="btn btn-default" onclick="modal('#editLessonModal', {name: '${lesson.name?html}', shortName: '${lesson.shortName?html}', id: ${lesson.id?c}})">Редагувати</button></th>
                        <th><button class="btn btn-default" onclick="modal('#deleteLessonModal', {id: ${lesson.id?c}, name: '${lesson.name?html}'})">Видалити</button></th>
                    </tr>
                </#list>
            </tbody>
        </table>
    </div>

    <@common.createModal 'addLessonModal' 'Додати заняття' 'Додати'>
        <@common.postForm "/lessons/add">
            <input class="form-control" name="name" placeholder="Введіть назву заняття" />
            <input class="form-control" name="shortName" placeholder="Введіть коротку назву заняття" />
        </@common.postForm>
    </@common.createModal>

    <@common.createModal 'editLessonModal' 'Редагувати заняття' 'Зберегти'>
        <@common.postForm "/lessons/edit">
            <input class="form-control" name="name" placeholder="Введіть назву заняття" />
            <input class="form-control" name="shortName" placeholder="Введіть коротку назву заняття" />
            <input type="hidden" name="id" />
        </@common.postForm>
    </@common.createModal>

    <@common.createModal 'deleteLessonModal' 'Видалити заняття' 'Видалити'>
        <@common.postForm '/lessons/delete'>
            <input name="id" type="hidden" />
            Ви точно хочете видалити заняття &quot;<span name="name"></span>&quot;?
        </@common.postForm>
    </@common.createModal>
</@common.html>