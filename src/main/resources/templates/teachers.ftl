<#import "parts/common.ftl" as common><@common.html 'Викладачі «' + department.name + '»'>
    <ul id="breadcrumbs" class="breadcrumbs">
        <li><a href="/"><i class="fa fa-home"></i></a></li>
        <li><a href="/departments">Кафедри</a></li>
        <li><a href="/departments/${department.id?c}">${department.name?html}</a></li>
        <li class="current">Викладачі</li>
    </ul>

    <#include "parts/messages.ftl" />

    <button class="btn btn-default" href="javascript:void(0)" onclick="modal('#addTeacherModal', null);">Додати викладача</button>
    <a class="btn btn-default" href="/teacherStatus">Редагувати вчені звання</a>
    <a class="btn btn-default" href="/groups">Редагувати список груп</a>
    <a class="btn btn-default" href="/lessons">Редагувати список занять</a>

    <div class="row">
        <#list allTeachers as teacher>
            <div class="col-sm-4">
                <div class="dep-teacher-block">
                    <div class="block">
                        <div class="dep-teacher-photo">
                            <div class="dep-teacher-photo-bg">
                                <#if teacher.hasAvatar()><img src="/image/${teacher.avatar.getFullName()}" height="100%" alt="${teacher.getFullName()}"></#if>
                            </div>
                        </div>
                        <div class="dep-teacher-info">
                            <div class="dep-teacher-name">
                                <a href="/teacher/${teacher.id?c}">${teacher.getFullName()}</a>
                            </div>
                            <div class="dep-teacher-status"><#if teacher.status??>${teacher.status.name}</#if></div>
                        </div>
                    </div>
                    <a href="javascript:void(0)" onclick="modal('#editTeacherModal', {id: ${teacher.id?c}, firstName: '${teacher.firstName?html}', middleName: '${teacher.middleName?html}', lastName: '${teacher.lastName?html}'})">Редагувати</a>
                    <a href="javascript:void(0)" onclick="modal('#setDepartmentChiefModal', {teacher: ${teacher.id?c}, name: '${teacher.getShortName()}'})">Зробити завідуючим кафедри</a>
                    <a href="javascript:void(0)" onclick="modal('#deleteTeacherModal', {id: ${teacher.id?c}, name: '${teacher.getFullName()}'})" class="text-danger">Видалити</a>
                </div>
            </div>
        </#list>
    </div>

    <@common.createModal 'addTeacherModal' 'Додати викладача' 'Додати'>
        <@common.postForm '/departments/addTeacher' '' 'multipart/form-data'>
            <input type="hidden" name="department" value="${department.id?c}" />
            <input class="form-control" name="firstName" placeholder="Введіть прізвище викладача">
            <input class="form-control" name="middleName" placeholder="Введіть ім'я викладача">
            <input class="form-control" name="lastName" placeholder="Введіть по-батькові викладача">
            <select name="status">
                <#list allTeacherStatuses as status><option value="${status.id?c}">${status.name?html}</option></#list>
            </select>
            <input class="form-control" name="avatar" type="file" accept="image/*" placeholder="Оберіть аватар викладача" />
        </@common.postForm>
    </@common.createModal>

    <@common.createModal 'editTeacherModal' 'Редагувати запис' 'Зберегти'>
        <@common.postForm '/departments/editTeacher'>
            <input name="id" type="hidden">
            <input name="departmentId" value="${department.id?c}" type="hidden" />
            <input class="form-control" name="firstName" placeholder="Введіть прізвище викладача">
            <input class="form-control" name="middleName" placeholder="Введіть ім'я викладача">
            <input class="form-control" name="lastName" placeholder="Введіть по-батькові викладача">
            <select name="status">
                <#list allTeacherStatuses as status><option value="${status.id?c}">${status.name?html}</option></#list>
            </select>
            <input class="form-control" name="avatar" type="file" accept="image/*" placeholder="Оберіть аватар викладача" />
        </@common.postForm>
    </@common.createModal>

    <@common.createModal 'deleteTeacherModal' 'Видалити викладача' 'Видалити'>
        <@common.postForm '/departments/deleteTeacher'>
            <input name="id" type="hidden" />
            <input name="departmentId" type="hidden" value="${department.id?c}" />
            Ви точно хочете видалити викладача &quot;<span name="name"></span>&quot;?
        </@common.postForm>
    </@common.createModal>

    <@common.createModal 'setDepartmentChiefModal' 'Позначити викладача як завідуючого кафедри' 'Зберегти'>
        <@common.postForm '/departments/setChief'>
            <input name="teacher" type="hidden" />
            <input name="department" type="hidden" value="${department.id?c}" />
            Ви точно хочете позначити викладача &quot;<span name="name"></span>&quot; як завідуючого кафедри?
        </@common.postForm>
    </@common.createModal>
</@common.html>