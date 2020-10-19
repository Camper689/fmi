<#import "parts/common.ftl" as common><@common.html 'Список груп'>
    <ul id="breadcrumbs" class="breadcrumbs">
        <li><a href="/"><i class="fa fa-home"></i></a></li>
        <li class="current">Список груп</li>
    </ul>

    <div class="block block-margin">
        <button class="btn btn-default" onclick="modal('#addGroupModal', null)">Додати групу</button>

        <table class="table mt-2">
            <thead>
                <th>Курс</th>
                <th>Назва</th>
                <th></th>
            </thead>
            <tbody>
                <#list allGroups as group>
                    <tr>
                        <th>${group.course}</th>
                        <th>${group.name?html}</th>
                        <th><button class="btn btn-default" onclick="modal('#editGroupModal', {name: '${group.name?html}', course: ${group.course?c}, id: ${group.id?c}})">Редагувати</button></th>
                        <th><button class="btn btn-default" onclick="modal('#deleteGroupModal', {id: ${group.id?c}, name: '${group.name?html}'})">Видалити</button></th>
                    </tr>
                </#list>
            </tbody>
        </table>
    </div>

    <@common.createModal 'addGroupModal' 'Додати групу' 'Додати'>
        <@common.postForm "/groups/add">
            <input type="number" class="form-control" name="course" placeholder="Введіть курс" step="1" min="1" max="10" />
            <input class="form-control" name="name" placeholder="Введіть назву групи" />
        </@common.postForm>
    </@common.createModal>

    <@common.createModal 'editGroupModal' 'Редагувати групу' 'Зберегти'>
        <@common.postForm "/groups/edit">
            <input type="number" class="form-control" name="course" placeholder="Введіть курс" step="1" min="1" max="10" />
            <input class="form-control" name="name" placeholder="Введіть назву групи" />
            <input type="hidden" name="id" />
        </@common.postForm>
    </@common.createModal>

    <@common.createModal 'deleteGroupModal' 'Видалити групу' 'Видалити'>
        <@common.postForm '/groups/delete'>
            <input name="id" type="hidden" />
            Ви точно хочете видалити групу &quot;<span name="name"></span>&quot;?
        </@common.postForm>
    </@common.createModal>
</@common.html>